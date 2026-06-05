package com.the703.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.the703.dao.BoardMapper;
import com.the703.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired BoardMapper dao;  //db 관련
	
	@Override
	public List<BoardDto> selectAll() {
		return dao.selectAll();
	}

	@Override
	public int insert(BoardDto dto) {
		try {
			dto.setBip(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) { e.printStackTrace(); }
		
		return dao.insert(dto);
	}

	@Override
	public BoardDto detail(int bno) {
		dao.updateBhitByBno(bno);
		return dao.select(bno);
	}

	@Override
	public BoardDto editView(int bno) {
		return dao.select(bno);
	}

	@Override
	public int edit(BoardDto dto) {
		//비번 맞으면 수정
		BoardDto isExistsDto = dao.selectOneByBpass(dto);
		if(isExistsDto != null) return dao.update(dto);
		else return 0;
	}

	@Override
	public int delete(BoardDto dto) {
		//비번 맞으면 삭제
		BoardDto isExistsDto = dao.selectOneByBpass(dto);
		if(isExistsDto != null) return dao.delete(dto.getBno());
		else return 0;
	}

}
