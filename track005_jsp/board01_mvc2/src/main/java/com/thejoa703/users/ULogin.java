package com.thejoa703.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ULogin
 */
@WebServlet("/ULogin")
public class ULogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ULogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿠키 배열에서 이메일 관련 쿠키 찾기
	    String savedEmail = "";
	    boolean isChecked = false;
	    
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie c : cookies) {
	            if ("userEmail".equals(c.getName())) {
	                savedEmail = c.getValue();
	                isChecked = true; // 쿠키가 존재하면 체크박스도 체크 상태로 변경
	                break;
	            }
	        }
	    }
		
	    request.setAttribute("savedEmail", savedEmail);
	    request.setAttribute("isChecked", isChecked);
		request.getRequestDispatcher("users/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String bpass = request.getParameter("bpass");

		// Cookie
		String emailSave = request.getParameter("emailSave");

		UserDAO dao = new UserDAO();
		UserDTO dto = dao.loginUser(new UserDTO(bpass, email));
		boolean isLogin = false;
		if (dto != null) {
			session.setAttribute("email", dto.getEmail());
			session.setAttribute("nickname", dto.getNickname());
			isLogin = true;
		}

		if (isLogin) {
			// 쿠키 객체 생성
			Cookie emailCookie = new Cookie("userEmail", email);
			emailCookie.setPath("/"); // 애플리케이션 전역에서 쿠키 접근 가능하도록 설정

			if ("save".equals(emailSave)) {
				// 체크박스가 켜져 있으면: 쿠키 유효기간 설정
				emailCookie.setMaxAge(60);
				response.addCookie(emailCookie);
			} else {
				// 체크박스가 꺼져 있으면: 기존 쿠키를 즉시 삭제 (유효기간을 0으로)
				emailCookie.setMaxAge(0);
				response.addCookie(emailCookie);
			}

			out.println("<script> alert('로그인 성공!'); location.href='UMypage';</script>");
		} else {
			out.println("<script> alert('로그인 실패, 아이디나 비밀번호를 확인해주세요.'); location.href='ULogin';</script>");
		}
	}
}
