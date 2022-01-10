package org.jun.service;

import java.util.ArrayList;

import org.jun.domain.BoardDTO;
import org.jun.domain.Criteria;

public interface BoardService {
	//게시판 글쓰기
	public void write(BoardDTO board);
	//게시판 목록 리스트
	public ArrayList<BoardDTO> tables(Criteria cri);
	//게시판 상세페이지
	public BoardDTO datail(BoardDTO board);
	//게시판 수정페이지
	public void modify(BoardDTO board);
	//게시판 삭제페이지
	public void remove(BoardDTO board);
}
