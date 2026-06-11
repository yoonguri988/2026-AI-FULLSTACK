package com.the703.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.the703.service.UserService;

@Controller
public class UserController {
	@Autowired UserService service;
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/users/login";
	}
	
	@RequestMapping(value="/users/join", method = RequestMethod.GET)
	public String join() { return "users/join"; }
	
	@RequestMapping(value="/users/login", method = RequestMethod.GET)
	public String login() { return "users/login"; }
	
	@RequestMapping(value="/users/mypage", method = RequestMethod.GET)
	public String mypage(Principal principal, Model model) {
		model.addAttribute("email", principal.getName());
		return "users/mypage"; 
	}
}
