package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public List<BoardVo> getMessageList() {
		
		return boardRepository.findAll();
	}

	public BoardVo getViewPage(Long no) {
		
//		System.out.println(no);
		return boardRepository.findByNo(no);
	}

	public boolean deleteMessage(Long no) {
		return boardRepository.delete(no);
		
	}

	public boolean addMessage(BoardVo vo) {
		System.out.println(vo);
		
		return boardRepository.insert(vo);
	}

	public void hitUpdate(Long no) {
		boardRepository.hit_update(no);
		
	}

	public void modifyMessage(String title, String contents, Long no) {
		System.out.println(title +" "+ contents +" "+ no);
		boardRepository.update(title, contents, no);
		
		
	}

	public BoardVo getModifyMessage(Long no) {
		return boardRepository.findByNo(no);
	}
}
