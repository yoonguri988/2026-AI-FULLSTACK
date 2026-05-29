package com.thejoa703.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.thejoa703.dto.UserDTO;
import com.thejoa703.service.UserChkEmail;
import com.thejoa703.service.UserChkNickname;
import com.thejoa703.service.UserDelete;
import com.thejoa703.service.UserEdit;
import com.thejoa703.service.UserEditView;
import com.thejoa703.service.UserJoin;
import com.thejoa703.service.UserLogin;
import com.thejoa703.service.UserLostEmail;
import com.thejoa703.service.UserLostPass;
import com.thejoa703.service.UserMypageView;
import com.thejoa703.service.UserSearch;
import com.thejoa703.service.UserService;

/**
 * Servlet implementation class UserController
 */
//@WebServlet("*.do") // 개발버전
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String path = request.getServletPath(); // /[경로].do
		
		UserService service = null;
		
		if(path.equals("/user/LoginView.do")) { // 로그인 페이지 뷰 
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
		    
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
		} else if(path.equals("/user/Login.do")) { //로그인 기능
			service = new UserLogin();
			service.exec(request, response);

			// Cookie
			String emailSave = request.getParameter("emailSave");
			UserDTO user = (UserDTO) request.getAttribute("user");
			boolean isLogin = false;
			if (user != null) {
				session.setAttribute("email", user.getEmail());
				session.setAttribute("nickname", user.getNickname());
				isLogin = true;
			}
			
			if (isLogin) {
				// 쿠키 객체 생성
				Cookie emailCookie = new Cookie("userEmail", user.getEmail());
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

				out.println("<script> alert('로그인 성공!'); location.href='MypageView.do';</script>");
			} else {
				out.println("<script> alert('로그인 실패, 아이디나 비밀번호를 확인해주세요.'); location.href='LoginView.do';</script>");
			}
		} else if(path.equals("/user/Logout.do")) { //로그인 기능
			session.invalidate();

			out.println("<script> alert('로그아웃'); location.href='../board/BList.do';</script>");
		} else if(path.equals("/user/JoinView.do")) { // 회원 가입 페이지 뷰
			
			request.getRequestDispatcher("/user/join.jsp").forward(request, response);
		} else if(path.equals("/user/Join.do")) { // 회원 가입 기능
			service = new UserJoin();
			service.exec(request, response);
			
			int rs = (int) request.getAttribute("rs");
			if (rs > 0) {
				out.println("<script>");
				out.println("alert('회원가입 성공!');");
				out.println("location.href='LoginView.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원가입 실패, 관리자에게 문의해주세요.');");
				out.println("location.href='JoinView.do'");
				out.println("</script>");
			}
		} else if(path.equals("/user/ChkEmail.do")) { // 이메일 중복 확인 기능
			service = new UserChkEmail();
			service.exec(request, response);
			
			Gson gson = new Gson();
			out.println(gson.toJson(request.getAttribute("chkMsgDTO")));
		} else if(path.equals("/user/ChkNickname.do")) { // 닉네임 중복 확인 기능
			service = new UserChkNickname();
			service.exec(request, response);
			
			Gson gson = new Gson();
			out.println(gson.toJson(request.getAttribute("chkMsgDTO")));
		}else if(path.equals("/user/MypageView.do")) { // 마이 페이지 뷰
			String email = (String) session.getAttribute("email");
			request.setAttribute("email", email);
			
			service = new UserMypageView();
			service.exec(request, response);
			
			request.getRequestDispatcher("/user/mypage.jsp").forward(request, response);
		} else if(path.equals("/user/EditView.do")) {
			String email = (String) session.getAttribute("email");
			request.setAttribute("email", email);
			
			service = new UserEditView();
			service.exec(request, response);
			
			request.getRequestDispatcher("/user/user_edit.jsp").forward(request, response);
		} else if(path.equals("/user/Edit.do")) {
			service = new UserEdit();
			service.exec(request, response);
			
			int rs = (int) request.getAttribute("rs");
			if (rs > 0) {
				out.println("<script> alert('회원 정보가 수정 되었습니다.'); location.href='MypageView.do';</script>");
			} else {
				out.println("<script> alert('회원 정보 수정 실패, 비밀번호를 확인하세요'); location.href='EditView.do';</script>");
			}
		} else if(path.equals("/user/DeleteView.do")) {
			request.getRequestDispatcher("/user/user_delete.jsp").forward(request, response);
		} else if(path.equals("/user/Delete.do")) {
			String email = (String) session.getAttribute("email");
			request.setAttribute("email", email);
			
			service = new UserDelete();
			service.exec(request, response);
			
			int rs = (int) request.getAttribute("rs"); 
			if (rs > 0) {
				session.invalidate();
				out.println("<script> alert('탈퇴처리 되었습니다.'); location.href='BList.do';</script>");
			} else {
				out.println("<script> alert('회원 탈퇴 실패, 잘못된 비밀번호 입력입니다.'); location.href='DeleteView.do'</script>");
			}
			
		} else if(path.equals("/user/LostEmailView.do")) {
			
			request.getRequestDispatcher("/user/login_lost_email.jsp").forward(request, response);
		} else if(path.equals("/user/LostEmail.do")) {
			service = new UserLostEmail();
			service.exec(request, response);
			
			UserDTO dto = (UserDTO) request.getAttribute("dto");
			if (dto != null) {
				out.println("<script> alert('이메일 조회 성공!\\n이메일: " + dto.getEmail() + "'); location.href='LoginView.do';</script>");
			} else {
				out.println("<script> alert('해당 정보에 맞는 이메일이 존재하지 않습니다.'); location.href='JoinView.do';</script>");
			}
			
		} else if(path.equals("/user/LostPassView.do")) {
			
			request.getRequestDispatcher("/user/login_lost_pass.jsp").forward(request, response);
		} else if(path.equals("/user/LostPass.do")) {
			service = new UserSearch();
			service.exec(request, response);
			
			UserDTO dto = (UserDTO) request.getAttribute("dto");
			if (dto == null) {
				out.println("<script> alert('해당 이메일이 존재하지 않습니다.'); location.href='LoginView.do';</script>");
			} else {
				service = new UserLostPass();
				service.exec(request, response);
				
				int rs = (int) request.getAttribute("rs");
				if (rs > 0) {
					out.println("<script> alert('비밀번호가 재설정 되었습니다.'); location.href='LoginView.do';</script>");
				} else {
					out.println("<script> alert('비밀번호 설정 실패, 관리자에게 문의하세요'); location.href='LoginView.do';</script>");
				}
			}
			
		} 
	}

}
