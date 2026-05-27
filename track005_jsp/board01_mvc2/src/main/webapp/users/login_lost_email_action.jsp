<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String nickname = request.getParameter("nickname");
	String moblie = request.getParameter("moblie");
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/mbasic";
	String user = "root";
	String pass = "1234";
	String sql = "SELECT * FROM USERS WHERE NICKNAME = ? AND MOBILE = ?";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try{
		conn = DriverManager.getConnection(url, user, pass);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nickname);
		pstmt.setString(2, moblie);
		
		rs = pstmt.executeQuery();
		
		String email = null;
		while(rs.next()){
			email = rs.getString("email");
		}
		if(email != null){
			out.println("<script> alert('이메일 조회 성공!\\n이메일: "+email+"'); location.href='login.jsp';</script>");
		} else {
			out.println("<script> alert('해당 정보에 맞는 이메일이 존재하지 않습니다.'); location.href='join.jsp';</script>");
		}
	} catch(Exception e){
		e.printStackTrace();
	} finally {
		rs.close();
		pstmt.close();
		conn.close();
	}
%>