package com.cafe24.mysite.vo;

public class SiteVo {
	private Long no;
	private String title;
	private String welcomMessage;
	private String profile;
	private String description;

	public SiteVo() {
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

	public String getWelcomMessage() {
		return welcomMessage;
	}

	public void setWelcomMessage(String welcomMessage) {
		this.welcomMessage = welcomMessage;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SiteVo [no=" + no + ", title=" + title + ", welcomMessage=" + welcomMessage + ", profile=" + profile
				+ ", description=" + description + "]";
	}

}
