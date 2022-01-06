package org.jun.service;

import org.jun.domain.BoardDTO;
import org.jun.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper bmapper;
	
	public void write(BoardDTO board) {
		bmapper.write(board);
	}
	
}
