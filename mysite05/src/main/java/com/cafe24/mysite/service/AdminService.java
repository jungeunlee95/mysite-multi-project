package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.AdminDao;
import com.cafe24.mysite.vo.SiteVo;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	public SiteVo getMain() {
		return adminDao.getMain();
	}
}
