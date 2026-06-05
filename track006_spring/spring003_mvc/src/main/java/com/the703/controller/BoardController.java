package com.the703.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.the703.dto.BoardDto;
import com.the703.service.BoardService;

@Controller
public class BoardController {
	@Autowired BoardService service;
	
	@RequestMapping("/")
	public String index() {
		// 구동 시작점 - db 컨트롤러:  index.jsp
		return "redirect:/board/list.do";
	}
	
	//■1. 전체 리스트
	@RequestMapping("/board/list.do")
	public String list(Model model) {
		model.addAttribute("list", service.selectAll());
		return "board/list";
	}
	//■2. 글쓰기 폼 경로
	@RequestMapping(value="/board/write.do", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	//■2. 글쓰기 기능
	@RequestMapping(value = "/board/write.do",method = RequestMethod.POST)
	public String write_post(BoardDto dto , RedirectAttributes rttr) {
		String result = "글쓰기 실패";
		if(service.insert(dto) > 0) { result = "글쓰기 성공"; }
		rttr.addFlashAttribute("result",result); //Flash - 1번만 동작
		return "redirect:/board/list.do"; //response.sendRedirect + alert(x)
	}
	
	//■3. 글 상세 보기
	@RequestMapping("/board/detail.do")
	public String detail(int bno, Model model) {
		model.addAttribute("board",service.detail(bno));
		return "board/detail";
	}
	
	//■4. 글 수정 폼 경로
	@RequestMapping(value="/board/edit.do", method = RequestMethod.GET)
	public String edit(BoardDto dto, Model model) {
		model.addAttribute("board",service.editView(dto.getBno()));
		return "board/edit";
	}
	//■4. 글 수정 기능
	@RequestMapping(value="/board/edit.do", method = RequestMethod.POST)
	public String edit_post(BoardDto dto, Model model, RedirectAttributes rttr) {
		String result = "글수정 실패";
		
		if(service.edit(dto) > 0) { result = "글수정 성공"; }
		rttr.addFlashAttribute("result",result);
		return "redirect:/board/detail.do?bno="+dto.getBno();
	}
	//■5. 글 삭제 폼 경로
	@RequestMapping(value="/board/delete.do", method = RequestMethod.GET)
	public String delete() {
		return "board/delete";
	}
	
	//■5. 글 삭제 기능
	@RequestMapping(value="/board/delete.do", method = RequestMethod.POST)
	public String delete_post(BoardDto dto, Model model, RedirectAttributes rttr) {
		String result = "글삭제 실패";
		if(service.delete(dto) > 0) { result = "글삭제 성공"; }
		rttr.addFlashAttribute("result",result);
		return "redirect:/board/detail.do?bno="+dto.getBno();
	}
}
