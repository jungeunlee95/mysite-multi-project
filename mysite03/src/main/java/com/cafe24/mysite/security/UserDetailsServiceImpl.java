package com.cafe24.mysite.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.UserVo;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVo userVo = userDao.get(username); 
		
		SecurityUser securityUser = new SecurityUser();
		
		if ( userVo != null ) { 
			securityUser.setNo(userVo.getNo());
			securityUser.setName(userVo.getName());         
			securityUser.setUsername(userVo.getEmail());     // principal
			securityUser.setPassword(userVo.getPassword());  // credetial
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userVo.getRole())));
			
			// 이전 setAuthorities 코드
//			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//			authorities.add(new SimpleGrantedAuthority(userVo.getRole()));
		}
		
		return securityUser;
	}

	

}
