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
 * Servlet implementation class ULostPass
 */
@WebServlet("/ULostPass")
public class ULostPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ULostPass() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("users/login_lost_pass.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String newBpass = request.getParameter("newBpass");
		
		UserDAO dao = new UserDAO();
		UserDTO dto = dao.searchUser(new UserDTO("", email));
		
		if (dto == null) {
			out.println("<script> alert('해당 이메일이 존재하지 않습니다.'); location.href='ULogin';</script>");
		}

		int rs = dao.updatePassByEmail(new UserDTO(newBpass, email));

		if (rs > 0) {
			out.println("<script> alert('비밀번호가 재설정 되었습니다.'); location.href='ULogin';</script>");
		} else {
			out.println("<script> alert('비밀번호 설정 실패, 관리자에게 문의하세요'); location.href='ULogin';</script>");
		}
	}
}
