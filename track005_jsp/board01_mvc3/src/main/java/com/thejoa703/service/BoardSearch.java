package com.thejoa703.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dao.BoardDAO;
import com.thejoa703.dto.BoardDTO;

public class BoardSearch implements BoardService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");

		BoardDAO dao = new BoardDAO();
		List<BoardDTO> boardList = dao.getBoards(search);
		
		request.setAttribute("boardList", boardList);
	}
}
