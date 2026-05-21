<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//utf-8
	request.setCharacterEncoding("UTF-8");
	
	int userage = Integer.parseInt(request.getParameter("userage"));
	if(userage < 20){
		response.sendRedirect("jsp013_child.jsp?userage="+userage);
	}else {
		request.getRequestDispatcher("jsp013_adult.jsp").forward(request, response);
	}
%>