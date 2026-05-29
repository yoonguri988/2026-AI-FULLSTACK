package com.thejoa703.service;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dao.BoardDAO;
import com.thejoa703.dto.BoardDTO;

public class BoardWrite implements BoardService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bname = request.getParameter("bname");
		String bpass = request.getParameter("bpass");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bip = InetAddress.getLocalHost().getHostAddress();

		BoardDAO dao = new BoardDAO();
		int rs = dao.insertBoard(new BoardDTO(bname, bpass, btitle, bcontent, bip));
		
		request.setAttribute("rs", rs);
	}

}
