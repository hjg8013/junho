package org.jun.controller;

import java.util.ArrayList;

import org.jun.domain.ReplyDTO;
import org.jun.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		System.out.println("getListbno = "+bno);
		
		return new ResponseEntity<>(rservice.list(bno),HttpStatus.OK);
	}
	//댓글수정을 하기 위해 댓글내용 가져오기
	@GetMapping(value="{rno}",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyDTO> getDetail(@PathVariable int rno){
		
		System.out.println("getDetailrno = "+rno);
		
		return new ResponseEntity<>(rservice.detail(rno),HttpStatus.OK);
	}
	//@RequestBody 객체를 가져올때 사용한다  @PathVariable 변수를 가져올때 사용한다
	@PutMapping(value="update",consumes="application/json",produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> update(@RequestBody ReplyDTO rdto){
		System.out.println("updaterdto = "+rdto);
		
		int result=rservice.update(rdto);
		//		update 정상적으로 처리되었을때, update 배정상적으로 처리되었을 때
		return result==1?new ResponseEntity<>("success",HttpStatus.OK):new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="remove",consumes="application/json",produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@RequestBody ReplyDTO rdto) {
		
		System.out.println("removedto = "+rdto);
		
		int result=rservice.remove(rdto);
		
		return result==1?new ResponseEntity<>("success",HttpStatus.OK):new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
