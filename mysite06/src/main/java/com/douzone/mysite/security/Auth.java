package com.douzone.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//default로 모두 붙일수 있게 되있어서 설정 해주면 됨.
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
//	public String test();
//	public boolean test() default false; // default 값 세팅
	public String value() default "";
	public String role() default "USER";
}
