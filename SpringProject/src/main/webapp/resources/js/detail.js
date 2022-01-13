/**
 * 
 */
$(document).ready(function(){
	//댓글쓰기 버튼을 클릭하면
	$("#addReplyBtn").on("click",function(){
		//alert("aa");
		$(".modal fade").modal("show");
		//alert("bb");
	})
	//모딜창을 띄워라
	//console.log(replyService); //다시보여
	var bno= $("#bno").html();
	console.log(bno);
	
	//detail.jsp가 실행되자마자 댓글목록리스트가 실행되어야함.
	replyService.getList({bno:bno},function(list){
		console.log(list)
		var str="";
		
		for(var i=0;i<list.length;i++){
			str+= list[i].bno+"  "	
			str+= list[i].replyer+"  "
			str+= list[i].reply+"<br>"	
		}
		$("#relist").html(str);
	});
	
	//댓글쓰기 버튼을 클릭하면
	$("#modalRegisterBtn").on("click",function(){
		//사용자가 입력한 댓글내용을 저장
		var reply = $("input[name='reply']").val();
		//사용자가 입력한 댓글작성자를 저장
		var replyer = $("input[name='replyer']").val();
		//              ajax보내고자하는 json타입                                ,callback함수호출
		replyService.add({reply:reply,replyer:replyer,bno:bno},
						function(result){
							alert("insert 작업 : "+result)
							//목록리스트를 처리
							
						);
		
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
	
	
	
	//댓글수정을 하기 위한 함수 선언
	
	//댓글삭제를 하기 위한 함수 선언
	
	
	
	
	
	// return {name:"AAA"};
	return {
		add:add,
		getList:getList
			};
})()