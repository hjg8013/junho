package org.jun.controller;

import java.util.ArrayList;

import org.jun.domain.ReplyDTO;
import org.jun.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("replies")
public class ReplyController {
	
	@Autowired
	private ReplyService rservice;
	
	//댓글쓰기를 하기 ㅎ위한 RequestMapping
	//consumes : 들어오는 데이터 타입정의(생략가능)
	//produces : 반환하는 데이터 타입 정의(생략가능)
	//둘다 생략을 하게되면 웹브라우저가 알아서 판단한다
	@PostMapping(value="new",consumes="application/json",produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyDTO rdto){
		System.out.println("ReplyDTO"+rdto);
		//insert 성공시 ReplyService.java로 부터1
		//insert 실패시 ReplyService.java로 부터0
		//값을 리턴받는다
		int result=rservice.write(rdto);
		
		//				insert 정상적으로 처리되었을때, insert 배정상적으로 처리되었을 때
		return result==1?new ResponseEntity<>("success",HttpStatus.OK):new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="list/{bno}",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ArrayList<ReplyDTO>> getList(@PathVariable int bno){
		//@PathVariable : REST방식에서 주로 사용. URL경로의 일부를 파라미터 사용하고자할 때  {bno} = int bno로 사용하고자 할때 쓴다
		System.out.println("Controllerbno = "+bno);
		
		return new ResponseEntity<>(rservice.list(bno),HttpStatus.OK);
	}
	
	
	
	
}
