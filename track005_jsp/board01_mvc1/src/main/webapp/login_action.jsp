<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String email = request.getParameter("email");
	String bpass = request.getParameter("bpass");

	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/mbasic";
	String user = "root";
	String pass = "1234";
	String sql = "SELECT * FROM USERS WHERE EMAIL = ? AND BPASS = ?";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try{
		conn = DriverManager.getConnection(url, user, pass);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, bpass);
		
		rs = pstmt.executeQuery();
		boolean isLogin = false;
		while(rs.next()){
			session.setAttribute("email", rs.getString("EMAIL"));
			session.setAttribute("nickname", rs.getString("NICKNAME"));
			isLogin = true;
		}
		
		if(isLogin){
			out.println("<script> alert('로그인 성공!'); location.href='mypage.jsp';</script>");
		} else {
			out.println("<script> alert('로그인 실패, 아이디나 비밀번호를 확인해주세요.'); location.href='login.jsp';</script>");
		}
	} catch(Exception e){
		e.printStackTrace();
	} finally {
		rs.close();
		pstmt.close();
		conn.close();
	}
%>