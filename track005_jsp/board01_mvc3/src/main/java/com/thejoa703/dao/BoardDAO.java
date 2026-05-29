package com.thejoa703.dao;

import com.thejoa703.dto.BoardDTO;
import com.thejoa703.comm.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	public BoardDTO getBoard(int bno) {
		BoardDTO dto = null;
		
		String sql1 = "UPDATE MVCBOARD1 SET BHIT = BHIT+1 WHERE BNO=? ";
		String sql2 = "SELECT * FROM MVCBOARD1 WHERE BNO=?";
		
		String btitle = "";
		String bname = "";
		String bcontent = "";
		String bdate = "";
		int bhit = 0;
		String bip = "";
		
		try{
			Connection conn = DBConn.getConnection(); 
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, bno);
			pstmt1.executeUpdate();
			
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, bno);
			ResultSet rs2 = pstmt2.executeQuery(); 
			
			while(rs2.next()){
				btitle = rs2.getString("BTITLE");
				bname = rs2.getString("BNAME");
				bdate = rs2.getString("BDATE");
				bcontent = rs2.getString("BCONTENT");
				bip = rs2.getString("BIP");
				bhit = rs2.getInt("BHIT");
				
				dto = new BoardDTO(bno, btitle, bname, bdate, bcontent, bip, bhit);
			}

		if(rs2 != null) rs2.close();
		if(pstmt2 != null) pstmt2.close();
		if(pstmt1 != null) pstmt1.close();
		if(conn != null) conn.close();
	} catch(Exception e) {
		e.printStackTrace();
	}
		return dto;
	}
	
	public List<BoardDTO> getBoards(String q) {
		List<BoardDTO> list = new ArrayList<>();
		String sql = "SELECT ROW_NUMBER() OVER (ORDER BY BNO ASC) AS ROWNUM,"
				+ "       BNO, BNAME, BTITLE, BCONTENT, BDATE, BHIT, BIP" 
				+ "  FROM MVCBOARD1 " 
				+ (q.equals("")? "" : "WHERE BTITLE LIKE ? OR BCONTENT LIKE ?" )
				+ " ORDER BY BDATE DESC";
		try {
			Connection conn = DBConn.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			if(!q.equals("")) {
				pstmt.setString(1, "%"+q+"%");
				pstmt.setString(2, "%"+q+"%");
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("ROWNUM");
				int bno = rs.getInt("BNO");
				String btitle = rs.getString("BTITLE");
				String bname = rs.getString("BNAME");
				String bdate = rs.getString("BDATE");
				String bcontent = rs.getString("BCONTENT");
				String bip = rs.getString("BIP");
				int bhit = rs.getInt("BHIT");

				list.add(new BoardDTO(rownum, bno, btitle, bname, bdate, bcontent, bip, bhit));
			}
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertBoard(BoardDTO board) {
		int rs = 0;
		String sql = "INSERT INTO MVCBOARD1 (bname, bpass, btitle, bcontent, bip) VALUES (?,?,?,?,?)";
		try {
			Connection conn = DBConn.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBname());
			pstmt.setString(2, board.getBpass());
			pstmt.setString(3, board.getBtitle());
			pstmt.setString(4, board.getBcontent());
			pstmt.setString(5, board.getBip());
			
			rs = pstmt.executeUpdate(); 
			
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int updateBoard(BoardDTO board) {
		int rs = 0;
		String sql = "UPDATE MVCBOARD1 SET BTITLE=?, BCONTENT=? WHERE BNO = ? AND BPASS = ?";

		try {
			Connection conn = DBConn.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBtitle());
			pstmt.setString(2, board.getBcontent());
			pstmt.setInt(3, board.getBno());
			pstmt.setString(4, board.getBpass());
			
			rs = pstmt.executeUpdate();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int deleteBoard(BoardDTO board) {
		int rs = 0;
		String sql = "DELETE FROM MVCBOARD1 WHERE BNO = ? AND BPASS = ?";
		
		try {
			Connection conn = DBConn.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBno());
			pstmt.setString(2, board.getBpass());

			rs = pstmt.executeUpdate();

			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}
}
