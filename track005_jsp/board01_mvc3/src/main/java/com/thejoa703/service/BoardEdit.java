package com.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dao.BoardDAO;
import com.thejoa703.dto.BoardDTO;

public class BoardEdit implements BoardService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		String bname = request.getParameter("bname");
		String bpass = request.getParameter("bpass");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");

		BoardDAO dao = new BoardDAO();
		int rs = dao.updateBoard(new BoardDTO(bno, bname, bpass, btitle, bcontent));
		
		request.setAttribute("rs", rs);
	}

}
