<%@page import="java.sql.*"%>
<%@page import="java.net.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String bno = request.getParameter("bno");
	String bpass = request.getParameter("bpass");
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/mbasic";
	Connection conn = null;
	PreparedStatement pstmt = null;
	try{
		conn = DriverManager.getConnection(url, "root", "1234");
		String sql = "DELETE FROM MVCBOARD1 WHERE BNO = ? AND BPASS = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bno);
		pstmt.setString(2, bpass);
		
		int res = pstmt.executeUpdate();
		if(res > 0){
			out.println("<script> alert('성공'); location.href='list.jsp';</script>");
		} else {
			out.println("<script> alert('실패, 잘못된 비밀번호 입력입니다.'); location.href='delete.jsp?bno="+bno+"';</script>");
		}
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		pstmt.close();
		conn.close();	
	}
%>