<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./inc/header.jsp" %>
	<div class="container card my-5">
		<h3 class="card-header">글 삭제</h3>
		<form action="./delete_action.jsp?bno=<%=request.getParameter("bno") %>" method="post" onsubmit="return checkForm()">
			<div class="mb-3 mt-3">
				<label for="bpass" class="form-label">비밀번호</label> 
				<input type="password" class="form-control" id="bpass"
					placeholder="비밀번호를 입력하세요" name="bpass">
			</div>
			<div class="mb-3">
				<button type="submit" class="btn btn-primary">확인</button>
				<button type="reset"  class="btn btn-outline-primary" >취소</button>
			</div>
		</form>
	</div>
<%@include file="./inc/footer.jsp" %>
