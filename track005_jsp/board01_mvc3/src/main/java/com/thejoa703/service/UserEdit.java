package com.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dao.UserDAO;
import com.thejoa703.dto.UserDTO;

public class UserEdit implements UserService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String bpass = request.getParameter("bpass");
		String nickname = request.getParameter("nickname");
		String mobile = request.getParameter("mobile");

		UserDAO dao = new UserDAO();
		int rs = dao.updateUser(new UserDTO(nickname, bpass, email, mobile));
		
		request.setAttribute("rs", rs);
	}

}
