package com.thejoa703.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class UMypage
 */
@WebServlet("/UMypage")
public class UMypage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UMypage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		String email = (String) session.getAttribute("email");
		
		if(email == null){
			out.println("<script> alert('로그인 상태가 아니라 마이페이지에 접근이 불가합니다.'); location.href='login.jsp';</script>");
		} else {
			Map<String, String> userInfo = new HashMap<>();
			UserDAO dao = new UserDAO();
			UserDTO dto = dao.searchUser(new UserDTO("", email));
			
			userInfo.put("nicknameKr", "닉네임");
			userInfo.put("nickname", dto.getNickname());
			userInfo.put("emailKr", "이메일");
			userInfo.put("email", dto.getEmail());
			userInfo.put("mobileKr", "휴대폰");
			userInfo.put("mobile", dto.getMobile());
			userInfo.put("udateKr", "가입일");
			userInfo.put("udate", dto.getUdate());
			userInfo.put("bipKr", "가입IP");
			userInfo.put("bip", dto.getBip());
			
			request.setAttribute("user", userInfo);
		}
		
		
		request.getRequestDispatcher("/users/mypage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
