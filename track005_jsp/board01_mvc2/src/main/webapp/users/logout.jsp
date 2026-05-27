<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.invalidate();

	String path = request.getContextPath();

	out.println("<script> alert('로그아웃'); location.href='"+path+"/index.jsp';</script>");
%>