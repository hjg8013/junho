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
	//댓글수정을 하기 위해 댓글내용 가져오기
	public ReplyDTO detail(int rno);
	//댓글 수정
	public int update(ReplyDTO rdto);
	//댓글 삭제
	public int remove(ReplyDTO rdto);
}
