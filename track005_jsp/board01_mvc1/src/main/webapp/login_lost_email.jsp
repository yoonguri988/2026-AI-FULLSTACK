<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./inc/header.jsp" %>
	<div class="container card my-5">
		<h3 class="card-header">로그인</h3>
		<div class="alert alert-secondary my-3">
			<form action="login_lost_email_action.jsp" method="post" onsubmit="return checkForm()">
				<div class="mb-3 mt-3">
					<label for="nickname" class="form-label">닉네임</label> 
					<input
						type="text" class="form-control" id="nickname"
						placeholder="닉네임을 입력하세요" name="nickname">
				</div>
				<div class="mb-3">
					<label for="moblie" class="form-label">휴대폰번호</label> 
					<input
						type="text" class="form-control" id="moblie"
						placeholder="휴대폰번호를 입력하세요" name="moblie">
				</div>
				<div class="mb-3 text-end">
					<button type="submit" class="btn btn-primary" title="이메일찾기">이메일 찾기</button>
				</div>
			</form>
		</div>
	</div>
	<script>
	function checkForm() {
		const nickname = document.getElementById("nickname");
		const moblie = document.getElementById("moblie");
		
		if(nickname.value.trim() == "") {
			alert("빈칸입니다. \n확인해주세요")
			nickname.focus();
			return false;
		} 
		if(moblie.value.trim() == ""){
			alert("빈칸입니다. \n확인해주세요")
			moblie.focus();
			return false;
		} 
		return true;
	}
	</script>
<%@include file="./inc/footer.jsp" %>