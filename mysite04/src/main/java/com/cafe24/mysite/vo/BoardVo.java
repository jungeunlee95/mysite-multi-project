package com.cafe24.mysite.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class BoardVo {
	private Long no;
	
	@NotNull
	private Long userNo;
	
	@NotEmpty
	private String title;
	
	private String author;
	
	@NotEmpty
	private String content;
	
	private Long viewCount;
	private String regDate;
	private String updateDate;
	private Long groupNo;
	private Long orderNo;
	private Long depth;
	private Long master;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Long getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(Long groupNo) {
		this.groupNo = groupNo;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Long getDepth() {
		return depth;
	}

	public void setDepth(Long depth) {
		this.depth = depth;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public Long getMaster() {
		return master;
	}

	public void setMaster(Long master) {
		this.master = master;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", userNo=" + userNo + ", title=" + title + ", author=" + author + ", content="
				+ content + ", viewCount=" + viewCount + ", regDate=" + regDate + ", updateDate=" + updateDate
				+ ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth=" + depth + ", master=" + master + "]";
	}



}
