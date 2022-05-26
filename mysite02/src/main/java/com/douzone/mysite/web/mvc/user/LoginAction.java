package com.douzone.mysite.web.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ㅇㅇㅇㅇㅇ");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password);
		
		UserVo authUser = new UserRepository().findByEmailAndPassword(vo);
		if(authUser == null) {
//			로그인 실패
			request.setAttribute("email", email);
			request.setAttribute("result", "fail");
			WebUtil.forward(request, response, "user/loginform");
			System.out.println("로그인실패");
			return;
		}
		
//		로그인 처리
		System.out.println("로그인성공");
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		WebUtil.redirect(request, response, request.getContextPath());
	}

}
