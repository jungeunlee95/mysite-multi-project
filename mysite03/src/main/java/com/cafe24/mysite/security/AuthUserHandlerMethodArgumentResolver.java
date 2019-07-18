package com.cafe24.mysite.security;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	@Override
	public Object resolveArgument(
			MethodParameter parameter, 
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) throws Exception {
		
		Object principal = null;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null ) {
			principal = authentication.getPrincipal();
		}
		
		if(principal == null || principal.getClass() == String .class) {
			return null;
		}
		
		return principal;

	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		// @AuthUser가 안붙어있으면
		if(authUser==null) {
			return false; // 난 걔 관심없어 
		}
		// @AuthUser가 붙어있는데 Security가 아니면 return false
		if(parameter.getParameterType().equals(SecurityUser.class)==false) { //클래스 객체! 비교
			return false;
		}
		return true;
	}

}






