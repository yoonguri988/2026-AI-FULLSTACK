<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String bpass = request.getParameter("bpass");
	String email = (String) session.getAttribute("email");
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/mbasic";
	String user = "root";
	String pass = "1234";
	String sql = "DELETE FROM USERS WHERE EMAIL = ? AND BPASS = ?";
		
	Connection conn = null;
	PreparedStatement pstmt = null;
	int rs = 0;
	
	try{
		conn = DriverManager.getConnection(url, user, pass);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, bpass);
		
		rs = pstmt.executeUpdate();
		if(rs > 0){
			session.invalidate();
			out.println("<script> alert('탈퇴처리 되었습니다.'); location.href='list.jsp';</script>");
		} else {
			out.println("<script> alert('회원 탈퇴 실패, 잘못된 비밀번호 입력입니다.'); location.href='user_delete.jsp'</script>");
		}
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		pstmt.close();
		conn.close();	
	}
%>