package com.douzone.mysite.repository;

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
	
	public BoardVo findByNo(Long board_no) {
		return sqlSession.selectOne("board.findByNo", board_no);
	}
	
	public List<BoardVo> findPage(Long start_no, String kwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("start_no", start_no);
		map.put("kwd", kwd);
		return sqlSession.selectList("board.findPage",map);
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

	public BoardVo totalpage() {
		
		return sqlSession.selectOne("board.totalpage");
	}
}
