<%@page import="java.sql.*"%>
<%@page import="java.net.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String nickname = request.getParameter("nickname");
	String bpass = request.getParameter("bpass");
	String email = request.getParameter("email");
	String mobile = request.getParameter("mobile");
	String bip = InetAddress.getLocalHost().getHostAddress();
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/mbasic";
	String user = "root";
	String pass = "1234";
	String sql = "INSERT INTO USERS (NICKNAME, BPASS, EMAIL, MOBILE, BIP) VALUES (?,?,?,?,?)";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	int rs = 0;
	
	try{
		conn = DriverManager.getConnection(url, user, pass);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nickname);
		pstmt.setString(2, bpass);
		pstmt.setString(3, email);
		pstmt.setString(4, mobile);
		pstmt.setString(5, bip);
		
		rs = pstmt.executeUpdate();
		
		if(rs > 0){
			out.println("<script> alert('회원가입 성공!'); location.href='login.jsp';</script>");
		} else {
			out.println("<script> alert('회원가입 실패, 관리자에게 문의해주세요.'); location.href='join.jsp';</script>");
		}
	} catch(Exception e){
		e.printStackTrace();
	} finally {
		pstmt.close();
		conn.close();
	}
%>