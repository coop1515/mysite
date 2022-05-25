package com.douzone.mysite.web.mvc.guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		String password = request.getParameter("password");
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(no);
		vo.setPassword(password);
		
		if(new GuestBookRepository().delete(vo)){
			WebUtil.redirect(request, response,request.getContextPath()+"/guestbook?a=index");
		}
		else{
			WebUtil.redirect(request, response,request.getContextPath()+"/guestbook?a=deleteform&no="+no);
		}

	}

}
