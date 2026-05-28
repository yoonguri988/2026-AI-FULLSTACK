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
    public ULostPass() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		
		String url = "jdbc:mysql://localhost:3306/mbasic";
		String user = "root";
		String pass = "1234";
		String sql1 = "SELECT * FROM USERS WHERE EMAIL = ?";
		String sql2 = "UPDATE USERS SET BPASS=? WHERE EMAIL=?";
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, email);
			
			ResultSet rs1 = pstmt1.executeQuery();
			
			String info = null;
			while(rs1.next()){
				info = rs1.getString("email");
			}
			
			if(info == null){
				out.println("<script> alert('해당 이메일이 존재하지 않습니다.'); location.href='ULogin';</script>");
			}
			
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, newBpass);
			pstmt2.setString(2, email);
			
			int rs2 = pstmt2.executeUpdate();
			if(rs2 > 0){
				out.println("<script> alert('비밀번호가 재설정 되었습니다.'); location.href='ULogin';</script>");
			}else {
				out.println("<script> alert('비밀번호 설정 실패, 관리자에게 문의하세요'); location.href='ULogin';</script>");
			}
			if(rs1 != null) rs1.close();
			if(pstmt1 != null) pstmt1.close();
			if(pstmt2 != null) pstmt2.close();
			if(conn != null) conn.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
