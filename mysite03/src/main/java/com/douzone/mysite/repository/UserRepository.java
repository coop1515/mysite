package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;
	
	public boolean insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo) == 1;
	}

	public boolean delete(UserVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dataSource.getConnection();

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
	public UserVo findByNo(Long auth_no) {
		UserVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();

			// 3. SQL 준비
			String sql = "select no, name, email, gender from user where no = ?";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. parameter mapping
			pstmt.setLong(1, auth_no);
			// 5. SQL 실행

			rs = pstmt.executeQuery();

			// 6. 결과처리
			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String gender = rs.getString(4);
				
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
				result.setEmail(email);
				result.setGender(gender);
			}
		} catch (SQLException e) {
			System.out.println("뭐함");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
	public UserVo findByEmailAndPassword(UserVo vo) {
		UserVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();

			// 3. SQL 준비
			String sql = "select no, name from user where email = ? and password = ?";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. parameter mapping
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			// 5. SQL 실행

			rs = pstmt.executeQuery();

			// 6. 결과처리
			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
			}
		} catch (SQLException e) {
			System.out.println("뭐함");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
	
	public boolean update(UserVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dataSource.getConnection();

			// 3. SQL 준비
			if("".equals(vo.getPassword())) {
				String sql =
						" update user " + 
						" set name=?, gender=?" + 
						" where no=?";
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setLong(3, vo.getNo());
			}
			else {
			String sql = "update user set name = ?,  password = ?, gender = ? where no = ?";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. Mapping(bind)
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setLong(4, vo.getNo());
			}
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
}
