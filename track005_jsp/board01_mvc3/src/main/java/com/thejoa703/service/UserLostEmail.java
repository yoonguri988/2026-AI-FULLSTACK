package com.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dao.UserDAO;
import com.thejoa703.dto.UserDTO;

public class UserLostEmail implements UserService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String moblie = request.getParameter("moblie");
			
		UserDAO dao = new UserDAO();
		UserDTO dto = dao.findUserByNickAndMoblie(new UserDTO(nickname, moblie, ""));
		
		request.setAttribute("dto", dto);
	}

}
