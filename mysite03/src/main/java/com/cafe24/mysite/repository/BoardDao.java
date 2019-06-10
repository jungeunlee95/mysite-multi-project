package com.cafe24.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.dto.FindCriteria;
import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;

	public boolean update(BoardVo vo) {
		return 1 == sqlSession.update("board.update", vo);
	}
	
	public boolean viewCount(Long no) {
		return 1 == sqlSession.update("board.viewCount", no);
	}

	public BoardVo getView(Long no) {
		BoardVo result = sqlSession.selectOne("board.getView", no);
		return result;
	}

	public boolean delete(Long no) {
		return 1 == sqlSession.delete("board.delete", no);
	}

	public boolean deleteMaster(Long no) {
		return 0 != sqlSession.delete("board.deleteMaster", no);
	}

	public boolean insert(BoardVo vo) {
		boolean result = true;
		List<BoardVo> first = sqlSession.selectList("board.getList");
		if (first.size() == 0) {
			result = 1 == sqlSession.insert("board.insertFirst", vo);
		} else {
			result = 1 == sqlSession.insert("board.insert", vo);
		}
		return result;
	}

	public boolean reply(BoardVo vo) {
		boolean result = true;
		sqlSession.insert("board.replyOrderNo", vo);
		sqlSession.insert("board.reply", vo);
		return result;
	}

	public List<BoardVo> getList() {
		List<BoardVo> result = sqlSession.selectList("board.getList");
		return result;
	}

	public List<BoardVo> listFind(FindCriteria findCri) {
		List<BoardVo> result = sqlSession.selectList("board.listFind", findCri);
		
		return result; 
	} 

	public int findCountData(FindCriteria findCri){
		return sqlSession.selectOne("board.findCount", findCri);
	}

}
