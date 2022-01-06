package org.jun.controller;

import org.jun.domain.BoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board")
public class BoardController {
	@GetMapping("write")
	public void write() {
		System.out.println("aaaa");
	}
	@PostMapping("write")
	public void writePost(BoardDTO board) {
		System.out.println("sdsdsd" + board);
		
	}
}
