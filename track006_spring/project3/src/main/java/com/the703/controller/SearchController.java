package com.the703.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.the703.dto.UserDto;
import com.the703.service.UserService;

@Controller
public class SearchController {
	@Autowired UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/doubleEmail", method = RequestMethod.GET)
	public Map<String, Object> doubleEmail(@RequestParam("email") String email) {
		Map<String, Object> res = new HashMap<>();
		
		UserDto dto = userService.findByEmail(email);
		
		if(dto != null) res.put("exists", true);
		else res.put("exists", false);
		
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/doubleNick", method = RequestMethod.GET)
	public Map<String, Object> doubleNick(@RequestParam("nickname") String nickname) {
		Map<String, Object> res = new HashMap<>();
		
		UserDto dto = userService.findByNickname(nickname);
		
		if(dto != null) res.put("exists", true);
		else res.put("exists", false);
		
		return res;
	}
}
