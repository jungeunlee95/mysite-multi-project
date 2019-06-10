package com.cafe24.mysite.controller;

import java.util.List;

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

import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="")
	public String list(Model model, @ModelAttribute GuestbookVo guestbookVo) {
		List<GuestbookVo> list = guestbookService.getList();
		model.addAttribute("list", list);
		return "guestbook/list"; 
	}
	
	@RequestMapping(value="/add")
	public String add(@ModelAttribute @Valid GuestbookVo guestbookVo, 
					  BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> list =  result.getAllErrors();
			for(ObjectError error : list) {
				System.out.println(error);
			}
			List<GuestbookVo> list2 = guestbookService.getList();
			model.addAttribute("list", list2);
			model.addAllAttributes(result.getModel());
			return "guestbook/list";
		}
		guestbookService.addGuestbook(guestbookVo);
		return "redirect:/guestbook";
	}
	
//	@RequestMapping(value="/delete/{no}", method = RequestMethod.GET)
//	public String delete(@PathVariable(value="no") Long no, Model model) {
//		model.addAttribute("no", no);
//		return "guestbook/delete";
//	}
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String delete(@ModelAttribute(value="no") Long no) {
		return "guestbook/delete";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(GuestbookVo vo) {
		guestbookService.deleteGuestbook(vo);
		return "redirect:/guestbook";
	}

}













