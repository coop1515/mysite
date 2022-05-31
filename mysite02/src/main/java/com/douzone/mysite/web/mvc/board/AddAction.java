package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		Long no = Long.parseLong(request.getParameter("no"));
		Long user_no = Long.parseLong(request.getParameter("user_no"));
		BoardVo vo = new BoardVo();
		System.out.println(vo.getG_no());
		if(no == user_no) {
		
		} else {
			vo = new BoardRepository().findByNo(no);
			vo.setG_no(vo.getG_no());
			vo.setO_no(vo.getO_no()+1);
			vo.setDepth(vo.getDepth()+1);
			
//			System.out.println(vo.getO_no());
//			System.out.println(vo.getDepth());
		}
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUser_no(user_no);
		new BoardRepository().insert(vo);
		
		System.out.println(no+" "+ user_no);
		WebUtil.redirect(request, response,request.getContextPath()+"/board?a=index");
		
		
	}

}