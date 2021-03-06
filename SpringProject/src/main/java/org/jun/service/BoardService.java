package org.jun.service;

import java.util.ArrayList;

import org.jun.domain.AttachFileDTO;
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
	//게시판 페이징의 데이터 건수
	public int getTotalCount(Criteria cri);
	//게시판 상세 페이지에 파일 업로드된 이미지 출력
	public ArrayList<AttachFileDTO> fileList(int bno);
}
