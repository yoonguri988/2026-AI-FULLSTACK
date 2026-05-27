<%@page import="java.net.InetAddress"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String bname = request.getParameter("bname");
	String bpass = request.getParameter("bpass");
	String btitle = request.getParameter("btitle");
	String bcontent = request.getParameter("bcontent");
	String bip = InetAddress.getLocalHost().getHostAddress();
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/mbasic";
	Connection conn = null;
	PreparedStatement pstmt = null;
	try{
		conn = DriverManager.getConnection(url, "root", "1234");
		String sql = "INSERT INTO MVCBOARD1 (bname, bpass, btitle, bcontent, bip) VALUES (?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bname);
		pstmt.setString(2, bpass);
		pstmt.setString(3, btitle);
		pstmt.setString(4, bcontent);
		pstmt.setString(5, bip);
		
		int res = pstmt.executeUpdate();
		if(res > 0){
			out.println("<script> alert('글쓰기 성공!'); location.href='list.jsp';</script>");
		} else {
			out.println("<script> alert('글쓰기 실패, 관리자에게 문의 바랍니다.'); location.href='list.jsp';</script>");
		}
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		pstmt.close();
		conn.close();	
	}
%>