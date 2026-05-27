<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
	<div class="container card my-5">
		<h3 class="card-header">회원가입</h3>
		<div class="alert alert-secondary my-3">
			<form action="join_action.jsp" method="post" onsubmit="return checkForm()">
				<div class="mb-3 mt-3">
					<label for="nickname" class="form-label">닉네임</label> 
					<div class="input-group">
					<input
						type="text" class="form-control col-sm-10" id="nickname"
						placeholder="닉네임을 입력하세요" name="nickname" onkeyup="ipNickKeyup()">
					<button type="button" class="btn btn-primary col-sm-2" id="distinctNickBtn" onclick="distinctNick()">중복확인</button>
					</div>
				</div>
				<div class="mb-3">
					<label for="bpass" class="form-label">비밀번호</label> 
					<input
						type="password" class="form-control" id="bpass"
						placeholder="비밀번호를 입력하세요" name="bpass">
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">이메일</label>
					<div class="input-group"> 
					<input
						type="email" class="form-control" id="email"
						placeholder="이메일을 입력하세요" name="email"  onkeyup="ipEmailKeyup()">
					<button type="button" class="btn btn-primary col-sm-2" id="distinctEmailBtn" onclick="distinctEmail()">중복확인</button>
					</div>
				</div>
				<div class="mb-3">
					<label for="mobile" class="form-label">휴대폰</label> 
					<input
						type="text" class="form-control" id="mobile"
						placeholder="휴대폰번호를 입력하세요" name="mobile">
				</div>
				<div class="mb-3">
					<button type="reset" class="btn btn-outline-primary"  title="회원가입취소">취소</button>
					<button type="submit" class="btn btn-primary" title="회원가입">회원가입</button>
				</div>
			</form>
		</div>
	</div>
	<script>
	let isDistNickname = false;
	let isDistEmail = false;
		function checkForm() {
			const nickname = document.getElementById("nickname");
			const bpass = document.getElementById("bpass");
			const email = document.getElementById("email");
			const mobile = document.getElementById("mobile");
			
			if(nickname.value.trim() == "") {
				alert("빈칸입니다. \n확인해주세요");
				nickname.focus();
				return false;
			} 
			if(bpass.value.trim() == ""){
				alert("빈칸입니다. \n확인해주세요");
				bpass.focus();
				return false;
			} 
			if(email.value.trim() == "") {
				alert("빈칸입니다. \n확인해주세요");
				email.focus();
				return false;
			} 
			if(mobile.value.trim() == "") {
				alert("빈칸입니다. \n확인해주세요");
				mobile.focus();
				return false;
			} 
			if(!isDistNickname) {
				alert("닉네임 중복 확인해주세요");
				nickname.focus();
				return false;
			}
			if(!isDistEmail) {
				alert("이메일 중복 확인해주세요");
				email.focus();
				return false;
			}
			return true;
		}
		
		function distinctNick(){
			//alert("중복확인");
			const nickname = document.getElementById("nickname").value;
			fetch("./user_chk_nickname.jsp?nickname="+nickname)
				.then((res)=>{
				  if(!res.ok) throw Error("에러 코드: "+res.status);
				  return res.json();
				})
				.then((data)=>{
					isDistNickname = data[0].isDistNickname;
					alert(data[0].message);
				})
		}
		
		function ipNickKeyup() {
			isDistNickname = false;
		}
		
		function distinctEmail(){
			//alert("중복확인");
			const email = document.getElementById("email").value;
			fetch("./user_chk_email.jsp?email="+email)
				.then((res)=>{
				  if(!res.ok) throw Error("에러 코드: "+res.status);
				  return res.json();
				})
				.then((data)=>{
					isDistEmail = data[0].isDistEmail;
					alert(data[0].message);
				})
		}
		function ipEmailKeyup() {
			isDistEmail = false;
		}
	</script>
	
<%@include file="../inc/footer.jsp"%>