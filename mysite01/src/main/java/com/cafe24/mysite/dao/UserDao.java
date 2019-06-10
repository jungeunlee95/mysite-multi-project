package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.cafe24.mysite.vo.UserVo;

public class UserDao {
	
	public UserVo get(Long no) {
		UserVo result = null;

		// 자원정리
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select no, name, password, email "
						+ " from member "
						+ " where no = ? " ;
//			String sql = " select no, name, password, email "
//					+ " from user "
//					+ " where no = ? " ;
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long no2 = rs.getLong(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				
				result = new UserVo();
				result.setNo(no2);
				result.setName(name);
				result.setPassword(password);
				result.setEmail(email);

			}
			
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	
	// 로그인
	public UserVo get(String email, String password) {
		UserVo result = null;

		// 자원정리
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select no, name "
						+ " from member "
						+ " where email = ? "
						+ " and password = ? ";
//			String sql = " select no, name "
//					+ " from user "
//					+ " where email = ? "
//					+ " and password = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				result = new UserVo();
				result.setNo(no);
				result.setName(name);

			}
			
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public boolean insert(UserVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();


			String sql = " insert into member  " + 
						"  values(default, ?, ?, ?, ?, now()) ";
			
			// mariadb
//			String sql = " insert into user  " + 
//					"  values(null, ?, ?, ?, ?, now()) ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			int count = pstmt.executeUpdate();
			result = count == 1; 

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}
	
	public boolean update(UserVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();


			String sql = " update member set "
						+ " name = ?, "
						+ " email = ?, "
						+ " password = ?, "
						+ " gender = ? " 
						+ " where no = ? ";
//			String sql = " update user set "
//					+ " name = ?, "
//					+ " email = ?, "
//					+ " password = ?, "
//					+ " gender = ? " 
//					+ " where no = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			pstmt.setLong(5, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1; 

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}
	
	// postsql
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://192.168.1.52:5432/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} finally {
			
		} 
		return conn; 
	}
	
	// mariadb
//	private Connection getConnection() throws SQLException {
//		Connection conn = null;
//		
//		try {
//			
//			Class.forName("org.mariadb.jdbc.Driver");
//			String url = "jdbc:mariadb://192.168.1.52:3307/webdb";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//			
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패");
//		} finally {
//			
//		}
//		return conn;
//	}
}
