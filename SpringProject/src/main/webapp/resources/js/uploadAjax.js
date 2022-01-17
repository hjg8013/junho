/**
 * 
 */
$(document).ready(function(){
	$("input[type='submit']").on("click",function(){
		alert("클릭");
		
		var formData = new FormData();
		var inputFile=$("input[name='uploadFile']");
		var files=inputFile[0].files;
		console.log(files);
		
		for(var i=0;i<files.length;i++){
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
				alert("upload성공")
			}
			
		})
	})
})