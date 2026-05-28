package com.thejoa703.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.thejoa703.comm.DBConn;

public class UserDAO {
	public int regUser(UserDTO user) {
		int rs = 0;
		String sql = "INSERT INTO USERS (NICKNAME, BPASS, EMAIL, MOBILE, BIP) VALUES (?,?,?,?,?)";
		try {
			Connection conn = DBConn.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getNickname());
			pstmt.setString(2, user.getBpass());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getMobile());
			pstmt.setString(5, user.getBip());

			rs = pstmt.executeUpdate();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public UserDTO loginUser(UserDTO user) {
		UserDTO dto = null;
		
		String sql = "SELECT * FROM USERS WHERE EMAIL = ? AND BPASS = ?";
		try {
			Connection conn = DBConn.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getBpass());

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int uno = rs.getInt("UNO");
				String nickname = rs.getString("NICKNAME");
				String email = rs.getString("EMAIL");
				String mobile = rs.getString("MOBILE");
				String udate = rs.getString("UDATE");
				String bip = rs.getString("BIP");
				
				dto = new UserDTO(uno, nickname, email, mobile, udate, bip);
			}
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public UserDTO searchUser(UserDTO user) {
		UserDTO dto = null;
		
		String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
		try {
			Connection conn = DBConn.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int uno = rs.getInt("UNO");
				String nickname = rs.getString("NICKNAME");
				String email = rs.getString("EMAIL");
				String mobile = rs.getString("MOBILE");
				String udate = rs.getString("UDATE");
				String bip = rs.getString("BIP");
				
				dto = new UserDTO(uno, nickname, email, mobile, udate, bip);
			}
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public int updateUser(UserDTO user) {
		int rs = 0;
		String sql = "UPDATE USERS SET NICKNAME=?, MOBILE=?"
			    +" WHERE EMAIL=? AND BPASS=?";
		try {
			Connection conn = DBConn.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getNickname());
			pstmt.setString(2, user.getMobile());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getBpass());
			
			rs = pstmt.executeUpdate();
			
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
