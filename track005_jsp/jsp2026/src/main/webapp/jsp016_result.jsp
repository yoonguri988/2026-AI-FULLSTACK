<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

String email = request.getParameter("email");
String bpass = request.getParameter("bpass");

String url = "jdbc:mysql://localhost:3306/mbasic";
String user = "root";
String pass = "1234";
String sql = "SELECT * FROM USERS WHERE EMAIL = ? AND BPASS = ?";

try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = DriverManager.getConnection(url,user,pass);
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1,email);
	pstmt.setString(2,bpass);
	
	ResultSet rs = pstmt.executeQuery();
	boolean loginChk = false;
	while(rs.next()){
		session.setAttribute("nickname", rs.getString("NICKNAME"));
		loginChk = true;
	}
	
	if(loginChk){
		out.println("<script>");
		out.println("alert('로그인 성공')");
		out.println("location.href='jsp016_login.jsp'");
		out.println("</script>");
	} else {
		out.println("<script>");
		out.println("alert('로그인 실패')");
		out.println("location.href='jsp016_login.jsp'");
		out.println("</script>");
	}
	
	if(rs != null) rs.close();
	if(pstmt != null) pstmt.close();
	if(conn != null) conn.close();
} catch(Exception e){
	e.printStackTrace();
}
%>