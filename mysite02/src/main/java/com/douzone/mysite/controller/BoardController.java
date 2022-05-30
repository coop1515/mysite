package com.douzone.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.web.mvc.board.BoardActionFactory;
import com.douzone.mysite.web.mvc.guest.GuestActionFactory;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;


public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String actionName = request.getParameter("a");
//		ActionFactory factory = new BoardActionFactory();
//		Action action = factory.getAction(actionName);
		Action action = new BoardActionFactory().getAction(actionName);
		action.execute(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
	}

}
