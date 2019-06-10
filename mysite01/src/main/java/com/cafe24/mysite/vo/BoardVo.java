package com.cafe24.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String author;
	private Long viewCount;
	private String regDate;

	public BoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", author=" + author + ", viewCount=" + viewCount
				+ ", regDate=" + regDate + "]";
	}

}
