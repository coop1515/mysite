package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ApplicationExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(ApplicationExceptionHandler.class);
	@ExceptionHandler(Exception.class)
	public String handlerException(Exception e, Model model) {
		// 404 Error
		if(e instanceof NoHandlerFoundException) {
			return "error/404";
		}
		
		//1. 로깅("logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
//		e.printStackTrace(); // 콘솔 화면출력용
		LOGGER.error(errors.toString());
		
		//2. 사과 페이지(종료)
		model.addAttribute("exception",errors.toString());
		return "error/exception";
	}
}