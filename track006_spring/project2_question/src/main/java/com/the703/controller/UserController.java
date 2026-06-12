package com.the703.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.the703.dto.UserDto;
import com.the703.service.UserService;

@Controller
public class UserController {

	@Autowired  UserService service;
	
	@RequestMapping( "/" )
	public String index() {  return "redirect:/users/login"; }
	 
	///////////////////////////////////////
	@RequestMapping( value="/users/join" , method=RequestMethod.GET  )
	public String join() {  return "users/join"; }
	
	@RequestMapping(value="/users/join", method = RequestMethod.POST)
	public String join_post(UserDto dto, RedirectAttributes rttr) {
		String msg = "회원가입 실패";
		if(service.insert(dto) > 0) {msg = "회원가입 성공";}
		rttr.addFlashAttribute("msg", msg);
		return "redirect:users/login";
	}
	 
	@RequestMapping( value="/users/login" , method=RequestMethod.GET  )
	public String login() {  return "users/login"; }
	 
	@RequestMapping(value="/users/mypage", method = RequestMethod.GET)
	public String mypage(Principal principal, Model model) {
		model.addAttribute("email", principal.getName());
		model.addAttribute("dto", service.findByEmailUserInfo(principal.getName()));
		return "users/mypage"; 
	}
	
}
