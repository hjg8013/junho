/**
 * 
 */
$(document).ready(function(){
	//댓글쓰기
	$("#modalRegisterBtn").show();
	//댓글 수정
	$("#modalModBtn").show();
	//댓글 삭제
	$("#modalRemoveBtn").show();
	
	//댓글쓰기 버튼을 클릭하면
	$("#addReplyBtn").on("click",function(){
		//alert("aa");
		//댓글쓰기
		$("#modalRegisterBtn").show();
		//댓글 수정
		$("#modalModBtn").hide();
		//댓글 삭제
		$("#modalRemoveBtn").hide();
		
		$("input[name='replyer']").val("");
		$("input[name='reply']").val("");
		
		//$(".modal").modal("show");
		//alert("bb");
	})
	//모딜창을 띄워라
	//console.log(replyService); //다시보여
	var bno= $("#bno").html();
	console.log(bno);
	
	//detail.jsp가 실행되자마자 댓글목록리스트가 실행되어야함.
//	replyService.getList({bno:bno},function(list){
//		console.log(list)
//		var str="";
//		
//		for(var i=0;i<list.length;i++){
//			str+= list[i].bno+"  "	
//			str+= list[i].replyer+"  "
//			str+= list[i].reply+"<br>"	
//		}
//		$("#relist").html(str);
//	});
	
	//detail.jsp가 실행되자마자 댓글목록리스트가 실행되어야함.
	showList();
	
	function showList(){
		replyService.getList({bno:bno},function(list){
			console.log(list)
			var str="";
			
			for(var i=0;i<list.length;i++){
				str+= "<li data-rno='"+list[i].rno+"'><div>"+list[i].bno+"</div>"	
				str+= "<div><b>"+list[i].replyer+"</b></div>"
				str+= "<div>"+list[i].reply+"</div>"	
			}
			$("#relist").html(str);
		});
	}
	
	var bno=$("#bno").html();

	//상세페이지가 시작되자마자 이미지를 출력하기 위한 ajax
	$.getJSON("/controller/board/fileList/"+bno+".json",
		function(data){ //BoardController에 있는 fileList를 통해 얻어진 select결과를  data에 저장한후,
				//datail.jsp에 뿌리기
			console.log(data);
			var str="";
				$(data).each(function(i,obj){
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

		})
	//댓글쓰기 버튼을 클릭하면
	$("#modalRegisterBtn").on("click",function(){
		
		//사용자가 입력한 댓글내용을 저장
		var reply = $("input[name='reply']").val();
		//사용자가 입력한 댓글작성자를 저장
		var replyer = $("input[name='replyer']").val();
		//              ajax보내고자하는 json타입                                ,callback함수호출
		replyService.add({reply:reply,replyer:replyer,bno:bno},
						function(result){
							//alert("insert 작업 : "+result)
							//목록리스트를 처리
							showList();
						});
		//모달창을 숨겨ㅏ
		$(".modal").modal("hide");
	})
	
	//댓글내용을 클릭하면
	//$("#relist").on("click",function(){
	$("#relist").on("click","li",function(){
		
		//$("#relist").on("click","li",function(){
		//li를 추가해서 범위를 한정시켜주는것이다
		//rno값을 가져오기
		var rno= $(this).data("rno");
		
		//alert($("li").data("rno"));
		//현재는 1만 계속나온다
		alert($(this).data("rno"));
		//내가 클릭한 li의 data를 가져온다
		
		replyService.reDetail(rno,function(detail){
			
			//내용확인
			console.log(detail.replyer);
			console.log(detail.reply);
			
			//input태그안에 내용을 넣어준다
			$("input[name='rno']").val(detail.rno);
			$("input[name='replyer']").val(detail.replyer);
			$("input[name='reply']").val(detail.reply);
		});
		
		
		//alert("aaa");
		//댓글쓰기
		$("#modalRegisterBtn").hide();
		//댓글 수정
		$("#modalModBtn").show();
		//댓글 삭제
		$("#modalRemoveBtn").show();
		//model을 띄워라
		$(".modal").modal("show");

	})
	//댓글수정버튼을 클릭하면
	$("#modalModBtn").on("click",function(){
		
		//alert("modalModBtn");
		var reply={rno:$("input[name='rno']").val(),reply:$("input[name='reply']").val()}
		console.log(reply);
		//댓글수정함수를 호출해서 처리
		replyService.reupdate(reply,function(update){
			// 콜백영역update가 정상적으로 처리된 후 조치
			//alert("update 작업 : "+update)
			//모달창을 숨겨ㅏ
			$(".modal").modal("hide");
			//댓글 목록리스트 다시보여주기
			showList();
			
		})
		
	})
	//댓글삭제버튼을 클릭하면
		$("#modalRemoveBtn").on("click",function(){
		
		alert("modalRemoveBtn");
		var rno = {rno:$("input[name='rno']").val()};
		console.log(rno);
		//댓글삭제함수를 호출해서 처리
		replyService.remove(rno,function(remove){
			// 콜백영역delete가 정상적으로 처리된 후 조치
			alert("delete 작업 : "+remove)
			//모달창을 숨겨ㅏ
			$(".modal").modal("hide");
			//댓글 목록리스트 다시보여주기
			showList();
			
		})
		
	})
	
})

var replyService=(function(){
	
	//댓글쓰기를 하기 위한 함수 선언
	function add(reply,callback){
		console.log("reply.......");
		﻿
		$.ajax({
		//url:"/controller/replies/new",
		url:"/controller/replies/new",
		type:"post",
		data:JSON.stringify(reply), // JSON.stringfy : 자바스크립트의 값을 JASON 문자열로 변환
        contentType:"application/json; charset=utf-8",
        success:function(result){
           //callback함수선언
        	if(callback)
        		//만약 콜백함수가 있으면
        		callback(result);
        	
        },   // 통신이 정상적으로 성공했으면
        error:function(){
           
        }      // 통신이 비정상적으로 처리가 되어 error가 있으면
     })
	}
	//댓글목록리스트를 하기 위한 함수선언
	function getList(param,callback){
		var bno=param.bno;
		console.log("getList"+bno);
		$.getJSON("/controller/replies/list/"+bno+".json",
				function(data){
					if(callback)
						callback(data);		
				})//http://localhost:8080/controller/replies/list/4.json 결과값을 보여준다
	}
	

	//댓글수정을 하기 위해 댓글 내용 가져오기 함수 선언
	function reDetail(rno,callback){
		var detail = rno;
		$.getJSON("/controller/replies/"+rno+".json",
				function(data){
					if(callback)
						callback(data);
				})
	}
	
	
	//댓글수정을 하기 위한 함수 선언
	function reupdate(reply,callback){
		$.ajax({
			url:"/controller/replies/update",
			type:"put",
			data:JSON.stringify(reply),
	        contentType:"application/json; charset=utf-8",
	        success:function(result){
	            //callback함수선언
	         	if(callback)
	         		//만약 콜백함수가 있으면
	         		callback(result);
	         	
	         },   // 통신이 정상적으로 성공했으면
	         error:function(){
	            
	         }      // 통신이 비정상적으로 처리가 되어 error가 있으면
		})
	}
	
	
	
	
	
	
	//댓글삭제를 하기 위한 함수 선언
	function remove(reply,callback){
		$.ajax({
			url:"/controller/replies/remove",
			type:"delete",
			data:JSON.stringify(reply),
	        contentType:"application/json; charset=utf-8",
	        success:function(result){
	            //callback함수선언
	         	if(callback)
	         		//만약 콜백함수가 있으면
	         		callback(result);
	         	
	         },   // 통신이 정상적으로 성공했으면
	         error:function(){
	            
	         }      // 통신이 비정상적으로 처리가 되어 error가 있으면
		})
	}
	
	
	
	
	// return {name:"AAA"};
	return {
		add:add,
		getList:getList,
		reDetail:reDetail,
		reupdate:reupdate,
		remove:remove
			};
})()