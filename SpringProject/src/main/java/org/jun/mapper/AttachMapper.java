package org.jun.mapper;

import java.util.ArrayList;

import org.jun.domain.AttachFileDTO;

public interface AttachMapper {
	
	//게시판 글쓰기 할때 파일과 관ㄹㄴ되어 있는 attach테이블 insert
	public void insert(AttachFileDTO board);
	//게시판 상세페이지에 업로드된 이미지를 뿌리기위한 list
	public ArrayList<AttachFileDTO> fileList(int bno);
}
