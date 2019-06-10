package com.cafe24.mysite.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// list action
		GuestbookDao dao = new GuestbookDao();
		List<GuestbookVo> list = dao.getList();
		// 공유객체
		request.setAttribute("list", list);
		WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");

	}

}
