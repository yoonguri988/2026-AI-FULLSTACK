<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./inc/header.jsp" %>
<%
	String email = (String) session.getAttribute("email");
	
	if(email == null){
		out.println("<script> alert('로그인 상태가 아니라 마이페이지에 접근이 불가합니다.'); location.href='login.jsp';</script>");
	}
%>
	<div class="container card my-5">
		<h3 class="card-header">마이페이지</h3>
		<div class="my-3">
		<table class="table table-striped table-bordered table-hover">
			<caption>USER 정보 목록</caption>
			<!-- 표 제목 -->
			<thead></thead>
			<tbody id="ResultBody">
			<%
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/mbasic";
				String user = "root";
				String pass = "1234";
				String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null; 
				
				try{
					conn = DriverManager.getConnection(url, user, pass);
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, email);
					
					rs = pstmt.executeQuery();
					StringBuffer sb = new StringBuffer();
					while(rs.next()){
						sb.append("<tr><th scope='row'>닉네임</th>");
						sb.append(String.format("<td>%s</td>",rs.getString("NICKNAME")));
						sb.append("</tr>");
						sb.append("<tr><th scope='row'>이메일</th>");
						sb.append(String.format("<td>%s</td>",rs.getString("EMAIL")));
						sb.append("</tr>");
						sb.append("<tr><th scope='row'>휴대폰</th>");
						sb.append(String.format("<td>%s</td>",rs.getString("MOBILE")));
						sb.append("</tr>");
						sb.append("<tr><th scope='row'>가입일</th>");
						sb.append(String.format("<td>%s</td>",rs.getString("UDATE")));
						sb.append("</tr>");
						sb.append("<tr><th scope='row'>가입IP</th>");
						sb.append(String.format("<td>%s</td>",rs.getString("BIP")));
						sb.append("</tr>");
					}
					out.println(sb.toString());
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					rs.close();
					pstmt.close();
					conn.close(); 
				}
			%>
			</tbody>
		</table>
		</div>
		<div class="my-3 text-end">
			<a href="./user_edit.jsp" class="btn btn-outline-primary" title="회원정보수정">수정</a> 
			<a href="./user_delete.jsp" class="btn btn-outline-danger" title="회원정보탈퇴">탈퇴</a> 
		</div>
	</div>
<%@include file="./inc/footer.jsp" %>