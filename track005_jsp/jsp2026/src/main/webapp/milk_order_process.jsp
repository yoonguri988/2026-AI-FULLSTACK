<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container card my-5">
		<h3 class="card-header"></h3>
		<%
			String oname = request.getParameter("oname");
			int onum = Integer.parseInt(request.getParameter("onum"));
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/mbasic",
						"root",
						"1234");
				PreparedStatement pstmt = conn.prepareStatement("insert into milk_order (oname, onum, oip) values (?,?,?)");
				pstmt.setString(1, oname);
				pstmt.setInt(2, onum);
				pstmt.setString(3, "198.160.0.1");
				
				int result = pstmt.executeUpdate();
				
				String referer = request.getHeader("Referer");
				if(result > 0) {

					if (referer != null) {
					    response.sendRedirect(referer); // 이전 페이지로 리다이렉트
					} else {
					    response.sendRedirect("default_page.jsp"); // 혹시 이전 페이지 정보가 없다면 이동할 기본 페이지
					}
					return;
				}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			} catch (Exception e){ e.printStackTrace(); }
		%>
	</div>
</body>
</html>