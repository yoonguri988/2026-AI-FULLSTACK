package com.thejoa703.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dao.UserDAO;
import com.thejoa703.dto.CheckMsgDTO;

public class UserChkEmail implements UserService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");

		UserDAO dao = new UserDAO();
		boolean isExists = dao.isExistsUserByEmail(email);

		CheckMsgDTO dto = new CheckMsgDTO();
		if (isExists) {
			dto = new CheckMsgDTO(false, "중복된 이메일이 존재합니다.\n입력한 이메일을 변경해주세요");
		} else {
			dto = new CheckMsgDTO(true, "사용가능한 이메일입니다.");
		}
		
		request.setAttribute("chkMsgDTO", dto);
	}

}
