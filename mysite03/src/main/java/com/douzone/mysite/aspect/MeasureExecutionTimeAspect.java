package com.douzone.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
	
	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.service.*.*(..)) || execution(* *..*.controller.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		//before
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = pjp.proceed();		
		
		//after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		String className = pjp.getTarget().getClass().getName(); //클래스 이름 가져오는 것. pjp가 모든 정보를 가지고 있어서 가능.
		String methodName = pjp.getSignature().getName(); //메소드 이름 가져오는 것.
		String taskName = className + "."+ methodName;
		
		
		System.out.println("[Excution Time]["+taskName+"] " + totalTime + " millis");
		
		return result;
	}
}
