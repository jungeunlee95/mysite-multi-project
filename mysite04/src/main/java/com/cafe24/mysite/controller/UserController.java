package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error);
			}
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.joinUser(userVo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "result", required = false) String result, Model model) {
		model.addAttribute("result", result);
		return "user/login";
	}

	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(
			@AuthUser UserVo authUser, 
			Model model) {
		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, 
						@ModelAttribute UserVo userVo, 
						Model model) {
		userService.updateUser(userVo);
		// session의 authUser 변경
		authUser.setName(userVo.getName());
		return "redirect:/user/update?result=success";
	}
	
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public void auth() {
	}
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout() {
	}

// ------------------------------- [인터셉터로 넘긴코드들]  ------------------------------
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(@RequestParam(value = "email", required = true, defaultValue = "") String email,
//			@RequestParam(value = "password", required = true, defaultValue = "") String password, HttpSession session,
//			Model model) {
//		// UserVo authUser = userDao.get(email,password);
//		UserVo userVo = new UserVo(email, password);
//		UserVo authUser = userService.getUser(userVo);
//
//		if (authUser == null) {
//			// data 넘기기
//			model.addAttribute("result", "fail");
//			return "user/login";
//		}
//
//		// session 처리
//		session.setAttribute("authUser", authUser);
//
//		return "redirect:/";
//	}
//
//	@RequestMapping(value = "/logout")
//	public String logout(HttpSession session) {
//		session.removeAttribute("authUser");
//		session.invalidate();
//		return "redirect:/";
//	}
}
