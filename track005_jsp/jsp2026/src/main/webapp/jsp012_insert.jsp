<%@page import="java.net.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. utf-8 설정
	request.setCharacterEncoding("UTF-8");

	//2. request.getParameter() 이용해서 데이터 받기
	String oname = request.getParameter("oname");
	int onum = Integer.parseInt(request.getParameter("onum"));
	String ip = InetAddress.getLocalHost().getHostAddress();
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mbasic",
				"root",
				"1234");
		PreparedStatement pstmt = conn.prepareStatement("insert into milk_order (oname, onum, oip) values (?,?,?)");
		pstmt.setString(1, oname);
		pstmt.setInt(2, onum);
		pstmt.setString(3, ip);
		
		int result = pstmt.executeUpdate();
		
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		if(result > 0) {
			sb.append("alert('우유데이터 삽입 성공!');");
			sb.append("location.href='milk.jsp';");
			//response.sendRedirect("milk.jsp");
		}else {
			sb.append("alert('관리자에게 문의 바랍니다!');");
			sb.append("location.href='milk.jsp';");
		}
		sb.append("</script>");
		out.println(sb.toString());
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
	} catch (Exception e){ e.printStackTrace(); }
%>