package com.cafe24.mysite.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@RestController("guestbookAPIController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
   
   @Autowired
   private GuestbookService guestbookService;
   
   @RequestMapping(value="/list/{no}", method=RequestMethod.GET)
   public JSONResult list(@PathVariable(value="no") Long page) {
      List<GuestbookVo> list = guestbookService.getList(page);
      return JSONResult.success(list);
   }

   
}