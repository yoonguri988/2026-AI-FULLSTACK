<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
	<div class="container card my-5">
		<h3 class="card-header">마이페이지</h3>
		<div class="my-3">
		<table class="table table-striped table-bordered table-hover">
			<caption>USER 정보 목록</caption>
			<!-- 표 제목 -->
			<thead></thead>
			<tbody id="ResultBody">
				<tr>
					<th scope="row">${user.nicknameKr}</th>
					<td>${user.nickname}</td>
				</tr>
				<tr>
					<th scope="row">${user.emailKr}</th>
					<td>${user.email}</td>
				</tr>
				<tr>
					<th scope="row">${user.mobileKr}</th>
					<td>${user.mobile}</td>
				</tr>
				<tr>
					<th scope="row">${user.udateKr}</th>
					<td>${user.udate}</td>
				</tr>
				<tr>
					<th scope="row">${user.bipKr}</th>
					<td>${user.bip}</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div class="my-3 text-end">
			<a href="EditView.do" class="btn btn-outline-primary" title="회원정보수정">수정</a> 
			<a href="DeleteView.do" class="btn btn-outline-danger" title="회원정보탈퇴">탈퇴</a> 
		</div>
	</div>
<%@include file="../inc/footer.jsp" %>