package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("guestbook")
public class GuestBookController {

	@Autowired
	private GuestBookService guestBookService;

	@RequestMapping({ "", "/index" })
	public String index(Model model) {
		List<GuestBookVo> list = guestBookService.getMessageList();
		model.addAttribute("list", list);
		return "guestbook/index";
	}

	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
//		model.addAttribute(no);
		return "guestbook/delete";
	}

	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
	public String delete(@PathVariable("no") Long no,
			@RequestParam(value = "password", required = true, defaultValue = "") String password) {
		boolean message = guestBookService.deleteMessage(no, password);
		if (message == false) {
			return "redirect:/guestbook/delete/{no}";
		} else {
			return "redirect:/guestbook";
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(GuestBookVo vo) {
//		System.out.println(vo);
		guestBookService.addMessage(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping({ "/spa"})
	public String spaLanding() {
		return "guestbook/index-spa";
	}
//	@ExceptionHandler(Exception.class)
//	public String handlerException() {
//		return "error/exception";
//	}
}