<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container card  my-5">
		<h3 class="card-header"> LOGIN 유지하기 </h3>
		<pre class="my-3">
-- mysql> desc users;
-- +----------+--------------+------+-----+-------------------+-------------------+
-- | Field    | Type         | Null | Key | Default           | Extra             |
-- +----------+--------------+------+-----+-------------------+-------------------+
-- | uno      | int          | NO   | PRI | NULL              | auto_increment    |
-- | nickname | varchar(20)  | NO   |     | NULL              |                   |
-- | bpass    | varchar(50)  | NO   |     | NULL              |                   |
-- | email    | varchar(100) | NO   |     | NULL              |                   |
-- | mobile   | varchar(50)  | NO   |     | NULL              |                   |
-- | udate    | timestamp    | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
-- | bip      | varchar(50)  | NO   |     | NULL              |                   |
-- +----------+--------------+------+-----+-------------------+-------------------+
-- 7 rows in set (0.00 sec)

1.cmd 창에서 회원가입정보 입력
insert into (nickname, bpass, email, mobile, bip)
values      ('first','1111','first@gmail.com', '010-1111-1111', '127.0.0.1');
2. 처리부분: action="" / 주소표시창 노출 X / 데이터 보관 이름: email, bpass
3. 로그아웃 버튼 클릭시 로그아웃 처리
		</pre>
		<% String nickname = (String) session.getAttribute("nickname"); %>
		<% if(nickname != null) { %>
		<!-- 로그인 시에만 보이기 -->
		<pre class="alert alert-warning">
		반갑습니다. <%=nickname %> 님
		</pre>
		<a href="jsp016_logout.jsp">로그아웃</a>		
		<!-- 로그인 시에만 보이기 -->
		<% }  %>
		
		<p class="text-center"><img src="images/login.png" alt="login"/></p>  
		<form action="jsp016_result.jsp"  method="post" onsubmit="return login()">
			<div class="my-3">
				<label for="email"   class="form-label">Email : </label>
				<input type="email"  class="form-control"  id="email"  name="email" />      
			</div>
			<div class="my-3">
				<label for="bpass"       class="form-label">Password : </label>
				<input type="password"  class="form-control"  id="bpass"  name="bpass" />      
			</div>		
			<div class="my-3">
				<label for="remember"       class="form-label">Remember : </label>
				<input type="checkbox"   id="remember"  name="remember" />      
			</div>	
			<div class="my-3"> 
				<button type="submit"    title="login 하러가기"
				       class="btn btn-danger"> 로그인 </button> 
			</div>								
		</form>
		<script>
			function login(){
				let email = document.getElementById("email");
				let pass = document.getElementById("pass");
				if(email.value.trim() == ""){
					alert("이메일 칸을 확인해주세요!");
					email.focus();
					return false;
				} else if(pass.value.trim() == "") {
					alert("비밀번호 칸을 확인해주세요!")
					pass.focus();
					return false;
				}
				return true;
			}
		</script>
	</div>
</body>
</html> 
 

