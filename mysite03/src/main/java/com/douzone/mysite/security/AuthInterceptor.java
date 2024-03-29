package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. Handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			// DefaultServletHandler가 처리하는 정적 자원
			return true;
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. Handler Method의 @Auth 받아보기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Handler Method에 @Auth가 없으면 Type에 붙어 있는 지 확인.
		if(auth == null) {
			// 과제
			 auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
//			 System.out.println(auth);
		}
		
		
		// 5. Type과 Method 모두에 @Auth 붙어 있지 않은 경우
		if(auth == null) {
			return true;
		}
		
		// 6. Handler Method에 @Auth가 붙어 있기 때문에 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		// 7. @Auth 가 적용되어 지만 인증이 되어 있지 않음.
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+ "/user/login");
			return false;
		}
		
		// 8. 권한 체크를 위해서 @Auth의 role 값을 가져오기
		String role = auth.role();
//		System.out.println(role);
		String authUserRole = authUser.getRole();
//		System.out.println(authUserRole);
		// 안맞는 경우 @Auth(role="ADMIN")이면 session에 있는 @도 ADMIN이여야 함.
		if(!role.equals(authUserRole)) {
			response.sendRedirect(request.getContextPath()+ "/main");
			return false;
		}
		
		// 8. @Auth 가 적용되어 있고 인증도 되어 있음.
		return true;
	}

}