package com.the703.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.the703.dto.UserDto;
import com.the703.service.UserService;

@Controller
public class UserController_V1 {
	@Autowired UserService service;
	
	//로그인 폼
//	@RequestMapping(value="/users/login.do", method=RequestMethod.GET)
//	public String login() {
//		return "users/login";
//	}

	//로그인 기능
//	@RequestMapping(value="/users/login.do", method=RequestMethod.POST)
//	public String login_post(UserDto dto, HttpSession session, RedirectAttributes rttr) {
//		String result = "로그인 실패";
//		UserDto isExists = service.selectOneByDto(dto);
//		String url = "redirect:/board/list.do";
//		if(isExists != null) { 
//			result = "로그인 성공"; 
//			session.setAttribute("email", isExists.getEmail());
//			url = "redirect:/board/list.do";
//		}
//		rttr.addFlashAttribute("result", result);
//		return url;
//	}
	
	//로그아웃 기능
//	@RequestMapping(value="/users/logout.do", method=RequestMethod.GET)
//	public String logout_post(HttpSession session) {
//		session.invalidate();
//		return "redirect:/board/list.do";
//	}
	
	//회원가입 폼
//	@RequestMapping(value="/users/join.do", method=RequestMethod.GET)
//	public String join() {
//		return "users/join";
//	}
	
	//회원가입 기능
//	@RequestMapping(value="/users/join.do", method=RequestMethod.POST)
//	public String join_post(UserDto dto, RedirectAttributes rttr) {
//		String result = "회원가입실패";
//		String url = "redirect:/users/join.do";
//		if(service.insert(dto) > 0) { 
//			result = "회원가입성공";
//			url = "redirect:/users/login.do";
//		}
//		rttr.addFlashAttribute("result",result);
//		return url;
//	}
	
	//마이페이지
//	@RequestMapping(value="/users/mypage.do", method = RequestMethod.GET)
//	public String mypage(HttpSession session, Model model) {
//		String email = (String) session.getAttribute("email");
//		model.addAttribute("user", service.selectOneByEmail(email));
//		return "users/mypage";
//	}
	
	//아이디 중복 검사 기능
//	public Map<String, Boolean> checkEmail(String email) {
//		boolean isDup = service.isDupEmail(email);
//		return Map.of("duplicate", isDup);
//	}

}
