package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.mysite.vo.GuestbookVo;


public class GuestbookDao {
	public boolean delete(GuestbookVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();


			String sql = " delete from guestbook  " 
						+ "  where no = ? "
						+ " and password = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());

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
	
	public boolean insert(GuestbookVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();


			String sql = " insert into guestbook  " + 
						"  values(default, ?, ?, ?, now()) ";
			
			// mariadb
//			String sql = " insert into guestbook  " + 
//					"  values(null, ?, ?, ?, now()) ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());

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
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> result = new ArrayList<GuestbookVo>();

		// 자원정리
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// postgresql
			String sql = "select no, name, contents, "
					+ "          to_char(reg_date, 'yyyy-mm-dd') " + 
						" from guestbook " + 
						" order by reg_date desc ";
			
			// mariadb
//			String sql = "select no, name, contents, "
//					+ "          date_format(reg_date, '%Y-%m-%d %h:%i:%s') " + 
//					" from guestbook " + 
//					" order by reg_date desc ";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);

				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setRegDate(regDate);

				result.add(vo);
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
//
//		return conn;
//		
//	}

}
