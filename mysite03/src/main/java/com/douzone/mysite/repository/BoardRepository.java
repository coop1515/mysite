package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "select * from board a, user b "
					+ " where a.user_no = b.no "
					+ " order by g_no desc , o_no asc";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. parameter mapping

			// 5. SQL 실행

			rs = pstmt.executeQuery();

			// 6. 결과처리
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				Long hit = rs.getLong(4);
				String reg_date = rs.getString(5);
				Long g_no = rs.getLong(6);
				Long o_no = rs.getLong(7);
				Long depth = rs.getLong(8);
				Long user_no = rs.getLong(9);
				String name = rs.getString(11);
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(reg_date);
				vo.setG_no(g_no);
				vo.setO_no(o_no);
				vo.setDepth(depth);
				vo.setUser_no(user_no);
				vo.setName(name);
				result.add(vo);
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
	
	public BoardVo findByNo(Long board_no) {
//		BoardVo result = null;
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			connection = getConnection();
//
//			// 3. SQL 준비
//			String sql = "select no, title, contents, user_no, g_no, o_no, depth from board where no = ?";
//			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체
//			
//			// 4. parameter mapping
//			pstmt.setLong(1, board_no);
//			// 5. SQL 실행
//
//			rs = pstmt.executeQuery();
//
//			// 6. 결과처리
//			if (rs.next()) {
//				Long no = rs.getLong(1);
//				String title = rs.getString(2);
//				String contents = rs.getString(3);
//				Long user_no = rs.getLong(4);
//				Long g_no = rs.getLong(5);
//				Long o_no = rs.getLong(6);
//				Long depth = rs.getLong(7);
//				result = new BoardVo();
//				result.setNo(no);
//				result.setTitle(title);
//				result.setContents(contents);
//				result.setUser_no(user_no);
//				result.setG_no(g_no);
//				result.setO_no(o_no);
//				result.setDepth(depth);
//			}
//		} catch (SQLException e) {
//			System.out.println("뭐함");
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//
//			}
//		}
//		return result;
		return sqlSession.selectOne("board.findByNo", board_no);
	}
	
	public List<BoardVo> findPage(Long start_no, String kwd) {
		List<BoardVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			if(kwd == null) {
			// 3. SQL 준비
			String sql = "select * from board a, user b "
					+ " where a.user_no = b.no"
					+ " order by g_no desc , o_no asc limit ?,5";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체
			
			// 4. parameter mapping
			pstmt.setLong(1, start_no);
			
			}
			else {
				String sql = "select * from board a, user b "
						+ " where a.user_no = b.no"
						+ "	and concat(title,contents) like concat('%',?,'%') "
						+ " order by g_no desc , o_no asc limit ?,5 ";
				pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

				// 4. parameter mapping
				pstmt.setString(1, kwd);
				pstmt.setLong(2, start_no);
			}
			// 5. SQL 실행
			rs = pstmt.executeQuery();

			// 6. 결과처리
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				Long hit = rs.getLong(4);
				String reg_date = rs.getString(5);
				Long g_no = rs.getLong(6);
				Long o_no = rs.getLong(7);
				Long depth = rs.getLong(8);
				Long user_no = rs.getLong(9);
				String name = rs.getString(11);
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(reg_date);
				vo.setG_no(g_no);
				vo.setO_no(o_no);
				vo.setDepth(depth);
				vo.setUser_no(user_no);
				vo.setName(name);
				result.add(vo);
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
	
	public boolean insert(BoardVo vo) {
		return sqlSession.insert("board.insert",vo) == 1;
	}
	
	public boolean update(String title, String contents, Long no) {
		Map<String, Object> map = new HashMap<>();
		map.put("title", title);
		map.put("contents", contents);
		map.put("no", no);
		return sqlSession.update("board.update",map) == 1;
	}
	
	public boolean hit_update(Long no) {
		return sqlSession.update("board.hit_update",no) == 1;
	}
	
	public boolean order_update(Long no) {
		return sqlSession.update("board.order_update",no) == 1;
	}
	
	public boolean delete(Long no) {
		return sqlSession.update("board.delete",no) == 1;
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

	public BoardVo totalpage() {
		
		return sqlSession.selectOne("board.totalpage");
	}
}
