<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./inc/header.jsp" %>
<%
				int bno = Integer.parseInt(request.getParameter("bno"));
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/mbasic";
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String btitle = "";
				String bname = "";
				String bcontent = "";
				
				try{
					conn = DriverManager.getConnection(url, "root", "1234");
					String sql = "SELECT * FROM MVCBOARD1 WHERE BNO=? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, bno);
					rs = pstmt.executeQuery(); 
					
					while(rs.next()){
						btitle = rs.getString("BTITLE");
						bname = rs.getString("BNAME");
						bcontent = rs.getString("BCONTENT");
					}
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					rs.close();
					pstmt.close();
					conn.close();
				}
%>

	<div class="container card my-5">
		<h3 class="card-header">글 수정</h3>
		<div class="alert alert-secondary my-3">
			<form action="./edit_action.jsp?bno=<%=bno %>" method="post" onsubmit="return checkForm()">
				<div class="mb-3 mt-3">
					<label for="bname" class="form-label">이름</label> 
					<input type="text" value="<%=bname %>" 
						class="form-control" id="bname" placeholder="이름을 입력하세요"
						name="bname" readonly>
				</div>
				<div class="mb-3">
					<label for="bpass" class="form-label">비밀번호</label> 
					<input type="password" class="form-control" id="bpass"
						placeholder="비밀번호를 입력하세요" name="bpass">
				</div>
				<div class="mb-3">
					<label for="btitle" class="form-label">제목</label> 
					<input type="text" value="<%=btitle %>" 
						class="form-control" id="btitle"
						placeholder="제목을 입력하세요" name="btitle">
				</div>
				<div class="mb-3">
					<label for="bcontent" class="form-label">내용</label>
					<textarea class="form-control" id="bcontent"
						placeholder="내용을 입력하세요" name="bcontent"><%=bcontent %></textarea>
				</div>
				<div class="mb-3">
					<button type="reset" class="btn btn-outline-primary" onclick="cancel()" title="글취소">취소</button>
					<button type="button" class="btn btn-outline-success" onclick="list()" title="목록보러가기">목록</button>
					<button type="submit" class="btn btn-primary" title="수정">글쓰기</button>
				</div>
			</form>
		</div>
	</div>
	<script>
	function checkForm() {
		const bname = document.getElementById("bname");
		const bpass = document.getElementById("bpass");
		const btitle = document.getElementById("btitle");
		const bcontent = document.getElementById("bcontent");

		if(bname.value.trim() == "") {
			alert("빈칸입니다. \n확인해주세요");
			bname.focus();
			return false;
		} else if(bpass.value.trim() == ""){
			alert("빈칸입니다. \n확인해주세요");
			bpass.focus();
			return false;
		} else if(btitle.value.trim() == "") {
			alert("빈칸입니다. \n확인해주세요");
			btitle.focus();
			return false;
		} else if(bcontent.value.trim() == "") {
			alert("빈칸입니다. \n확인해주세요");
			bcontent.focus();
			return false;
		} else { return true; } 
	}

	function cancel() {
		//alert("취소");
	}

	function list() {
		//alert("목록화면으로 이동");
		location.href = "list.jsp";
	}
</script>
<%@include file="./inc/footer.jsp" %>