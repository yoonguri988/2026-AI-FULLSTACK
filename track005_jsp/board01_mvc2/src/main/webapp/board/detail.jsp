<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
	<div class="container card my-5">
		<h3 class="card-header">QNA 상세보기</h3>
		<div class="alert alert-secondary my-3">
			<div class="mb-3 mt-3">
				<label for="bhit" class="form-label">조회수</label> 
				<input type="number" value="${board.bhit}" 
				 class="form-control" id="bhit" name="bhit" readonly>
			</div>
			<div class="mb-3">
				<label for="bname" class="form-label">이름</label> 
				<input type="text" value="${board.bname}"
					class="form-control" id="bname" name="bname" readonly>
			</div>
			<div class="mb-3">
				<label for="btitle" class="form-label">제목</label> 
				<input type="text" value="${board.btitle}"
				  class="form-control" id="btitle" name="btitle" readonly>
			</div>
			<div class="mb-3">
				<label for="bcontent" class="form-label">내용</label>
				<textarea class="form-control" id="bcontent" name="bcontent" readonly>${board.bcontent}</textarea>
			</div>
			<div class="my-3 text-end">
				<a href="BEdit?bno=${board.bno}" class="btn btn-outline-primary" title="글수정">수정</a> 
				<a href="BDelete?bno=${board.bno}" class="btn btn-outline-success" title="글삭제">삭제</a>
				<a href="BList" class="btn btn-primary" title="목록보러가기">목록</a>
			</div>
		</div>
	</div>
<%@include file="../inc/footer.jsp" %>