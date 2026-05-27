<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String email = request.getParameter("email");
	String newBpass = request.getParameter("newBpass");
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/mbasic";
	String user = "root";
	String pass = "1234";
	String sql1 = "SELECT * FROM USERS WHERE EMAIL = ?";
	String sql2 = "UPDATE USERS SET BPASS=? WHERE EMAIL=?";
	
	Connection conn = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs1 = null; int rs2 = 0;
	
	try{
		conn = DriverManager.getConnection(url, user, pass);
		pstmt1 = conn.prepareStatement(sql1);
		pstmt1.setString(1, email);
		
		rs1 = pstmt1.executeQuery();
		
		String info = null;
		while(rs1.next()){
			info = rs1.getString("email");
		}
		
		if(info == null){
			out.println("<script> alert('해당 이메일이 존재하지 않습니다.'); location.href='login.jsp';</script>");
		}
		
		pstmt2 = conn.prepareStatement(sql2);
		pstmt2.setString(1, newBpass);
		pstmt2.setString(2, email);
		
		rs2 = pstmt2.executeUpdate();
		if(rs2 > 0){
			out.println("<script> alert('비밀번호가 재설정 되었습니다.'); location.href='login.jsp';</script>");
		}else {
			out.println("<script> alert('비밀번호 설정 실패, 관리자에게 문의하세요'); location.href='login.jsp';</script>");
		}
	} catch(Exception e){
		e.printStackTrace();
	} finally {
		rs1.close();
		pstmt1.close();
		pstmt2.close();
		conn.close();
	}
%>