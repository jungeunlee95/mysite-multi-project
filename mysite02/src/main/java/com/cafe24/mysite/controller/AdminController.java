package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mysite.service.AdminService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Auth(role=Auth.Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping({"","/main"})
	public String main(@AuthUser UserVo authUser) {
		return "admin/main";
	}
	@RequestMapping("/main/update")
	public String mainUpdate(@AuthUser UserVo authUser) {
		return "admin/main";
	}

	@RequestMapping("/user")
	public String user(@AuthUser UserVo authUser) {
		return "admin/user";
	}
	
	@RequestMapping("/board")
	public String board(@AuthUser UserVo authUser) {
		return "admin/board";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook(@AuthUser UserVo authUser) {
		return "admin/guestbook";
	}
}
