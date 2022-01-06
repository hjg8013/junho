package org.jun.controller;

import org.jun.domain.BoardDTO;
import org.jun.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board")
public class BoardController {
	
	// @Autowired 사용하면private BoardService service = new BoardService();
	@Autowired
	private BoardService service;
	
	@GetMapping("write")
	public void write() {
		System.out.println("write");
	}
	@PostMapping("write")
	public void writePost(BoardDTO board) {
		
		service.write(board);
		System.out.println("postwrite" + board);
		
	}
	@GetMapping("tables")
	public void tables() {
		System.out.println("tables");
	}
}
