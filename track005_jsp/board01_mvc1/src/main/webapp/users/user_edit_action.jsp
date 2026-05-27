<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String email = request.getParameter("email");
	String bpass = request.getParameter("bpass");
	String nickname = request.getParameter("nickname");
	String mobile = request.getParameter("mobile");
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/mbasic";
	String user = "root";
	String pass = "1234";
	String sql = "UPDATE USERS SET NICKNAME=?, MOBILE=?"
			    +" WHERE EMAIL=? AND BPASS=?";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	int rs = 0;
	try{
		conn = DriverManager.getConnection(url, user, pass);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nickname);
		pstmt.setString(2, mobile);
		pstmt.setString(3, email);
		pstmt.setString(4, bpass);
		
		rs = pstmt.executeUpdate();
		if(rs > 0){
			out.println("<script> alert('회원 정보가 수정 되었습니다.'); location.href='mypage.jsp';</script>");
		}else {
			out.println("<script> alert('회원 정보 수정 실패, 비밀번호를 확인하세요'); location.href='user_edit.jsp';</script>");
		}
	} catch(Exception e){
		e.printStackTrace();
	} finally {
		pstmt.close();
		conn.close();
	}
%>