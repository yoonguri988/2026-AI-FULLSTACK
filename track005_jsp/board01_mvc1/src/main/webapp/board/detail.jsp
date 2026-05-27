<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<%
				int bno = Integer.parseInt(request.getParameter("bno"));
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/mbasic";
				Connection conn = null;
				PreparedStatement pstmt1 = null; PreparedStatement pstmt2 = null;
				int rs1 = 0; ResultSet rs2 = null;
				
				String btitle = "";
				String bname = "";
				String bcontent = "";
				int bhit = 0;
				
				try{
					conn = DriverManager.getConnection(url, "root", "1234");
					String sql = "UPDATE MVCBOARD1 SET BHIT = BHIT+1 WHERE BNO=? ";
					pstmt1 = conn.prepareStatement(sql);
					pstmt1.setInt(1, bno);
					rs1 = pstmt1.executeUpdate();
					
					String sql2 = "SELECT * FROM MVCBOARD1 WHERE BNO=?";
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setInt(1, bno);
					rs2 = pstmt2.executeQuery(); 
					
					StringBuffer sb = new StringBuffer();
					while(rs2.next()){
						btitle = rs2.getString("BTITLE");
						bname = rs2.getString("BNAME");
						bcontent = rs2.getString("BCONTENT");
						bhit = rs2.getInt("BHIT");
					}
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					rs2.close();
					pstmt2.close();
					pstmt1.close();
					conn.close();
				}
%>
	<div class="container card my-5">
		<h3 class="card-header">QNA 상세보기</h3>
		<div class="alert alert-secondary my-3">
			<div class="mb-3 mt-3">
				<label for="bhit" class="form-label">조회수</label> 
				<input type="number" value="<%=bhit %>" 
				 class="form-control" id="bhit" name="bhit" readonly>
			</div>
			<div class="mb-3">
				<label for="bname" class="form-label">이름</label> 
				<input type="text" value="<%=bname %>"
					class="form-control" id="bname" name="bname" readonly>
			</div>
			<!-- <div class="mb-3">
				<label for="bpass" class="form-label">비밀번호</label> 
				<input type="password" class="form-control" id="bpass"
					placeholder="비밀번호를 입력하세요" name="bpass">
			</div> -->
			<div class="mb-3">
				<label for="btitle" class="form-label">제목</label> 
				<input type="text" value="<%=btitle %>"
				  class="form-control" id="btitle" name="btitle" readonly>
			</div>
			<div class="mb-3">
				<label for="bcontent" class="form-label">내용</label>
				<textarea class="form-control" id="bcontent" name="bcontent" readonly><%=bcontent %></textarea>
			</div>
			<div class="my-3 text-end">
				<a href="./edit.jsp?bno=<%=bno %>" class="btn btn-outline-primary" title="글수정">수정</a> 
				<a href="./delete.jsp?bno=<%=bno %>" class="btn btn-outline-success" title="글삭제">삭제</a>
				<a href="./list.jsp" class="btn btn-primary" title="목록보러가기">목록</a>
			</div>
		</div>
	</div>
<%@include file="../inc/footer.jsp" %>