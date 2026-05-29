package com.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dao.UserDAO;
import com.thejoa703.dto.UserDTO;

public class UserLogin implements UserService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String bpass = request.getParameter("bpass");

		UserDAO dao = new UserDAO();
		UserDTO dto = dao.loginUser(new UserDTO(bpass, email));

		request.setAttribute("user", dto);
	}

}
