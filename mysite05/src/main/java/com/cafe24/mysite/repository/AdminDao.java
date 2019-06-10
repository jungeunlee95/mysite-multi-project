package com.cafe24.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.SiteVo;

@Repository
public class AdminDao {
	@Autowired
	private SqlSession sqlSession;

	public SiteVo getMain() {
		SiteVo result = sqlSession.selectOne("admin.getMain");
		return result;
	}
}
