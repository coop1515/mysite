package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.douzone.mysite.vo.UserVo;

public class UserRepository {

	public boolean insert(UserVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "insert into user values (null, ?, ?, ?, ?, now())";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. Mapping(bind)
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			// 4. SQL 실행
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("뭐함");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {

			}
		}
		return result;
	}

	public boolean delete(UserVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "delete from guestbook where no = ? and password = ?";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. Mapping(bind)
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());

			// 4. SQL 실행
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("뭐함");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {

			}
		}
		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection connection = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.10.45:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("Fail Driver loading");
		}

		return connection;
	}
}
