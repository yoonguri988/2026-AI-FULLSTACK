<%@page import="java.sql.*"%>
<%@page import="java.net.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String bno = request.getParameter("bno");
	String bpass = request.getParameter("bpass");
	String btitle = request.getParameter("btitle");
	String bcontent = request.getParameter("bcontent");
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/mbasic";
	Connection conn = null;
	PreparedStatement pstmt = null;
	try{
		conn = DriverManager.getConnection(url, "root", "1234");
		String sql = "UPDATE MVCBOARD1 SET BTITLE=?, BCONTENT=? WHERE BNO = ? AND BPASS = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, btitle);
		pstmt.setString(2, bcontent);
		pstmt.setString(3, bno);
		pstmt.setString(4, bpass);
		
		int res = pstmt.executeUpdate();
		if(res > 0){
			//1. http-equiv="refresh" jsp
			//2. sendRedirect
			//3.dispatcher spring
			
			String script = String.format("<script> alert('성공'); location.href='detail.jsp?bno=%s';</script>", bno);
			out.println(script);
		} else {
			out.println("<script> alert('실패, 관리자에게 문의 바랍니다.'); location.href='edit.jsp?bno="+bno+"';</script>");
		}
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		pstmt.close();
		conn.close();	
	}
%>