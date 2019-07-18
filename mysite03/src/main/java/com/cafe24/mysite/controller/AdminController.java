package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mysite.security.AuthUser;
import com.cafe24.mysite.security.SecurityUser;
import com.cafe24.mysite.service.AdminService;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping({"","/main"})
	public String main(@AuthUser SecurityUser securityUser) {
		return "admin/main";
	}
	@RequestMapping("/main/update")
	public String mainUpdate(@AuthUser SecurityUser securityUser) {
		return "admin/main";
	}

	@RequestMapping("/user")
	public String user(@AuthUser SecurityUser securityUser) {
		return "admin/user";
	}
	
	@RequestMapping("/board")
	public String board(@AuthUser SecurityUser securityUser) {
		return "admin/board";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook(@AuthUser SecurityUser securityUser) {
		return "admin/guestbook";
	}
}
