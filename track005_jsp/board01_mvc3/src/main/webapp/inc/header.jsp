<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="${pageContext.request.contextPath}/css/board.css" rel="stylesheet">

</head>
<body>
	<!-- header -->
	<header>
		<h1 class="myhidden">글 게시판</h1>
		<div class="p-5 bg-primary text-white text-center myvisual">
			<h1>First Template</h1>
			<p>Lorem ipsum...</p>
		</div>
		<nav class="navbar navbar-expand-sm navbar-light bg-secondary">
			<h2 class="myhidden">주메뉴</h2>
			<div class="container-fluid">
				<a class="navbar-brand" href="index.jsp">Logo</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#mynavbar">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="mynavbar">
					<ul class="navbar-nav ms-auto">
						<c:if test="${empty sessionScope.email }">
							<li class="nav-item"><a class='nav-link' href='${pageContext.request.contextPath}/user/LoginView.do'>LOGIN</a></li>
							<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user/JoinView.do">JOIN</a></li>
						</c:if>
						<c:if test="${not empty sessionScope.email }">
							<li class="nav-item"><a class='nav-link' href='${pageContext.request.contextPath}/user/Logout.do'>LOGOUT</a></li>
							<li class="nav-item"><a class='nav-link'href='${pageContext.request.contextPath}/user/MypageView.do'>${sessionScope.nickname }</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<!-- header -->