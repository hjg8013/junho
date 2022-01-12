package org.jun.controller;

import org.jun.domain.ReplyDTO;
import org.jun.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
		rservice.write(rdto);
		return null;
	}
}
