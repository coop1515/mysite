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

	public boolean addMessage(BoardVo vo, Long no, Long auth_no) {
		System.out.println(vo);
		if(no == null) {
			System.out.println(" 1 :" + vo);
		}
		else {
			BoardVo vo2 = boardRepository.findByNo(no);
//			System.out.println(no);
			boardRepository.order_update(vo2.getG_no());
			vo.setG_no(vo2.getG_no());
			vo.setO_no(vo2.getO_no()+1);
			vo.setDepth(vo2.getDepth()+1);
//			System.out.println("2 : " + vo);
		}
		vo.setUser_no(auth_no);
		return boardRepository.insert(vo);
	}

	public void hitUpdate(Long no) {
		boardRepository.hit_update(no);
		
	}

	public void modifyMessage(String title, String contents, Long no) {
//		System.out.println(title +" "+ contents +" "+ no);
		boardRepository.update(title, contents, no);
		
		
	}

	public BoardVo getModifyMessage(Long no) {
		return boardRepository.findByNo(no);
	}

	public BoardVo getTotalPage() {
		return boardRepository.totalpage();
		
	}
}
