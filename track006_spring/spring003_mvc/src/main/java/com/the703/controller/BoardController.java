package com.the703.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	@RequestMapping("/board/list.do")
	public String list(Model model) {
		return "/board/list";
	}
	
	@RequestMapping("/board/write.do")
	public String write(Model model) {
		return "/board/write";
	}
	
	@RequestMapping("/board/detail.do")
	public String detail(Model model) {
		return "/board/detail";
	}
	
	@RequestMapping("/board/edit.do")
	public String edit(Model model) {
		return "/board/edit";
	}
	
	@RequestMapping("/board/delete.do")
	public String delete(Model model) {
		return "/board/delete";
	}
}
