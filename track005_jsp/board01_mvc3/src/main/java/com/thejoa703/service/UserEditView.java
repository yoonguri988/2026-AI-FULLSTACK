package com.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dao.UserDAO;
import com.thejoa703.dto.UserDTO;

public class UserEditView implements UserService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String) request.getAttribute("email");

		UserDAO dao = new UserDAO();
		UserDTO dto = dao.searchUser(new UserDTO("", email));

		request.setAttribute("user", dto);
	}

}
