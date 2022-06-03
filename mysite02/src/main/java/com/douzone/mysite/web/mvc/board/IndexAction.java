package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoardVo> list = new BoardRepository().findAll();
		Long no = Long.parseLong(request.getParameter("i"));
		String kwd = request.getParameter("kwd");
		int length = 0;
		
		if(kwd == null) {
			length = list.size();
			if(no == 1) {
				list = new BoardRepository().findPage(no-1,kwd);
			}
			
			else {
				list = new BoardRepository().findPage((no-1)*5,kwd);
			}
			
		}
		
		else {
//			list = new BoardRepository().findSearch(kwd, no-1);
			list = new BoardRepository().findPage(no-1, kwd);
			length = list.size();
			if(no == 1) {
				list = new BoardRepository().findPage(no-1,kwd);
			}
			
			else {
				list = new BoardRepository().findPage((no-1)*5,kwd);
			}
		}
		
		request.setAttribute("length", length);
//		System.out.println(kwd+" "+length);
		
		
		request.setAttribute("list", list);
		request.setAttribute("kwd", kwd);
		WebUtil.forward(request, response, "board/index");
//		WebUtil.redirect(request, response, request.getContextPath()+"/board?a=index&i=1");
	}

}