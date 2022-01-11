package org.jun.mapper;

import java.util.ArrayList;

import org.jun.domain.BoardDTO;
import org.jun.domain.Criteria;

public interface BoardMapper{
	//게시판 글쓰기와 관련이 되어 있는 DB작업에 대한 설계
	public void write(BoardDTO board);
	
	public ArrayList<BoardDTO> tables(Criteria cri);
	
	public BoardDTO datail(BoardDTO board);
	
	public void cntupdate(BoardDTO board);
	
	public void modify(BoardDTO board);
	
	public void remove(BoardDTO board);
	
	public int getTotalCount();
	
}
