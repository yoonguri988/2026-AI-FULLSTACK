<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
	<div class="container card my-5">
		<h3 class="card-header">비밀번호 재설정</h3>
		<div class="alert alert-secondary my-3">
			<form action="LostPass.do" method="post" onsubmit="return checkForm()">
				<div class="mb-3 mt-3">
					<label for="email" class="form-label">이메일</label> 
					<input
						type="email" class="form-control" id="email"
						placeholder="이메일을 입력하세요" name="email">
				</div>
				<div class="mb-3">
					<label for="newBpass" class="form-label">비밀번호</label> 
					<input
						type="password" class="form-control" id="newBpass"
						placeholder="비밀번호를 입력하세요" name="newBpass">
				</div>
				<div class="mb-3">
					<label for="passChk" class="form-label">비밀번호 확인</label> 
					<input
						type="password" class="form-control" id="passChk"
						placeholder="비밀번호를 한번더 입력하세요" name="passChk">
				</div>
				<div class="mb-3 text-end">
					<button type="submit" class="btn btn-primary" title="비밀번호재설정">비밀번호 재설정</button>
				</div>
			</form>
		</div>
	</div>
	<script>
	function checkForm() {
		const email = document.getElementById("email");
		const newBpass = document.getElementById("newBpass");
		const passChk = document.getElementById("passChk");
		
		if(email.value.trim() == "") {
			alert("빈칸입니다. \n확인해주세요")
			email.focus();
			return false;
		} 
		if(newBpass.value.trim() == "") {
			alert("빈칸입니다. \n확인해주세요")
			newBpass.focus();
			return false;
		} 
		if(passChk.value.trim() == ""){
			alert("빈칸입니다. \n확인해주세요")
			passChk.focus();
			return false;
		} 
		if(passChk.value.trim() != newBpass.value.trim()){
			alert("비밀번호가 일치하지 않습니다. \n확인해주세요")
			return false;
		}
		return true;
	}
	</script>
<%@include file="../inc/footer.jsp" %>