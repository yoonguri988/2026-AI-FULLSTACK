<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
			int ono = Integer.parseInt(request.getParameter("ono"));
			
			String url = "jdbc:mysql://localhost:3306/mbasic";
			Connection conn = null;  PreparedStatement pstmt = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection( url, "root", "1234");
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
					sb.append("alert('관리자에게 문의 바랍니다!');");
				}
				sb.append("</script>");
				out.println(sb.toString());
			} catch (Exception e){ e.printStackTrace(); }
			  finally { conn.close(); pstmt.close(); }
%>