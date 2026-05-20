<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
			int ono = Integer.parseInt(request.getParameter("ono"));
			int onum = Integer.parseInt(request.getParameter("onum"));
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/mbasic",
						"root",
						"1234");
				PreparedStatement pstmt = conn.prepareStatement("update milk_order set onum=? where ono=?");
				pstmt.setInt(1, onum);
				pstmt.setInt(2, ono);
				
				int result = pstmt.executeUpdate();
				
				StringBuffer sb = new StringBuffer();
					sb.append("<script>");
				if(result > 0) {
					sb.append("alert('우유데이터 수정 성공!');");
					sb.append("location.href='milk.jsp';");
					//response.sendRedirect("milk.jsp");
				}else {
					sb.append("alert('관리자에게 문의 바랍니다!');");
				}
				sb.append("</script>");
				out.println(sb.toString());
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			} catch (Exception e){ e.printStackTrace(); }
%>