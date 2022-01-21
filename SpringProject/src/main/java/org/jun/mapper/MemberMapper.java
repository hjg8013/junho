package org.jun.mapper;

import org.jun.domain.MemberDTO;

public interface MemberMapper {
	
	public void insert(MemberDTO mdto);
	
	public MemberDTO login(MemberDTO mdto);
	
}
