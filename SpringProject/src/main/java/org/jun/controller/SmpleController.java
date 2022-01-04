package org.jun.controller;

import org.jun.domain.SampleMemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//컨트롤을 하는 클래스라고 정의되어있다
@Controller
public class SmpleController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "sample", method = RequestMethod.GET)
	public void basic() {
		logger.info("sample 실행됨.");
	}
	
//	//샘플이라는 폴더를 하나 만들어줘야된다 그안에 ex01을 만들어야한다
//	@RequestMapping(value = "sample/ex01", method = RequestMethod.GET)
//	public void basic1() {
//		logger.info("sample/ex01 실행됨.");
//	}
	
	//이런식으로 만들면 폴더를 안만들어도 실행이된다
	@RequestMapping(value = "sample/ex01", method = RequestMethod.GET)
	public String basic2() {
		logger.info("sample/ex01 실행됨.");
		return "ex01";
	}
	
  //파일을 처리하는 방법
	@RequestMapping(value = "sample/index",method = RequestMethod.GET)
	public void index() {
		
	}
//	@RequestMapping(value = "sample",method = RequestMethod.GET)
//	public String index1() {
//		return "index";
//	}
	
	@RequestMapping(value = "sample/member",method = RequestMethod.GET)
	public void member(String id,String pw,String name) {
		System.out.println("id="+id);
		System.out.println("pw="+pw);
		System.out.println("name="+name);
	}
	
	@RequestMapping(value = "sample/memberDTO",method = RequestMethod.GET)
	public void memberdto(SampleMemberDTO smd) {
		System.out.println("id="+smd.getId());
		System.out.println("pw="+smd.getPw());
		System.out.println("name="+smd.getName());
	}
	//사이트들어갈때 주소
	@RequestMapping(value = "/catdream/containercat",method = RequestMethod.GET)
	public void cat() {
//		//jsp의파일의 경로를 찾아줘야한다
//		return "catdream/containercat";
	}
	
	
}
