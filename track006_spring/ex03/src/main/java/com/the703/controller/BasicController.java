package com.the703.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//<mvc:annotation-driven />
@Controller
public class BasicController {
	@RequestMapping("/")
	public String index() {
		// 구동 시작점 - db 컨트롤러:  index.jsp
		//return "redirect:/board/list.do";
		return "index";  // /view/ + index / .jsp
	}
	
	@RequestMapping("/basic.do") // http://localhost:8080/spring003_mvc/basic.do
	public String basic(Model model) {
		// ## model
		model.addAttribute("result", "Hello");
		return "basic";
	}
}
