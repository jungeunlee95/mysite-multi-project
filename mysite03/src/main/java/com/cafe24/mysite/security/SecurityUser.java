package com.cafe24.mysite.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails {
	
	// domain fields(principal: 보호할 사용자 중요 데이터)
	private Long no;
	private String name;  // biz data

	// security fields
	private Collection<? extends GrantedAuthority> authorities;
	private String username;  // credential(email)
	private String password;  // credential
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// ROLE
		return authorities;
	}

	
	public Long getNo() {
		return no;
	}


	public void setNo(Long no) {
		this.no = no;
	}


	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	
	// -------------- 계정에 대한 디테일한 설정 -----------------
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	// -----------------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	


	
	
	
}
