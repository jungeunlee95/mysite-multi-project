package com.cafe24.mysite.dto;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PagingMaker {
	private int totalData; //전체 데이타 갯수
	private int startPage; //페이지목록의 시작번호
	private int endPage; //페이지 목록의 끝번호
	private boolean prev; //이전 버튼을 나타내는 부울 값
	private boolean next; // 다음버튼을 나타내는 부울 값
	private int displayPageNum = 5;  //페이지목록에 나타낼 페이지 번호의 수
	
	private PageCriteria cri;  //PageCriteria의 vo를 쓸 수 있고 멤버변수를 가져오는 것이다.
	
	public void setCri(PageCriteria cri) {
		this.cri = cri;
	}
	
	
	//이 부분이 굉장히 중요한 부분이다.!!
	public void setTotalData(int totalData) {
		this.totalData = totalData;
		
		getPagingData();
	}
	
	private void getPagingData() {
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		
		startPage =(endPage - displayPageNum) + 1;
		
		int finalEndPage = (int)(Math.ceil(totalData/(double)cri.getNumPerPage()));
		
		if(endPage > finalEndPage) {
			endPage= finalEndPage;
		}
		
		prev = startPage ==1? false: true;
		next = endPage*cri.getNumPerPage() >= totalData ? false:true;
	
	}//getPagingData()

	//numPerPage를 받기위한 URI만드는 코드 
	public String makeURI(int page) {
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("numPerPage", cri.getNumPerPage())
				.build();
		return uriComponents.toUriString();
	}
	
	public String makeFind(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
			.queryParam("page", page)
			.queryParam("numPerPage", ((FindCriteria)cri).getNumPerPage())
			.queryParam("findType", ((FindCriteria)cri).getFindType())
			.queryParam("keyword", ((FindCriteria)cri).getKeyword())
			.build();
		
		return uriComponents.toString();

	}
	
	//setter getter
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalData() {
		return totalData;
	}

	public PageCriteria getCri() {
		return cri;
	}
	
	
	
	
}