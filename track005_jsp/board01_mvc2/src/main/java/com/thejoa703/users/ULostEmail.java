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

/**
 * Servlet implementation class ULostEmail
 */
@WebServlet("/ULostEmail")
public class ULostEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ULostEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("users/login_lost_email.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String nickname = request.getParameter("nickname");
		String moblie = request.getParameter("moblie");
			
		UserDAO dao = new UserDAO();
		UserDTO dto = dao.findUserByNickAndMoblie(new UserDTO(nickname, moblie, ""));

		String email = dto.getEmail();

		if (email != null) {
			out.println("<script> alert('이메일 조회 성공!\\n이메일: " + email + "'); location.href='ULogin';</script>");
		} else {
			out.println("<script> alert('해당 정보에 맞는 이메일이 존재하지 않습니다.'); location.href='UJoin';</script>");
		}
	}
}
