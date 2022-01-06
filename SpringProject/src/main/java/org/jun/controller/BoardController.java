package org.jun.controller;

import org.jun.domain.BoardDTO;
import org.jun.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public void tables(Model model) {
		System.out.println("tables"+service.tables());
		model.addAttribute("tables",service.tables());
	}
	//게시판 목록 리스트에서 제목으 클릭하면
	@GetMapping("datail")
	public void datail(BoardDTO board,Model model) {
		System.out.println("datail");
		
		model.addAttribute("datail",service.datail(board));
	}
	
	
}
