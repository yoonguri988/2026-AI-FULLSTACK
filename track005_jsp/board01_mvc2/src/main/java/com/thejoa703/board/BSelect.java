package com.thejoa703.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class BSelect
 */
@WebServlet("/BSelect")
public class BSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String search = request.getParameter("search");

		String url = "jdbc:mysql://localhost:3306/mbasic";
		String user = "root";
		String pass = "1234";
		String sql = "SELECT ROW_NUMBER() OVER (ORDER BY BNO ASC) AS ROWNUM,"
			       +" BNO, BNAME, BTITLE, BCONTENT, BDATE, BHIT"
			       +" FROM MVCBOARD1"
			       +" WHERE BTITLE LIKE ? OR BCONTENT LIKE ?"
			       +" ORDER BY BDATE DESC";

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, "%"+search+"%");
			ResultSet rs = pstmt.executeQuery();
			
			Gson gson = new Gson();
			List<BoardDTO> list = new ArrayList<>();
			
			while(rs.next()){
				int rownum = rs.getInt("ROWNUM");
				int bno = rs.getInt("BNO");
				String btitle = rs.getString("BTITLE");
				String bname = rs.getString("BNAME");
				String bdate = rs.getString("BDATE");
				int bhit = rs.getInt("BHIT");
				
				list.add(new BoardDTO(rownum,bno,btitle,bname,bdate,bhit));
			}
			String jsonResponse = gson.toJson(list);
			out.println(jsonResponse);
		
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
