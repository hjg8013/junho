package org.jun.service;

import java.util.ArrayList;

import org.jun.domain.BoardDTO;
import org.jun.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper bmapper;
	
	//게시판 글쓰기 설계된것을 구현
	public void write(BoardDTO board) {
		bmapper.write(board);
	}
	//게시판 목록리스트 설계된것을 구현
	public ArrayList<BoardDTO> tables() {
		return bmapper.tables();
	}
	//게시판 목록리스트에서 제목을 클릭했을때 내용이 나오는 상세페이지 구현
	public BoardDTO datail(BoardDTO board) {
		return bmapper.datail(board);
	}
	
}
