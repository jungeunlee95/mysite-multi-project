package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		///////////////////// 접근제어 -> 로그인 상태 아니면 안됨 ///////////////////
		HttpSession session = request.getSession();
		if(session == null) {
			WebUtil.redirect(request, response, request.getContextPath());
			return;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			WebUtil.redirect(request, response, request.getContextPath());
			return;
		}
		/////////////////////////////////////////////////////////////////
		 
		Long userNo = authUser.getNo();
		UserVo userVo = new UserDao().get(userNo);

		request.setAttribute("userVo", userVo);

		WebUtil.forward(request, response, "/WEB-INF/views/user/updateform.jsp");
	}

}