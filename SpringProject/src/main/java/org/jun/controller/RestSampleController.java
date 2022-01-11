package org.jun.controller;

import org.jun.domain.RestSampleDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
}
