<%@page import="com.thejoa703.users.CheckMsgDTO"%>
<%@page import="java.util.*"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

String email = request.getParameter("email");

Class.forName("com.mysql.cj.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/mbasic";
String user = "root";
String pass = "1234";
String sql = "SELECT * FROM USERS WHERE EMAIL=?";

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try{
	conn = DriverManager.getConnection(url, user, pass);
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, email);
	
	rs = pstmt.executeQuery();
	
	boolean isDistinct = false;
	while(rs.next()){
		isDistinct = true;
	}
	
	Gson gson = new Gson();
	List<CheckMsgDTO> list = new ArrayList<>();
	
	if(isDistinct){
		list.add(new CheckMsgDTO(false, "\"중복된 이메일이 존재합니다.\\n입력한 이메일을 변경해주세요\""));
	} else {
		list.add(new CheckMsgDTO(true, "\"사용가능한 이메일입니다.\""));
	}
	out.println(gson.toJson(list));
} catch(Exception e){
	e.printStackTrace();
} finally {
	rs.close();
	pstmt.close();
	conn.close();
}
%>