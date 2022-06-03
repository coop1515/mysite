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

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("kwd");
		Long no = Long.parseLong(request.getParameter("i"));
		List<BoardVo> list = new BoardRepository().findSearch(search,no);
		if(no == 1) {
			
			list = new BoardRepository().findSearch(search,no-1);
		}
		else{
			
			list = new BoardRepository().findSearch(search,(no-1)*5);
		}
		
		
		
		int length = list.size();
		request.setAttribute("length", length);
		request.setAttribute("list", list);
//		System.out.println(list);
//		System.out.println(search);
		WebUtil.forward(request, response, "board/index");

	}

}
