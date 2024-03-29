package com.douzone.mysite.web.mvc.guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class GuestBookAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String message = request.getParameter("content");
		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		new GuestBookRepository().insert(vo);
		
		WebUtil.redirect(request, response,request.getContextPath()+"/guestbook?a=index");
	}

}
