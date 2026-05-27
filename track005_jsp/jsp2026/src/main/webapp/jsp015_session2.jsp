<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*jsp015_session2.jsp*/
	session.setMaxInactiveInterval(60 * 10); // seconds -> 10분동안 세션 유지

	session.setAttribute("username", "sally"); // 이름, 값
	session.setAttribute("userage", "10");
	
	response.sendRedirect("jsp015_1_session.jsp");
%>