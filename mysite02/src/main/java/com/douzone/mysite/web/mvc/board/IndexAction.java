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
		int length = list.size();
		request.setAttribute("length", length);
		Long no = Long.parseLong(request.getParameter("i"));
		if(no == 1) {
			list = new BoardRepository().findPage(no-1);
			
		}
		else{
			list = new BoardRepository().findPage((no-1)*5);
		}
		request.setAttribute("list", list);
		WebUtil.forward(request, response, "board/index");
//		WebUtil.redirect(request, response, request.getContextPath()+"/board?a=index&i=1");
	}

}
