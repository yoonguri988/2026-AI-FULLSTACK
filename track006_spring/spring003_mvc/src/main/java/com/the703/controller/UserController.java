package com.the703.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.the703.dto.UserInfoDto;
import com.the703.service.UserInfoService;

@Controller
public class UserController {
	@Autowired UserInfoService service;
	
	@RequestMapping(value="/users/login.do", method=RequestMethod.GET)
	public String login() {
		return "users/login";
	}
	
	@RequestMapping(value="/users/login.do", method=RequestMethod.POST)
	public String login_post(UserInfoDto dto, HttpSession session, RedirectAttributes rttr) {
		String result = "로그인 실패";
		UserInfoDto isExists = service.selectOneByDto(dto);
		String url = "redirect:/board/list.do";
		if(isExists != null) { 
			result = "로그인 성공"; 
			session.setAttribute("email", isExists.getEmail());
			url = "redirect:/board/list.do";
		}
		rttr.addFlashAttribute("result", result);
		return url;
	}
	
	@RequestMapping(value="/users/logout.do", method=RequestMethod.GET)
	public String logout_post(HttpSession session) {
		session.invalidate();
		return "redirect:/board/list.do";
	}
	
	@RequestMapping(value="/users/join.do", method=RequestMethod.GET)
	public String join() {
		return "users/join";
	}
	
	@RequestMapping(value="/users/join.do", method=RequestMethod.POST)
	public String join_post(UserInfoDto dto, RedirectAttributes rttr) {
		String result = "회원가입실패";
		String url = "redirect:/users/join.do";
		if(service.insert(dto) > 0) { 
			result = "회원가입성공";
			url = "redirect:/users/login.do";
		}
		rttr.addFlashAttribute("result",result);
		return url;
	}
	
	@RequestMapping(value="users/mypage.do", method = RequestMethod.GET)
	public String mypage(HttpSession session, Model model) {
		String email = (String) session.getAttribute("email");
		model.addAttribute("user", service.selectOneByEmail(email));
		return "users/mypage";
	}
	

}
