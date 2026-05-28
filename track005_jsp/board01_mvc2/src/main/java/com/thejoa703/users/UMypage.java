package com.thejoa703.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

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
		}
		
		String url = "jdbc:mysql://localhost:3306/mbasic";
		String user = "root";
		String pass = "1234";
		String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
		
		Map<String, String> userInfo = new HashMap<>();
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			
			ResultSet rs = pstmt.executeQuery();
			Gson gson = new Gson();
			
			while(rs.next()){
				userInfo.put("nicknameKr", "닉네임");
				userInfo.put("nickname", rs.getString("NICKNAME"));
				userInfo.put("emailKr", "이메일");
				userInfo.put("email", rs.getString("EMAIL"));
				userInfo.put("mobileKr", "휴대폰");
				userInfo.put("mobile", rs.getString("MOBILE"));
				userInfo.put("udateKr", "가입일");
				userInfo.put("udate", rs.getString("UDATE"));
				userInfo.put("bipKr", "가입IP");
				userInfo.put("bip", rs.getString("BIP"));
			}
			
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("user", userInfo);
		
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
