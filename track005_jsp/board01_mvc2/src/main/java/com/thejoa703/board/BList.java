package com.thejoa703.board;

import java.io.IOException;
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

/**
 * Servlet implementation class BList
 */
@WebServlet("/BList")
public class BList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String url = "jdbc:mysql://localhost:3306/mbasic";
		String sql = "SELECT ROW_NUMBER() OVER (ORDER BY BNO ASC) AS ROWNUM,"
				    +"       BNO, BNAME, BTITLE, BCONTENT, BDATE, BHIT"
				    +"  FROM MVCBOARD1 "
				    +" ORDER BY BDATE DESC";
		String user = "root";
		String pass = "1234";
		
		List<BoardDTO> boardList = new ArrayList<>();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				int rownum = rs.getInt("ROWNUM");
				int bno = rs.getInt("BNO");
				String btitle = rs.getString("BTITLE");
				String bname = rs.getString("BNAME");
				String bdate = rs.getString("BDATE");
				int bhit = rs.getInt("BHIT");
				
				boardList.add(new BoardDTO(rownum, bno, btitle, bname, bdate, bhit));
			}
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("boardList", boardList);
		
		request.getRequestDispatcher("board/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
