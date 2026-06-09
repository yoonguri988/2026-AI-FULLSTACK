<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TITLE</title>
<!-- Latest compiled and minified CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
<link href="./css/board.css" rel="stylesheet">
</head>
<body>
	<div class="container my-3">
		<h3>업로드 결과</h3>
		<div class="mb-3">
			<label for="name" class="form-label">작성자</label>
			<input type="text" id="name" name="name" value="${name }" class="form-control" readonly>
		</div>
		<div class="mb-3">
			<label for="file" class="form-label">파일업로드</label> 
			<img class="w-70" src="${pageContext.request.contextPath}/upload/${file}" alt="${file}" />
		</div>
	</div>
</body>
</html>