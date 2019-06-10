package com.cafe24.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// list action
		BoardDao dao = new BoardDao();
		List<BoardVo> list = dao.getList();
		// 공유객체
		request.setAttribute("list", list);
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");

	}

}
