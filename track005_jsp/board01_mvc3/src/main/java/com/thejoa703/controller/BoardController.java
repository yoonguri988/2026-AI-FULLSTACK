package com.thejoa703.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thejoa703.service.BoardDelete;
import com.thejoa703.service.BoardDetail;
import com.thejoa703.service.BoardEdit;
import com.thejoa703.service.BoardList;
import com.thejoa703.service.BoardSearch;
import com.thejoa703.service.BoardService;
import com.thejoa703.service.BoardEditView;
import com.thejoa703.service.BoardWrite;

/**
 * Servlet implementation class BoardController
 */
public class BoardController {
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String path = request.getServletPath(); // /[경로].do
		
		BoardService service = null;
		if(path.equals("/board/BList.do")) { // 보드 목록 조회 기능
			service = new BoardList();
			service.exec(request, response);

			request.getRequestDispatcher("/board/list.jsp").forward(request, response);
		} else if(path.equals("/board/BDetail.do")) { // 보드 상세 조회 기능
			service = new BoardDetail();
			service.exec(request, response);

			request.getRequestDispatcher("/board/detail.jsp").forward(request, response);
		} else if(path.equals("/board/BWriteView.do")) { // 글 쓰기 페이지 뷰
			
			request.getRequestDispatcher("/board/write.jsp").forward(request, response);
		} else if(path.equals("/board/BWrite.do")) { // 글 쓰기 기능
			service = new BoardWrite();
			service.exec(request, response);
			
			int rs = (int) request.getAttribute("rs");
			if (rs > 0) {
				out.println("<script>");
				out.println("alert('글쓰기 성공!');");
				out.println("location.href='BList.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('글쓰기 실패, 관리자에게 문의 바랍니다.');");
				out.println("location.href='BList.do'");
				out.println("</script>");
			}
		} else if(path.equals("/board/BEditView.do")) { // 글 수정 페이지 뷰
			service = new BoardEditView();
			service.exec(request, response);
			
			request.getRequestDispatcher("/board/edit.jsp").forward(request, response);
		} else if(path.equals("/board/BEdit.do")) { // 글 수정 기능
			service = new BoardEdit();
			service.exec(request, response);
			
			int bno = Integer.parseInt(request.getParameter("bno"));
			
			int rs = (int) request.getAttribute("rs");
			if (rs > 0) {
				out.println("<script>");
				out.println("alert('글쓰기 수정 성공!');");
				out.println("location.href='BDetail.do?bno=" + bno + "';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('실패, 관리자에게 문의 바랍니다!');");
				out.println("location.href='BEditView.do?bno=" + bno + "';");
				out.println("</script>");
			}
		} else if(path.equals("/board/BDeleteView.do")) { // 글 삭제 페이지 뷰
			int bno = Integer.parseInt(request.getParameter("bno"));
			request.setAttribute("bno", bno);
			
			request.getRequestDispatcher("/board/delete.jsp").forward(request, response);
		} else if(path.equals("/board/BDelete.do")) { // 글 삭제 기능
			service = new BoardDelete();
			service.exec(request, response);
			
			int bno = Integer.parseInt(request.getParameter("bno"));
			
			int rs = (int) request.getAttribute("rs");
			if (rs > 0) {
				out.println("<script>");
				out.println("alert('글 삭제 성공!');");
				out.println("location.href='BList.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('실패, 잘못된 비밀번호 입력입니다.');");
				out.println("location.href='BDeleteView.do?bno=" + bno + "';");
				out.println("</script>");
			}
		} else if(path.equals("/board/BSearch.do")) { // 글 목록 검색 기능
			service = new BoardSearch();
			service.exec(request, response);
			
			Gson gson = new Gson();
			String jsonResponse = gson.toJson(request.getAttribute("boardList"));
			out.println(jsonResponse);
		} 
	}

}
