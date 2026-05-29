package com.thejoa703.service;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dao.UserDAO;
import com.thejoa703.dto.UserDTO;

public class UserJoin implements UserService{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String bpass = request.getParameter("bpass");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String bip = InetAddress.getLocalHost().getHostAddress();
		
		UserDAO dao = new UserDAO();
		int rs = dao.regUser(new UserDTO(nickname,bpass,email,mobile,bip));
		
		request.setAttribute("rs", rs);
	}

}
