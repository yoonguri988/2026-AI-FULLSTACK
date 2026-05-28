<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
	<div class="container card my-5">
		<h3 class="card-header">회원정보 수정</h3>
		<div class="alert alert-secondary my-3">
			<form action="UEdit" method="post" onsubmit="return checkForm()">
				<div class="mb-3 mt-3">
					<label for="email" class="form-label">이메일</label> 
					<input type="email" value="${user.email }" 
						class="form-control" id="email" placeholder="이메일을 입력하세요"
						name="email" readonly>
				</div>
				<div class="mb-3">
					<label for="nickname" class="form-label">닉네임</label> 
					<input type="text" value="${user.nickname }" 
						class="form-control" id="nickname" placeholder="이름을 입력하세요"
						name="nickname">
				</div>
				<div class="mb-3">
					<label for="bpass" class="form-label">비밀번호</label> 
					<input type="password" class="form-control" id="bpass"
						placeholder="비밀번호를 입력하세요" name="bpass">
				</div>
				<div class="mb-3">
					<label for="mobile" class="form-label">휴대폰번호</label> 
					<input type="text" value="${user.mobile }" 
						class="form-control" id="mobile"
						placeholder="휴대폰번호를 입력하세요" name="mobile">
				</div>
				<div class="mb-3">
					<button type="reset" class="btn btn-outline-primary" onclick="cancel()" title="회원정보수정취소">취소</button>
					<button type="submit" class="btn btn-primary" title="회원정보수정">수정</button>
				</div>
			</form>
		</div>
	</div>
	<script>
	function checkForm() {
		const email = document.getElementById("email");
		const nickname = document.getElementById("nickname");
		const bpass = document.getElementById("bpass");
		const mobile = document.getElementById("mobile");

		if(email.value.trim() == "") {
			alert("빈칸입니다. \n확인해주세요");
			email.focus();
			return false;
		}
		if(nickname.value.trim() == ""){
			alert("빈칸입니다. \n확인해주세요");
			nickname.focus();
			return false;
		}
		if(bpass.value.trim() == "") {
			alert("빈칸입니다. \n확인해주세요");
			bpass.focus();
			return false;
		}
		if(moblie.value.trim() == "") {
			alert("빈칸입니다. \n확인해주세요");
			moblie.focus();
			return false;
		}
		return true; 
	}
</script>
<%@include file="../inc/footer.jsp" %>