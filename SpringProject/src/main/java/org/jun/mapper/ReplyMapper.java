package org.jun.mapper;

import java.util.ArrayList;

import org.jun.domain.ReplyDTO;

public interface ReplyMapper {
	//댓글쓰기 설계
	//insert 성공시 ReplyMapper.xml로 부터1
	//insert 실패시 ReplyMapper.xml로 부터0
	//값을 리턴받는다
	public int write(ReplyDTO rdto);
	//댓글 목록리스트를 설계
	public ArrayList<ReplyDTO> list(int bno);
	
	public ReplyDTO detail(int rno);
}
