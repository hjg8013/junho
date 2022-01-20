package org.jun.controller;

import java.util.ArrayList;

import org.jun.domain.AttachFileDTO;
import org.jun.domain.BoardDTO;
import org.jun.domain.Criteria;
import org.jun.domain.PageDTO;
import org.jun.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("board")
public class BoardController {
	
	// @Autowired 사용하면private BoardService service = new BoardService();
	@Autowired
	private BoardService service;
	
	//글쓰기 화면으로
	@GetMapping("write")
	public void write() {
		System.out.println("write");
	}
	//글쓰기 버튼을 클릭하면
	@PostMapping("write")
	public String writePost(BoardDTO board) {
		
		service.write(board);
		System.out.println("postwrite" + board);
		return "redirect:/board/tables";
		
	}
	//게시판 목록 리스트
	@GetMapping("tables")
	public void tables(Criteria cri,Model model) {
		System.out.println("tables"+service.tables(cri));
		//컨트롤에서 뷰로 넘겨주는역할이 addAttribute이다
		model.addAttribute("tables",service.tables(cri));
		//이런식으로 바로 위에 등록하지 않고 바로사용할수있다 나중에 60도 받아와서 작업을 할수있게 만들거다
		int total=service.getTotalCount(cri);
		System.out.println("total"+total);
		//total을 가져와서 사용
		model.addAttribute("pageMaker", new PageDTO(cri,total));
	}
	//게시판 목록 리스트에서 제목으 클릭하면
	@GetMapping("datail")
	public void datail(BoardDTO board,Model model) {
		System.out.println("datail");
		
		model.addAttribute("datail",service.datail(board));
	}
	
	//게시판 상세페이지에서 이미지를 출력하기 위한 select됨 결과를 javaseript로...
	@GetMapping(value="fileList/{bno}",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ArrayList<AttachFileDTO>> fileList(@PathVariable int bno){
		System.out.println("fileList");
		return new ResponseEntity<>(service.fileList(bno),HttpStatus.OK);
	}
	
	
	//글수정 화면으로
	@GetMapping("modify")
	public void modify(BoardDTO board,Model model) {
		System.out.println("modify");
		model.addAttribute("datail",service.datail(board));
	}
	
	//글수정 버튼을 클릭하면
	@PostMapping("modify")
	public String modifyPost(BoardDTO board,RedirectAttributes rttr) {
		System.out.println("postmodify");
		service.modify(board);
		rttr.addAttribute("bno", board.getBno());
		//controller에서 bno값이 필요해서 추가했다 bno에 넣어줘라 get으로 불러온값을
		return "redirect:/board/datail";
	}
	//글삭제 버튼을 클릭하면
	@GetMapping("remove")
	public String remove(BoardDTO board) {
		System.out.println(board);
		service.remove(board);
		
		return "redirect:/board/tables";
	}
	
	
	
}
