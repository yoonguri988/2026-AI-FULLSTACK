package com.thejoa703.board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BDetail
 */
@WebServlet("/BDetail")
public class BDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		String url = "jdbc:mysql://localhost:3306/mbasic";
		String sql1 = "UPDATE MVCBOARD1 SET BHIT = BHIT+1 WHERE BNO=? ";
		String sql2 = "SELECT * FROM MVCBOARD1 WHERE BNO=?";
		String user = "root";
		String pass = "1234";
		
		String btitle = "";
		String bname = "";
		String bcontent = "";
		int bhit = 0;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			PreparedStatement pstmt1 = null;  PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;
			
			conn = DriverManager.getConnection(url, user, pass);
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, bno);
			pstmt1.executeUpdate();
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, bno);
			rs2 = pstmt2.executeQuery(); 
			
			while(rs2.next()){
				btitle = rs2.getString("BTITLE");
				bname = rs2.getString("BNAME");
				bcontent = rs2.getString("BCONTENT");
				bhit = rs2.getInt("BHIT");
				
				request.setAttribute("board", new BoardDTO(bno, btitle, bname, bcontent, bhit));
			}
			
			if(rs2 != null) rs2.close();
			if(pstmt2 != null) pstmt2.close();
			if(pstmt1 != null) pstmt1.close();
			if(conn != null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("board/detail.jsp?bno="+bno).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
