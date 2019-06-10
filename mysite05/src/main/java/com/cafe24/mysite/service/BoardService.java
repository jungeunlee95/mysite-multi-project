package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.dto.FindCriteria;
import com.cafe24.mysite.dto.PagingMaker;
import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> getList() {
		return boardDao.getList();
	}
	
	public PagingMaker getPagingMaker(FindCriteria fCri) {
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(fCri);
		pagingMaker.setTotalData(findCountData(fCri));
		return pagingMaker;
	}

		
	public List<BoardVo> listFind(FindCriteria findCri) {
		return boardDao.listFind(findCri);
	}

	public int findCountData(FindCriteria findCri){
		return boardDao.findCountData(findCri);
	}

	
	public boolean writeBoard(BoardVo vo) {
		return boardDao.insert(vo);
	}
	
	public boolean viewCount(Long no) {
		return boardDao.viewCount(no);
	}

	public boolean writeReply(BoardVo vo) {
		BoardVo master = getBoardView(vo.getGroupNo());
		vo.setGroupNo(master.getGroupNo());
		vo.setOrderNo(master.getOrderNo());
		vo.setDepth(master.getDepth());
		return boardDao.reply(vo);
	}

	public boolean delete(Long no) {
		return boardDao.delete(no);
	}

	public boolean deleteMaster(Long no) {
		return boardDao.deleteMaster(no);
	}

	public BoardVo getBoardView(Long no) {
		return boardDao.getView(no);
	}

	public boolean modifyBoard(BoardVo vo) {
		return boardDao.update(vo);
	}

}
