package com.cafe24.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.action.guestbook.GuestbookActionFactory;
import com.cafe24.web.mvc.Action;


// @WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// post 방식 인코딩은 기본
		// request.setCharacterEncoding("utf-8");-> fliter에서 처리

		String actionName = request.getParameter("a");		
		Action action = new GuestbookActionFactory().getAction(actionName);
		action.execute(request, response);
		
	}

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
