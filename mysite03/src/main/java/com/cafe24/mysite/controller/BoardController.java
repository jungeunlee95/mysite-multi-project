package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.dto.FindCriteria;
import com.cafe24.mysite.dto.PagingMaker;
import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "")
	public String list(Model model, FindCriteria fCri) {
		model.addAttribute("list", boardService.listFind(fCri));
		
		model.addAttribute("pagingMaker", boardService.getPagingMaker(fCri));
		
		model.addAttribute("findCountData", boardService.findCountData(fCri));
		return "board/list";		
	}
	
//	@Auth(role=Auth.Role.USER)
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(@AuthUser UserVo authUser,
						@ModelAttribute BoardVo boardVo,
						@ModelAttribute("fCri") FindCriteria fCri) {
		return "board/write";
	}

	@Auth
	@RequestMapping(value = "/write/{no}", method = RequestMethod.GET)
	public String write(@AuthUser UserVo authUser,
						@ModelAttribute("fCri") FindCriteria fCri, Model model, 
						@PathVariable(value = "no") Long no,
						@ModelAttribute BoardVo boardVo) {
		model.addAttribute("groupNo", no);
		return "board/write";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser,
						@ModelAttribute("fCri") FindCriteria fCri,
						Model model,
						@ModelAttribute @Valid BoardVo boardVo,
						BindingResult result) {
		if (result.hasErrors()) {
			List<ObjectError> list =  result.getAllErrors();
			for(ObjectError error : list) {
				System.out.println(error);
			}
			if(boardVo.getGroupNo()!=null) {
				model.addAttribute("groupNo", boardVo.getGroupNo());
			}
			model.addAllAttributes(result.getModel());
			return "board/write";					
		}else {
			if (boardVo.getGroupNo() == null) {
				boardService.writeBoard(boardVo);
			} else {
				boardService.writeReply(boardVo);
			}
			return "redirect:/board";
		}
	}

	@RequestMapping(value = "/view/{no}")
	public String view(@PathVariable(value = "no") Long no,
					   @ModelAttribute("fCri") FindCriteria fCri, 
					   Model model,
					   HttpServletRequest request, 
					   HttpServletResponse response, 
					   HttpSession session) {
		model.addAttribute("boardVo", boardService.getBoardView(no));
		
		// 조회수 쿠키 검사
		Cookie cookies[] = request.getCookies();
		// 비교 쿠기
		Cookie viewCookie = null;
		// 쿠키가 있을 경우
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("cookie"+no)) {
					viewCookie = cookie;
				}
			} 
		}
		// 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
		// viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
        if (viewCookie == null) {         
            // 쿠키 생성(이름, 값)
            Cookie newCookie = new Cookie("cookie"+no, "|" + no + "|"); 
            newCookie.setMaxAge(24*60*60);
            // 쿠키 추가
            response.addCookie(newCookie);
            // 쿠키를 추가 시키고 조회수 증가시킴
            boardService.viewCount(no);
        }
		
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value = "/delete/{no}")
	public String delete(@AuthUser UserVo authUser,
						 @PathVariable(value = "no") Long no, Model model,
						 @ModelAttribute("fCri") FindCriteria fCri) {
		Boolean result = null;
		BoardVo boardVo = boardService.getBoardView(no);
		if (boardVo.getMaster() == 1) {
			result = boardService.deleteMaster(boardVo.getGroupNo());
		} else {
			result = boardService.delete(no);
		}
		String message = result == true ? "success" : "fail";
		model.addAttribute("message", message);
		return "redirect:/board";
	}

	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser,
						 @PathVariable(value = "no") Long no, 
						 Model model, 
						 @ModelAttribute("fCri") FindCriteria fCri) {
		BoardVo boardVo = boardService.getBoardView(no);
		if(boardVo.getUserNo()!=authUser.getNo()) {
			return "redirect:/board";
		}
		model.addAttribute("boardVo", boardVo);
		return "board/modify";
	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@AuthUser UserVo authUser,
						 BoardVo vo, 
						 Model model, 
						 @ModelAttribute("fCri") FindCriteria fCri) {
		boardService.modifyBoard(vo);
		BoardVo boardVo = boardService.getBoardView(vo.getNo());
		model.addAttribute("boardVo", boardVo);
		return "redirect:/board/view/" + vo.getNo();
	}

}
