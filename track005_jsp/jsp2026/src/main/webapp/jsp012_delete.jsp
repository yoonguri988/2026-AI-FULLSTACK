<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//1. 데이터 넘겨 받기
request.setCharacterEncoding("UTF-8");
int ono = Integer.parseInt(request.getParameter("ono"));
	
//2. sql - delete from milk_order where ono=?
Connection conn = null;  PreparedStatement pstmt = null;
String url = "jdbc:mysql://localhost:3306/mbasic";
try {
	//2-1. 드라이버 연동
	Class.forName("com.mysql.cj.jdbc.Driver");
	//2-2. jdbc 연동
	conn = DriverManager.getConnection( url, "root", "1234");
	//2-3. sql 처리
	pstmt = conn.prepareStatement("delete from milk_order where ono=?");
	pstmt.setInt(1, ono);
	
	int result = pstmt.executeUpdate();
	StringBuffer sb = new StringBuffer();
	sb.append("<script>");
	if(result > 0) {
		sb.append("alert('우유데이터 삭제 성공!');");
		sb.append("location.href='milk.jsp';");
		//response.sendRedirect("milk.jsp");
	}else {
		sb.append("alert('삭제 실패! 관리자에게 문의 바랍니다!');");
		sb.append("location.href='milk.jsp';");
	}
	sb.append("</script>");
	out.println(sb.toString());
} catch (Exception e){ e.printStackTrace(); }
  finally { conn.close(); pstmt.close(); } //2-4. jdbc 끊기
%>