package com.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dao.BoardDAO;
import com.thejoa703.dto.BoardDTO;

public class BoardDelete implements BoardService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		String bpass = request.getParameter("bpass");
		
		BoardDAO dao = new BoardDAO();
		int rs = dao.deleteBoard(new BoardDTO(bno, bpass));
		
		request.setAttribute("rs", rs);
	}

}
