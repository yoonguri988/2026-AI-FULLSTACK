<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 1. utf-8 설정
request.setCharacterEncoding("UTF-8");

String search = request.getParameter("search");

String url = "jdbc:mysql://localhost:3306/mbasic";
String sql = "select * from milk_order";
if(search != null && !search.trim().isEmpty()) {	
	sql += " where oname like ?"; 
}
sql += " order by ono desc";
Connection conn = null;  PreparedStatement pstmt = null;
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	conn = DriverManager.getConnection( url, "root", "1234");
	pstmt = conn.prepareStatement(sql);
	if(search != null && !search.trim().isEmpty()) {	
		pstmt.setString(1, "%"+search+"%");
	}
				
	ResultSet rset = pstmt.executeQuery();
	StringBuffer sb = new StringBuffer();
	
	// json 으로 수정
	sb.append("[");
	boolean isFirst = true;
	while(rset.next()){
		if (!isFirst) {
			sb.append(",");
        }
        isFirst = false;
		
		int ono = rset.getInt("ono");
        String oname = rset.getString("oname");
        int onum = rset.getInt("onum");
        String odate = rset.getString("odate");
        
        sb.append("{");
        sb.append(String.format("\"ono\": %d,",ono));
        sb.append(String.format("\"oname\": \"%s\",",oname));
        sb.append(String.format("\"onum\": %d,",onum));
        sb.append(String.format("\"odate\": \"%s\"",odate));
        sb.append("}");
	}
	sb.append("]");
	out.println(sb.toString());
} catch (Exception e){ e.printStackTrace(); }
  finally { conn.close(); pstmt.close(); }
%>