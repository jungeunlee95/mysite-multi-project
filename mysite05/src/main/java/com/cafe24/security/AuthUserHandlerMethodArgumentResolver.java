package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	@Override
	public Object resolveArgument(
			MethodParameter parameter, 
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) throws Exception {
		
		if(supportsParameter(parameter)==false) {
			return WebArgumentResolver.UNRESOLVED; // 난 이거 몰라
		}
		
		// 내가 아는 파라미터라면 가져와서
		HttpServletRequest request = 
				webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		if(session == null) {
			return null;
		}
		
		// 설정
		return session.getAttribute("authUser");

	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		// @AuthUser가 안붙어있으면
		if(authUser==null) {
			return false; // 난 걔 관심없어 
		}
		// @AuthUser가 붙어있는데 UserVo가 아니라 BoardVo라던지 파라미터 타입이 다를때
		if(parameter.getParameterType().equals(UserVo.class)==false) { //클래스 객체! 비교
			return false;
		}
		return true;
	}

}






