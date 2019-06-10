package com.cafe24.mysite.dto;

public class PageCriteria {
	
	private int page;  //시작페이지를 가르킨다.
	private int numPerPage; //페이지당 넘버를 말한다.
	
	
	public PageCriteria() {
		this.page =1;
		this.numPerPage=10;
	}
	
	public void setPage(int page) {
		if(page<=0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public void setNumPerPage(int numPerPage) {
		if(numPerPage<=0 || numPerPage>100) {
			this.numPerPage = 10;
			return;
		}
		this.numPerPage = numPerPage;
	}
	
	public int getPage() {
		return page;
	}
	
	public int getStartPage() {
		return (this.page*numPerPage)-(numPerPage-1)-1;
	}
	
	public int getNumPerPage() {
		return numPerPage;
	}
	
	@Override
	public String toString() {
		return "PageCriteria [page="+page+","+"numPerPage="+numPerPage+"]"; 
	}

}