package com.the703.service;

import java.util.List;

import com.the703.dto.BoardDto;

public interface BoardService {
	//■1. 전체리스트 
	public List<BoardDto> selectAll();
	//■2. 글쓰기 기능 
	public int insert(BoardDto dto);
	//■3. 글상세보기 - 해당 글 / 조회수 올리기
	public BoardDto detail(int bno);
	//■4. 글수정폼 경로 - 해당글
	public BoardDto editView(int bno);
	//■4. 글수정 기능 - 비밀번호가 맞는지 확인 후 글수정 
	public int edit(BoardDto dto);
	//■5. 글삭제 기능 - 비밀번호가 맞는지 확인 후 글삭제
	public int delete(BoardDto dto);
}
