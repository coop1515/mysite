package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping({"","/{no}"})
	public String index(Model model, @PathVariable(value = "no", required = false) Long no,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String kwd) {
		
		List<BoardVo> list = boardService.getMessageList(no,kwd);
		model.addAttribute("list", list);
		BoardVo total = boardService.getTotalPage(kwd);
		model.addAttribute("total", total.getTotalpage());
//		System.out.println(list.size());
		Long pageCount = no;
		model.addAttribute("pagecount", pageCount);
		model.addAttribute("kwd",kwd);
		return "board/index";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		boardService.deleteMessage(no);
		return "redirect:/board/1";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo boardVo = boardService.getViewPage(no);
		model.addAttribute("boardVo", boardVo);
		boardService.hitUpdate(no);
		return "board/view";
	}
	
	@RequestMapping(value = {"/write","/write/{no}"}, method = RequestMethod.GET)
	public String write(@PathVariable(value ="no", required = false) Long no, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		return "board/write";
	}
	
	@RequestMapping(value = {"/write","/write/{no}"}, method = RequestMethod.POST)
	public String write(@PathVariable(value = "no", required = false) Long no, BoardVo vo, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		boardService.addMessage(vo, no, authUser.getNo());
		return "redirect:/board/1";
	}
	
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, Model model, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		BoardVo boardVo = boardService.getModifyMessage(no);
		model.addAttribute("boardVo", boardVo);
		return "board/modify";
	}
	

	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public String modify(@PathVariable("no") Long no, String title, String content, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		boardService.modifyMessage(title, content, no);
		return "redirect:/board/view/{no}";
	}
}