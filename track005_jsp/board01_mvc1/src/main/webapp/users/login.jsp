<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    // 쿠키 배열에서 이메일 관련 쿠키 찾기
    String savedEmail = "";
    boolean isChecked = false;
    
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("userEmail".equals(c.getName())) {
                savedEmail = c.getValue();
                isChecked = true; // 쿠키가 존재하면 체크박스도 체크 상태로 변경
                break;
            }
        }
    }
%>
<%@include file="../inc/header.jsp" %>
	<div class="container card my-5">
		<h3 class="card-header">로그인</h3>
		<div class="alert alert-secondary my-3">
			<form action="login_action.jsp" method="post" onsubmit="return checkForm()">
				<div class="mb-3 mt-3">
					<label for="email" class="form-label">이메일</label> 
					<input
						type="email" class="form-control" id="email"
						placeholder="이메일을 입력하세요" name="email" value="<%=savedEmail %>">
				</div>
				<div class="mb-3">
					<label for="bpass" class="form-label">비밀번호</label> 
					<input
						type="password" class="form-control" id="bpass"
						placeholder="비밀번호를 입력하세요" name="bpass">
				</div>
				<div class="mb-3 form-check">
				  <input class="form-check-input" type="checkbox" id="emailSave" name="emailSave" value="save" <%= isChecked? "checked": "" %>>
				  <label class="form-check-label">이메일 저장하기</label>
				</div>
				<div class="mb-3 text-center">
					<a href="login_lost_email.jsp">이메일 찾기</a>
					<a href="login_lost_pass.jsp">비밀번호 찾기</a>
				</div>
				<div class="mb-3 text-end">
					<button type="submit" class="btn btn-primary" title="로그인">로그인</button>
				</div>
			</form>
		</div>
	</div>
	<script>
	function checkForm() {
		const email = document.getElementById("email");
		const bpass = document.getElementById("bpass");
		
		if(email.value.trim() == "") {
			alert("빈칸입니다. \n확인해주세요")
			bname.focus();
			return false;
		} 
		if(bpass.value.trim() == ""){
			alert("빈칸입니다. \n확인해주세요")
			bpass.focus();
			return false;
		} 
		return true;
	}
	</script>
<%@include file="../inc/footer.jsp" %>