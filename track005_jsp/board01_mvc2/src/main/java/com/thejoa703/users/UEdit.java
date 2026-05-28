package com.thejoa703.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UEdit
 */
@WebServlet("/UEdit")
public class UEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		String email = (String) session.getAttribute("email");

		UserDAO dao = new UserDAO();
		UserDTO dto = dao.searchUser(new UserDTO("", email));

		request.setAttribute("user", dto);
		request.getRequestDispatcher("users/user_edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String bpass = request.getParameter("bpass");
		String nickname = request.getParameter("nickname");
		String mobile = request.getParameter("mobile");

		UserDAO dao = new UserDAO();
		int rs = dao.updateUser(new UserDTO(nickname, bpass, email, mobile));
		if (rs > 0) {
			out.println("<script> alert('회원 정보가 수정 되었습니다.'); location.href='UMypage';</script>");
		} else {
			out.println("<script> alert('회원 정보 수정 실패, 비밀번호를 확인하세요'); location.href='UEdit';</script>");
		}
	}
}
