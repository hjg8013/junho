/**
 * 
 */
$(document).ready(function(){
	
	//파일의 크리와 화장자를 검사하는 함수 선언
	//서버에서 설정해 놓은 파일크기 설정
	var maxSize=5242880;  //5MB
	//서버에서 허용 가능한 확장자 설정(정규식)
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	
	function checkExtension(fileSize,fileName){
		var str ="";
		
		//사용자가 선택한 파일의 크기가 서버에서 설정해 놓은 파일의 크기보다 이상이면,
		if(fileSize>=maxSize){
			//str =+"파일 사이즈 초과";
			alert("파일 사이즈 초과");
			return false;
		}
		//사용자가 선택한 파일의 확장자가 서버에서 설정해 놓은 파일의 확장자와 일치하지 않으면
		if(regex.test(fileName)){
			//str =+"해당 종류의 파일은 업로드 할 수 없습니다.";
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		//alert(str);
		return true;	
	}
	
	/*수정전
	 * 
	 * 
	 * 	$("input[type='submit']").on("click",function(){
		alert("클릭");
		
		var formData = new FormData();
		var inputFile=$("input[name='uploadFile']");
		var files=inputFile[0].files;
		console.log(files);
		
		for(var i=0;i<files.length;i++){
			//파일크기가 이상하면 밑에있는거 하지마라
			if(!checkExtension(files[i].size,files[i].name)){
				//밑에거 하지마라
				return false;
			}
			formData.append("uploadFile",files[i]);
		}
		
		//formData 자채를 데이터로 전송하고, formData를 데이터로 전송할때에는 processData,contentType은 반드시 false여야 한다
		$.ajax({
			url:"uploadAjaxAction",
			type:"post",
			data:formData,
			processData:false,
			contentType:false,
			success:function(result){
				//이게 실행될려면 ReplyController에 HttpSt ok가 실행될때 실행된다
				console.log(result);
				//console.log(result[0].fileName); 이런식으로 하나씩 선택을해서 사용한다
				
				
				//사용자가 선택한 파일을 원하는 경로에 성공적으로 업로드 한후
				
				
				
				showUploadFile(result);
				
				
				
				
			},   // 통신이 정상적으로 성공했으면
	        error:function(){
	            
	        } 
			
			
		})
	})
	*/
	
	
	var formObj = $("form[role='form']")
	//글쓰기 버튼을 클릭하면
	$("input[type='submit']").on("click",function(e){
		e.preventDefault();
		//데이터 태그에 있는
		//data선택자를 이용하여
		//input태그에 value값으로 변환
		var str="";
		//각 li태그에 있는
		$("#uploadResult ul li").each(function(i,obj){
			console.log(obj);
			//data선택자를 이용해서  input태그의 value값을 샛팅
			/*
			 * data함수
			 * span
			 * $("span").data("age",li) => <span data-age="13"> data함수 괄호안에 매개변수가 두개면  setter
			 * <span data-age="13">
			 * $("span").data("age") => 13 data함수 괄호 안에 매개변수가 하마면 getter
			 * */
			str+="<input type='hidder' name='attachList["+i+"].fileName' value='"+$(obj).data("filename")+"'>"
			str+="<input type='hidder' name='attachList["+i+"].uuid' value='"+$(obj).data("uuid")+"'>"
			str+="<input type='hidder' name='attachList["+i+"].uploadPath' value='"+$(obj).data("path")+"'>"
			str+="<input type='hidder' name='attachList["+i+"].image' value='"+$(obj).data("type")+"'>"
		})
		formObj.append(str).submit();
		//기존에 없었다가 그밑에 추가가 되었다 그리고 컨트롤러에 여기에있는 데이터를 넘겨줘야한다
	})


	
	
	
	
	//파일 선택의 내용이 변경되면
	$("input[type='file']").on("change",function(e){
		//alert("ㅁㅁㅁㅁㅁ");
		
		var formData = new FormData();
		var inputFile=$("input[name='uploadFile']");
		var files=inputFile[0].files;
		
		for(var i=0;i<files.length;i++){
			//파일크기가 이상하면 밑에있는거 하지마라
			if(!checkExtension(files[i].size,files[i].name)){
				//밑에거 하지마라
				return false;
			}
			formData.append("uploadFile",files[i]);
		}
		
		//formData 자채를 데이터로 전송하고, formData를 데이터로 전송할때에는 processData,contentType은 반드시 false여야 한다
		$.ajax({
			url:"/controller/uploadAjaxAction",
			type:"post",
			data:formData,
			processData:false,
			contentType:false,
			success:function(result){
				//이게 실행될려면 ReplyController에 HttpSt ok가 실행될때 실행된다
				console.log(result);
				//console.log(result[0].fileName); 이런식으로 하나씩 선택을해서 사용한다
				
				
				//사용자가 선택한 파일을 원하는 경로에 성공적으로 업로드 한후

				showUploadFile(result);
	
			},   // 통신이 정상적으로 성공했으면
	        error:function(){
	            
	        } 
			
			
		})
	})
	
	
	
})//ready end

//사용자가 선택한 파일을 원하는 경로에 성공적으로 업로드 한 후 웹브라우저에 파일을 띄워라에 대한 함수 선언
function showUploadFile(uploadresultArr){
	var str="";
	$(uploadresultArr).each(function(i,obj){
		console.log(obj);
		if(!obj.image){ //사용자가 업로드 한 파일의 타입이 이미지가 아니면 excel문서파일 
			//str+="<li>"+"이미지 파일아님"+"</li>"
			var fileCallPath=encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
			console.log(fileCallPath);
			str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><a href='/controller/download?fileName="+fileCallPath+"'>"+obj.fileName+"</a></li>"
		}else{ //사용자 업로드 한 파일으 타입이 이미지이면 img,png,gif
			//str+="<li>"+"이미지 파일임"+"</li>"
			var fileCallPath=encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
			console.log(fileCallPath);
			str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><img src='/controller/display?fileName="+fileCallPath+"'></li>"
		}	
	})
	$("#uploadResult ul").html(str);
}





//사용자가 선택한 파일을 원하는 경로에 성공적으로 업로드 한 후 웹브라우저에 파일을 띄워라에 대한 함수 선언
//function showUploadFile(uploadresultArr){
//	var str="";
//	$(uploadresultArr).each(function(i,obj){
//		console.log(obj);
//		if(!obj.image){ //사용자가 업로드 한 파일의 타입이 이미지가 아니면 excel문서파일 
//			str+="<li>"+"이미지 파일아님"+"</li>"
//			var fileCallPath=encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
//			console.log(fileCallPath);
//			str+="<li><a href='/controller/download?fileName="+fileCallPath+"'>"+obj.fileName+"</a></li>"
//		}else{ //사용자 업로드 한 파일으 타입이 이미지이면 img,png,gif
//			str+="<li>"+"이미지 파일임"+"</li>"
//			var fileCallPath=encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
//			console.log(fileCallPath);
//			str+="<li><img src='/controller/display?fileName="+fileCallPath+"'></li>"
//		}	
//	})
//	$("#uploadResult ul").html(str);
//}


