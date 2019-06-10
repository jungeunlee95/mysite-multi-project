package com.cafe24.mysite.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {

	private Long no;

	@NotEmpty
	@Length(min = 2, max = 8)
	private String name;

	@Email
	@NotEmpty
	private String email;

	@NotNull
	@Length(min = 3)
	private String password;

	@NotNull
	private String gender;

	private String role;

	private String regDate;

	public UserVo() {
		super();
	}

	public UserVo(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", role=" + role + ", regDate=" + regDate + "]";
	}

}
