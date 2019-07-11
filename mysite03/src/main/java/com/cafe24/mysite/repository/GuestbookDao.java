package com.cafe24.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	private static int COUNT_PER_PAGE = 5;
	
	@Autowired 
	private SqlSession sqlSession;
	
	public boolean delete(GuestbookVo vo) {
		return 1==sqlSession.delete("guestbook.delete", vo);

	}
	
	public boolean insert(GuestbookVo vo) {
		return 1==sqlSession.insert("guestbook.insert", vo);

	}
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> result = sqlSession.selectList("guestbook.getList");
		return result;
	}
	 
	public List<GuestbookVo> getList(Long startIndex) {
		List<GuestbookVo> result = sqlSession.selectList("guestbook.getList2", (startIndex-1)*COUNT_PER_PAGE);
		return result; 
	}


}
