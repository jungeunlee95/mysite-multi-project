package com.cafe24.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//이 어노테이션 어디에 붙여쓸거야? 파라미터? 메소드?
@Target({ElementType.TYPE, ElementType.METHOD}) 
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	
	public enum Role{USER, ADMIN}
	
	public Role role() default Role.USER;
	
//	String value() default "USER";
//	int test() default 1;
}
