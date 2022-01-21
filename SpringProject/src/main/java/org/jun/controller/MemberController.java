package org.jun.controller;

import javax.servlet.http.HttpSession;

import org.jun.domain.MemberDTO;
import org.jun.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private MemberService mservice;
	
	@GetMapping("member")
	public void Getmember() {
		System.out.println("Getmember");
	}
	//회원가입화면
	@PostMapping("member")
	public void Postmember(MemberDTO mdto) {
		System.out.println("Postmember");
		mservice.insert(mdto);
	}
	//로그인 화면
	@GetMapping("login")
	public void Getlogin() {
		System.out.println("Getlogin");
	}
	//로그인 화면을 통해 없어진 데이터를 활용하여 select
	
	@PostMapping("login")
	public String Postlogin(MemberDTO mdto,HttpSession session) {
		System.out.println("Postlogin="+mservice.login(mdto));
		
		MemberDTO login= mservice.login(mdto);
		//MebmerDTO에 있는 MemberDTO 에있는 세션영영ㄱ에 login이라는 변수를 저장
		//세션(session)객채에 login변수에, login값을 저장 (setAttribute)
		session.setAttribute("login", login); //로그아웃이나 웹브라우저를 닫지않는이상 데이터가 사라지지 않는다
		
		//session.invalidate(); //새션을 초기화 하겠다 로그아웃할때 사용한다
		
		
		
		//session영역에 login이라는 변수에 값이 있으면 로그인 된채로
		if(session.getAttribute("login")!=null) {
			//main페이지로 이동
			return "redirect:/";
			
		}else { //session영역에 login이라는 변수에 값이 없으면(null)
			//다시 로그인 할 수 있게 로그인 페이지로 이동
			return "redirect:/controller/member/login";
		}
	}
	
	
	
}
