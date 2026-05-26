<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String search = request.getParameter("search");

	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/mbasic";
	String user = "root";
	String pass = "1234";
	String sql = "SELECT ROW_NUMBER() OVER (ORDER BY BNO ASC) AS ROWNUM,"
		       +" BNO, BNAME, BTITLE, BCONTENT, BDATE, BHIT"
		       +" FROM MVCBOARD1"
		       +" WHERE BTITLE LIKE ? OR BCONTENT LIKE ?"
		       +" ORDER BY BDATE DESC";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try{
		conn = DriverManager.getConnection(url, user, pass);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%"+search+"%");
		pstmt.setString(2, "%"+search+"%");
		rs = pstmt.executeQuery();
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		boolean isFirst = true;
		while(rs.next()){
			if (!isFirst) {
				sb.append(",");
	        }
	        isFirst = false;
			
			int rownum = rs.getInt("ROWNUM");
			int bno = rs.getInt("BNO");
			String btitle = rs.getString("BTITLE");
			String bname = rs.getString("BNAME");
			String bdate = rs.getString("BDATE");
			int bhit = rs.getInt("BHIT");
			
	
			sb.append("{");
			sb.append(String.format("\"rownum\": %d,",rownum));
	        sb.append(String.format("\"bno\": %d,",bno));
	        sb.append(String.format("\"btitle\":\"%s\",",btitle));
	        sb.append(String.format("\"bname\": \"%s\",",bname));
	        sb.append(String.format("\"bdate\": \"%s\",",bdate));
	        sb.append(String.format("\"bhit\": %d",bhit));
			sb.append("}");
		}
		sb.append("]");
		out.println(sb.toString());
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		rs.close();
		pstmt.close();
		conn.close();
	}
%>