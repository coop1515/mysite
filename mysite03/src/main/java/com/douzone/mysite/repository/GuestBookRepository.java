package com.douzone.mysite.repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.douzone.mysite.vo.GuestBookVo;


@Repository
public class GuestBookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestBookVo> findAll() {
//		// before
//		StopWatch sw = new StopWatch();
//		
//		sw.start();
//		List<GuestBookVo> list = sqlSession.selectList("guestbook.findAll");
//		sw.stop();
//		
//		Long totalTime = sw.getTotalTimeMillis();
//		System.out.println("[Excution Time][GuestBookRepository.findAll] " + totalTime + " millis");
//		return list;
		return sqlSession.selectList("guestbook.findAll");
	}
	
	public boolean insert(GuestBookVo vo) {
//		System.out.println(vo);
		boolean result = sqlSession.insert("guestbook.insert",vo) == 1;
//		System.out.println(vo);
		return result;
	}
	public boolean delete(Long no, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("password", password);
		
		return sqlSession.delete("guestbook.delete", map) == 1;	
	}
}