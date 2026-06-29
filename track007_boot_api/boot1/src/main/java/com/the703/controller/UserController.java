package com.the703.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.the703.service.AppUserService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired AppUserService service;
	
	@GetMapping("/join") 
	public String join_get() {
		return "users/join";
	}
	
	@GetMapping("/login") 
	public String login_get() {
		return "users/login";
	}
	
	@GetMapping("/mypage") 
	public String mypage_get(Model model) {
		
		return "users/mypage";
	}
	
	@GetMapping("/update") 
	public String update_get() {
		return "users/update";
	}
	
	@GetMapping("/delete") 
	public String delete_get() {
		return "users/delete";
	}
	
	@GetMapping("/fail") 
	public String fail_get(Model model) {
		model.addAttribute("errorMessage", "로그인 실패: 아이디 또는 비밀번호를 확인해주세요");
		return "users/login";
	}
	
}
