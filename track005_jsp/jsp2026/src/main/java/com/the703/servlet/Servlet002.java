package com.the703.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/life")
public class Servlet002 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	int initCnt = 1;    // init 맨 처음
	int doGetCnt = 1;   // Thread get/post
	int destoryCnt = 1; // 맨 마지막
	
	
    public Servlet002() { super(); }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("............ #1. init() 첫 요청에만 호출 > "+ initCnt++ );
	}

	public void destroy() {
		System.out.println(" 파일 조금 수정하기 ctrl + s 저장");
		System.out.println("............ #3. destroy() 맨마지막에 호출 > "+ destoryCnt++ );
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("............ #2. get() 요청처리를 할때마다 호출 > "+ doGetCnt++ );
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
