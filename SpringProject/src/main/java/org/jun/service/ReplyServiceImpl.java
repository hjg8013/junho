package org.jun.service;

import org.jun.domain.ReplyDTO;
import org.jun.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper rmapper;
	
	//댓글 쓰기설계된것을 구현
	public void write(ReplyDTO rdto) {
		rmapper.write(rdto);
	}
}
