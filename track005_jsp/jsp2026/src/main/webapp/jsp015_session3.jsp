<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*jsp015_session3.jsp*/
//session.removeAttribute("username");
session.removeAttribute("userage");

response.sendRedirect("jsp015_1_session.jsp");
out.println("<script>location.href='jsp015_1_session.jsp';</script>");
%>