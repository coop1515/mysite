package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping({"","/index"})
	public String index(Model model) {
		List<BoardVo> list = boardService.getMessageList();
		model.addAttribute("list", list);
		return "board/index";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		boardService.deleteMessage(no);
		return "redirect:/board";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo boardVo = boardService.getViewPage(no);
		model.addAttribute("boardVo", boardVo);
		boardService.hitUpdate(no);
		return "board/view";
	}
	
	@RequestMapping(value = "/write/{no}", method = RequestMethod.GET)
	public String write(@PathVariable("no") Long no) {
		return "board/write";
	}
	
	@RequestMapping(value = "/write/{no}", method = RequestMethod.POST)
	public String write(@PathVariable("no") Long no, BoardVo vo) {
		boardService.addMessage(vo);
		return "redirect:/board";
	}
	
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, Model model) {
		BoardVo boardVo = boardService.getModifyMessage(no);
		model.addAttribute("boardVo", boardVo);
		return "board/modify";
	}
	

	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public String modify(@PathVariable("no") Long no, String title, String content) {
		boardService.modifyMessage(title, content, no);
		return "redirect:/board/view/{no}";
	}
}
