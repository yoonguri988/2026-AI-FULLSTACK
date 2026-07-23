package com.the703.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.the703.api.ApiOpenAi; 

@Controller
@RequestMapping("/api/util")
public class ApiUtilController {
 
	 
	/////////4. openai 
	// http://localhost:8080/api/util/openai
	@Autowired ApiOpenAi apiOpenAi;
	
	@GetMapping("/openai")  public String openai_get() {  return "util/openai";}

	@PostMapping(value="/openai" , produces = MediaType.APPLICATION_JSON_VALUE)  
	@ResponseBody
	public String openai_post( @RequestBody String content ) {  return apiOpenAi.getAIResponse(content);}

}


 

