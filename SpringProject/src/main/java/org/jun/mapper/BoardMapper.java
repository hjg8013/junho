package org.jun.mapper;

import java.util.ArrayList;

import org.jun.domain.BoardDTO;

public interface BoardMapper{
	//게시판 글쓰기와 관련이 되어 있는 DB작업에 대한 설계
	public void write(BoardDTO board);
	public ArrayList<BoardDTO> tables();
	public BoardDTO datail(BoardDTO board);
}
