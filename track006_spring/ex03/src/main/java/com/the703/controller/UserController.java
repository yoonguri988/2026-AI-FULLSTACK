package com.the703.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.the703.dto.UserDto;
import com.the703.service.UserService;

@Controller
public class UserController {
	@Autowired UserService service;
	
	//로그인 폼
	@RequestMapping(value="/users/login.do", method=RequestMethod.GET)
	public String login() {
		return "users/login";
	}

	//로그인 기능
	@RequestMapping(value="/users/login.do", method=RequestMethod.POST)
	public String login_post(UserDto dto, HttpSession session, RedirectAttributes rttr) {
		return "";
	}
	
	//로그아웃 기능
	@RequestMapping(value="/users/logout.do", method=RequestMethod.GET)
	public String logout_post(HttpSession session) {
		session.invalidate();
		return "redirect:/board/list.do";
	}
	
	//회원가입 폼
	@RequestMapping(value="/users/join.do", method=RequestMethod.GET)
	public String join() {
		return "users/join";
	}
	
	//회원가입 기능
	@RequestMapping(value="/users/join.do", method=RequestMethod.POST)
	public String join_post(UserDto dto, RedirectAttributes rttr) {
		return "";
	}
	
	//마이페이지
	@RequestMapping(value="/users/mypage.do", method = RequestMethod.GET)
	public String mypage(HttpSession session, Model model) {
		return "";
	}
	
	//아이디 중복 검사 기능
	public String checkEmail(String email) {
		return "";
	}

}
