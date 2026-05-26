<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

String nickname = request.getParameter("nickname");

Class.forName("com.mysql.cj.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/mbasic";
String user = "root";
String pass = "1234";
String sql = "SELECT * FROM USERS WHERE NICKNAME=?";

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try{
	conn = DriverManager.getConnection(url, user, pass);
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, nickname);
	
	rs = pstmt.executeQuery();
	
	boolean isDistinct = false;
	while(rs.next()){
		isDistinct = true;
	}
	
	StringBuffer sb = new StringBuffer();
	sb.append("[{");
	if(isDistinct){
		sb.append("\"isDistNickname\": false,");
		sb.append("\"message\": \"중복된 닉네임이 존재합니다.\\n입력한 닉네임을 변경해주세요\"");/* 		out.println("<script> alert('중복된 닉네임이 존재합니다.\\n입력한 닉네임을 변경해주세요');</script>"); */
	} else {
		sb.append("\"isDistNickname\": true,");
		sb.append("\"message\": \"사용가능한 닉네임입니다.\"");/* 		out.println("<script> alert('사용가능한 닉네임입니다.');</script>"); */
	}
	sb.append("}]");
	out.println(sb.toString());
} catch(Exception e){
	e.printStackTrace();
} finally {
	rs.close();
	pstmt.close();
	conn.close();
}
%>