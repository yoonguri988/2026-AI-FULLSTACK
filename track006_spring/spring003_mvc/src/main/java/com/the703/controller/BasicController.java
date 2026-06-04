package com.the703.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//<mvc:annotation-driven />
@Controller
public class BasicController {
	@RequestMapping("/basic.do") // http://localhost:8080/spring003_mvc/basic.do
	public String basic(Model model) {
		// ## model
		model.addAttribute("result", "Hello");
		return "basic";
	}
}
