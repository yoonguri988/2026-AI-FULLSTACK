package com.thejoa703.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dao.UserDAO;
import com.thejoa703.dto.UserDTO;

public class UserMypageView implements UserService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String) request.getAttribute("email");
		
		Map<String, String> userInfo = new HashMap<>();
		UserDAO dao = new UserDAO();
		UserDTO dto = dao.searchUser(new UserDTO("", email));
		
		userInfo.put("nicknameKr", "닉네임");
		userInfo.put("nickname", dto.getNickname());
		userInfo.put("emailKr", "이메일");
		userInfo.put("email", dto.getEmail());
		userInfo.put("mobileKr", "휴대폰");
		userInfo.put("mobile", dto.getMobile());
		userInfo.put("udateKr", "가입일");
		userInfo.put("udate", dto.getUdate());
		userInfo.put("bipKr", "가입IP");
		userInfo.put("bip", dto.getBip());
		
		request.setAttribute("user", userInfo);
	}

}
