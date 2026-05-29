package com.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dao.BoardDAO;
import com.thejoa703.dto.BoardDTO;

public class BoardEditView implements BoardService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));

		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.getBoard(bno);

		request.setAttribute("board", dto);
	}

}
