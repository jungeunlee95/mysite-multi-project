package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class UpdateSuccessAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		if(session != null && session.getAttribute("authUser") != null) {
			// 로그아웃 처리
			session.removeAttribute("authUser"); // UserVo 먼저 날리고
			session.invalidate(); // 이것만 하면 시간지나면 없어짐 -> UserVo가 메모리 차지함
		}
		WebUtil.forward(request, response, "/WEB-INF/views/user/updatesuccess.jsp");

	}

}
