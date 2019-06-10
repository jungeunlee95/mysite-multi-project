package com.cafe24.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class DeleteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String no = request.getParameter("no");
		request.setAttribute("no", no);
		WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
		

	}

}
