package com.the703.controller;


import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.the703.dto.BoardDto;
import com.the703.service.BoardService;
import com.the703.util.PagingUtil;

@Controller
public class BoardController {
	@Autowired BoardService service;
	
//	@RequestMapping("/")
//	public String index() {
//		// 구동 시작점 - db 컨트롤러:  index.jsp
//		return "redirect:/board/list.do";
//	}
	
	//■1. 전체 리스트
	@RequestMapping("/board/list.do")
	public String list(Model model, @RequestParam(value="pstartno", defaultValue = "1") int pstarValue) {
		model.addAttribute("paging", new PagingUtil(service.selectCnt(), pstarValue)); // /*service 전체 갯수*/
		model.addAttribute("list", service.select10(pstarValue)); /* list10 */
		return "board/list";
	}
	//■2. 글쓰기 폼 경로
	@RequestMapping(value="/board/write.do", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	//■2. 글쓰기 기능
	@RequestMapping(value = "/board/write.do",method = RequestMethod.POST)
	public String write_post( 
			@RequestParam("file") MultipartFile file,
			BoardDto dto,
			RedirectAttributes rttr) throws IllegalStateException, IOException {
		String result = "글쓰기 실패";
		
		// 파일 업로드 bfile
//		if(!bfile.isEmpty()) {
//			String uploadPath = "C:/file/";
//			File dest = new File(uploadPath+bfile.getOriginalFilename());
//			bfile.transferTo(dest);
//			dto.setBfileName(bfile.getOriginalFilename());
//		}
//		
		if(service.insert(dto, file) > 0) { result = "글쓰기 성공"; }
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
	   @RequestMapping( value= "/board/edit.do" , method = RequestMethod.POST)
	   public String edit_post(
	         BoardDto dto,
	         @RequestParam("file")  MultipartFile file, 
	         RedirectAttributes rttr) { 
	      // 알림창
	      String result = "비밀번호 확인!";
	      if( service.edit(dto , file) > 0 ) {  result = "수정성공";  }
	      rttr.addFlashAttribute("result", result);
	      
	      return "redirect:/board/detail.do?bno=" + dto.getBno();
	   } 
	//■5. 글 삭제 폼 경로
	@RequestMapping(value="/board/delete.do", method = RequestMethod.GET)
	public String delete() {
		return "board/delete";
	}
	
	//■5. 글 삭제 기능
	@RequestMapping(value="/board/delete.do", method = RequestMethod.POST)
	public String delete_post(BoardDto dto, Model model, RedirectAttributes rttr) {
		String result = "비밀번호 확인!";
		if(service.delete(dto) > 0) { result = "글삭제 성공"; }
		rttr.addFlashAttribute("result",result);
		return "redirect:/board/detail.do?bno="+dto.getBno();
	}
}
