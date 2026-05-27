<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String email = request.getParameter("email");
	String bpass = request.getParameter("bpass");
	// Cookie
	String emailSave = request.getParameter("emailSave");

	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/mbasic";
	String user = "root";
	String pass = "1234";
	String sql = "SELECT * FROM USERS WHERE EMAIL = ? AND BPASS = ?";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try{
		conn = DriverManager.getConnection(url, user, pass);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, bpass);
		
		rs = pstmt.executeQuery();
		boolean isLogin = false;
		while(rs.next()){
			session.setAttribute("email", rs.getString("EMAIL"));
			session.setAttribute("nickname", rs.getString("NICKNAME"));
			isLogin = true;
		}
		
		if(isLogin){
			// 쿠키 객체 생성
	        Cookie emailCookie = new Cookie("userEmail", email);
	        emailCookie.setPath("/"); // 애플리케이션 전역에서 쿠키 접근 가능하도록 설정

	        if ("save".equals(emailSave)) {
	            // 체크박스가 켜져 있으면: 쿠키 유효기간 설정
	            emailCookie.setMaxAge(60); 
	            response.addCookie(emailCookie);
	        } else {
	            // 체크박스가 꺼져 있으면: 기존 쿠키를 즉시 삭제 (유효기간을 0으로)
	            emailCookie.setMaxAge(0);
	            response.addCookie(emailCookie);
	        }
			
			out.println("<script> alert('로그인 성공!'); location.href='mypage.jsp';</script>");
		} else {
			out.println("<script> alert('로그인 실패, 아이디나 비밀번호를 확인해주세요.'); location.href='login.jsp';</script>");
		}
	} catch(Exception e){
		e.printStackTrace();
	} finally {
		rs.close();
		pstmt.close();
		conn.close();
	}
%>