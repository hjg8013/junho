package org.jun.controller;

import org.jun.domain.SampleMemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//컨트롤을 하는 클래스라고 정의되어있다
@Controller
//@RequestMapping("sample") //이런식으로 작성해두면 sample를 줄일수있다
public class SmpleController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	//@RequestMapping(value = "sample", method = RequestMethod.GET)
//	@GetMapping("") //RequestMapping를 사용하면 이런식으로 생략할수있다 주소에서는 생략못함
//	@PostMapping("") //RequestMapping를 사용하면 이런식으로 생략할수있다 주소에서는 생략못함
//	public void basic() {
//		logger.info("sample 실행됨.");
//	}
	@RequestMapping(value = "sample/sample", method = RequestMethod.GET)
	public void basic(Model model) {
		logger.info("sample 실행됨.");
		//model.addAttribute(변수의 이름, 데이터)
		model.addAttribute("abcd", "aaaa");
	}
	
//	//샘플이라는 폴더를 하나 만들어줘야된다 그안에 ex01을 만들어야한다
//	@RequestMapping(value = "sample/ex01", method = RequestMethod.GET)
//	public void basic1() {
//		logger.info("sample/ex01 실행됨.");
//	}
	
	//이런식으로 만들면 폴더를 안만들어도 실행이된다
	@RequestMapping(value = "sample/ex01", method = RequestMethod.GET)
	//@GetMapping("sample/ex01") 이런식으로 위의 코드를 줄일수있다
	public String basic2() {
		logger.info("sample/ex01 실행됨.");
		return "ex01";
	}
	
  //파일을 처리하는 방법
//	@RequestMapping(value = "sample/index",method = RequestMethod.GET)
//	public void index() {
//		
//	}
//	
	@RequestMapping(value = "sample/index",method = RequestMethod.GET)
	public String index(Model model) {
		//model.addAttribute(변수의 이름, 데이터)
		model.addAttribute("cccc", "XXXX");
		
		return "sample/index";
	}
//	@RequestMapping(value = "sample",method = RequestMethod.GET)
//	public String index1() {
//		return "index";
//	}
	
//	@RequestMapping(value = "sample/member",method = RequestMethod.GET)
//	public void member(String id,String pw,String name) {
//		System.out.println("id="+id);
//		System.out.println("pw="+pw);
//		System.out.println("name="+name);
//	}
	
	@RequestMapping(value = "sample/member",method = RequestMethod.GET)
	public void member(String id,String pw,String name, Model model) {
		System.out.println("id="+id);
		System.out.println("pw="+pw);
		System.out.println("name="+name);
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		model.addAttribute("name", name);
	}
	
	//post로 불러오면 post로 통일시켜야지 결과값이나온다 한글은 깨짐 수정이필요
//	@RequestMapping(value = "sample/memberDTO",method = RequestMethod.POST)
//	public void memberdto(SampleMemberDTO smd, Model model) {
//		System.out.println("id="+smd.getId());
//		System.out.println("pw="+smd.getPw());
//		System.out.println("name="+smd.getName());
//		model.addAttribute("id", smd.getId());
//		model.addAttribute("pw", smd.getPw());
//		model.addAttribute("name", smd.getName());
//		
//	}
	//post로 불러오면 post로 통일시켜야지 결과값이나온다 한글은 깨짐 수정이필요
	@RequestMapping(value = "sample/memberDTO",method = RequestMethod.POST)
	public String memberdto(SampleMemberDTO smd, Model model) {
		System.out.println("id="+smd.getId());
		System.out.println("pw="+smd.getPw());
		System.out.println("name="+smd.getName());
		model.addAttribute("id", smd.getId());
		model.addAttribute("pw", smd.getPw());
		model.addAttribute("name", smd.getName());
		
		//redirect:를 적은다음 경로를 적어주면 해당경로로 간다
		return "redirect:/controller";
	}
	//사이트들어갈때 주소
	@RequestMapping(value = "/catdream/containercat",method = RequestMethod.GET)
	public void cat() {
//		//jsp의파일의 경로를 찾아줘야한다
//		return "catdream/containercat";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
