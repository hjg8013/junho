package org.jun.service;

import java.util.ArrayList;

import org.jun.domain.ReplyDTO;
import org.jun.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper rmapper;
	
	//댓글 쓰기설계된것을 구현
	public int write(ReplyDTO rdto) {
		//insert 성공시 ReplyMapper.java로 부터1
		//insert 실패시 ReplyMapper.java로 부터0
		//값을 리턴받는다
		return rmapper.write(rdto);
	}
	//댓글 목록리스트 설계된것을 구현
	public ArrayList<ReplyDTO> list(int bno) {
		return rmapper.list(bno);
	}
}
