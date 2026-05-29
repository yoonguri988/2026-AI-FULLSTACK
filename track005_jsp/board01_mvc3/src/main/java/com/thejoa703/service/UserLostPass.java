package com.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dao.UserDAO;
import com.thejoa703.dto.UserDTO;

public class UserLostPass implements UserService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String newBpass = request.getParameter("newBpass");
		
		UserDAO dao = new UserDAO();
		int rs = dao.updatePassByEmail(new UserDTO(newBpass, email));
		
		request.setAttribute("rs", rs);
	}

}
