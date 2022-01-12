package org.jun.controller;

import javax.jws.soap.SOAPBinding.Style;

import org.jun.domain.RestSampleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//비동기식으로 처리를해라
@RestController
@RequestMapping("smaple")
public class RestSampleController {
	
	@GetMapping(value="getText",produces="text/plain; charset=UTF-8")
	public String getText() {
		
		return "안녕하세요.";
	}
	// 객체 반환 {MediaType.APPLICATION_JSON_UTF8_VALUE
	@GetMapping(value="getSampl",produces={MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE})
	public RestSampleDTO getSampl() {
		
		return new RestSampleDTO(100,"정","자바");
	}
	//﻿ResponseEntity타입 반환
	
	@GetMapping(value="check")
	public ResponseEntity<RestSampleDTO> check(int mno,String firsName, String lastName){
		// 사용자라부터 매니저번호
		RestSampleDTO rsdto = new RestSampleDTO(mno,firsName,lastName);
		ResponseEntity<RestSampleDTO> result = null;
		//매니저번호가 150미만이면
		if(mno<150) {
			//비정상으로 처리
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(rsdto);
		}
		else { 		//그렇지 않으면
			result = ResponseEntity.status(HttpStatus.OK).body(rsdto);
		}
		//http://localhost:8080/controller/smaple/check.json?mno=160&firsName=황&lastName=준호
		//정상적으로 처리
		return result;
	}
	//매소드의 매개변수
	//객체타입을 매개변수로 지정해야 되는 경우에는 @RequesBody를 사용해야함
	@PostMapping("mno")
	public RestSampleDTO mno(@RequestBody RestSampleDTO rsdto) {
		
		System.out.println("rsdto"+rsdto);
		
		return rsdto;
	}
	//{"변수값","값","변수값","값"}
	
}
