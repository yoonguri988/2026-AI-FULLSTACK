package com.thejoa703.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thejoa703.dao.UserDAO;
import com.thejoa703.dto.CheckMsgDTO;

public class UserChkNickname implements UserService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = request.getParameter("nickname");

		UserDAO dao = new UserDAO();
		boolean isExists = dao.isExistsUserByNickname(nickname);

		CheckMsgDTO dto = new CheckMsgDTO();

		if (isExists) {
			dto = new CheckMsgDTO(false, "중복된 닉네임이 존재합니다.\n입력한 닉네임을 변경해주세요");
		} else {
			dto = new CheckMsgDTO(true, "사용가능한 닉네임입니다.");
		}
		
		request.setAttribute("chkMsgDTO", dto);
	}

}
