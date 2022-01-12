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
	//댓글쓰기 버튼을 클릭하면
	$("#modalRegisterBtn").on("click",function(){
		//사용자가 입력한 댓글내용을 저장
		var reply = $("input[name='reply']").val();
		//사용자가 입력한 댓글작성자를 저장
		var replyer = $("input[name='replyer']").val();
		
		replyService.add({reply:reply,replyer:replyer,bno:bno});
	})
})

var replyService=(function(){
	
	//댓글쓰기를 하기 위한 함수 선언
	function add(reply){
		console.log("reply.......");
		﻿
		$.ajax({
		//url:"/controller/replies/new",
		url:"/controller/replies/new",
		type:"post",
		data:JSON.stringify(reply), // JSON.stringfy : 자바스크립트의 값을 JASON 문자열로 변환
        contentType:"application/json; charset=utf-8",
        success:function(){
           
        },   // 통신이 정상적으로 성공했으면
        error:function(){
           
        }      // 통신이 비정상적으로 처리가 되어 error가 있으면
     })
	}
	
	
	
	
	
	
	
	
	// return {name:"AAA"};
	return {add:add};
})()