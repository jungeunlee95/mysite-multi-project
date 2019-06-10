package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.service.AdminService;
import com.cafe24.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// System.out.println(handler+"@@@"); -> 이미지는 DefaultServletHttpRequestHandler

		ApplicationContext ac = 
		WebApplicationContextUtils.getWebApplicationContext(
				request.getServletContext()
				);
 
		AdminService adminService = ac.getBean(AdminService.class);
		request.getServletContext().setAttribute("site", adminService.getMain());
//		
		// 1. handler 종류 확인 
		if (handler instanceof HandlerMethod == false) {
			return true;
		}

		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. Method의 @Auth받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Method @Auth 없으면
		// class(Type)에 @Auth받아오기
		if(auth == null) { 
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}

		// 5. @Auth가 안 붙어있는 경우
		if (auth == null) {
			return true;
		}

		// 6. @Auth가 (class 또는 method에) 붙어있기 때문에
		// 인증여부 체크!
		HttpSession session = request.getSession();

		if (session == null) { // 인증 안되어있으면
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 7. 인증되어있으면 AuthUser 가져오기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) { // 인증 안되어있으면
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 8. Role 가져오기
		Auth.Role role = auth.role();

		// 9. role이 Auth.Role.User 라면,
		// BoardController에 @Auth(role=Auth.Role.USER) 라면! 관리자도, 유저도 들어갈 수 있어야함
		// 인증된 모든 사용자는 접근 가능
		if (role == Auth.Role.USER) {
			return true;
		}

		// 10. Admin Role 권한 체크 - authUser.getRole().equals("ADMIN")
		if( role==Auth.Role.ADMIN ) {
			// admin임을 알 수 있는 조건
			if( "ADMIN".equals(authUser.getRole()) == false ){   
								// admin이 아니므로 return false
				response.sendRedirect(request.getContextPath());
				return false;
			}
		}

		return true;
	}

}
