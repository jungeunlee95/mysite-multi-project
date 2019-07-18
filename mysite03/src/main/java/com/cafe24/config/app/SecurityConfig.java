package com.cafe24.config.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
   Security Filter Chain

	 1. ChannelProcessingFilter
	 2. SecurityContextPersistenceFilter		( auto-config default )
	 3. ConcurrentSessionFilter
	 4. LogoutFilter							( auto-config default )
	 5. UsernamePasswordAuthenticationFilter	( auto-config default )
	 6. DefaultLoginPageGeneratingFilter		( auto-config default )
	 7. CasAuthenticationFilter
	 8. BasicAuthenticationFilter			  	( auto-config default )
	 9. RequestCacheAwareFilter					( auto-config default )
	10. SecurityContextHolderAwareRequestFilter	( auto-config default )
	11. JaasApiIntegrationFilter
	12. RememberMeAuthenticationFilter
	13. AnonymousAuthenticationFilter			( auto-config default )
	14. SessionManagementFilter					( auto-config default )
	15. ExceptionTranslationFilter				( auto-config default )
	16. FilterSecurityInterceptor				( auto-config default )
*/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;

	// [ 스프링 시큐리티 필터 연결 ] 
	// WebSecurity 객체는 
	// springSecurityFilterChain이라는 이름의 
	// DelegatingFilterProxy Bean 객체를 생성
	// DelegatingFilterProxy Bean은 많은 Spring Security Filter Chain에 역할을 위임한다.

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		//super.configure(web);

		// 스프링 시큐리티의 필터 연결을 설정하기 위한 오버라이딩이다.
		// 예외가 웹접근 URL를 설정한다.
		// ACL(Access Control List - 접근 제어 목록)에 등록하지 않을  URL을 예외 설정
		
//		web.ignoring().antMatchers("/assets/**");
//		web.ignoring().antMatchers("/favicon.ico");
		
		web.ignoring().regexMatchers("\\A/assets/.*\\Z");
		web.ignoring().regexMatchers("\\A/favicon.ico\\Z"); 
	}

	/*
	인증( Authenticated ): (ROLE_USER, ROLE_ADMIN) -> 둘 중 하나 필요
	> - /user/logout
	> - /user/update
	> - /board/write
	> - /board/delete
	> - /board/modify
	
	권한( Authorized ) : ROLE_ADMIN
	> - /admin/**  
	
	인증/권한이 필요 없음
	> - allow all 
	*/
	
	// Interceptor URL의 요청을 안전하게 보호(보안)하는 방법을 설정
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		// 인터셉터로 요청을 안전하게 보호하는 방법을 설정하기 위한 오버라이딩이다.
		//
		// 1. ACL 설정
		//
		http.authorizeRequests()  // 모두 다 인증이 되어있어야한다.
		
		// 인증이 되어있을 때 (Authenticated?)
		.antMatchers("/user/update", "/user/logout").authenticated()
		.antMatchers("/board/write", "/board/delete", "/board/modify").authenticated()
		
		// ADMIN Authorization(ADMIN 권한, ROLE_ADMIN) - 3가지 방법
//		.antMatchers("/admin/**").hasRole("ROLE_ADMIN")
//		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN'")
		.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
		
		// 모두 허용 ( 위에서 걸리면(url 매칭)-저기있는 URL이 아니라면, 밑으로 안내려옴 ) - 2가지 방법
//		.antMatchers("/**").permitAll(); 
		.anyRequest().permitAll();
		

		// Temporary for Testing
		http.csrf().disable();

		
		//
		// 2. 로그인 설정
		//
		http
		.formLogin()
		.loginPage("/user/login")
		.loginProcessingUrl("/user/auth")  // view form의 action과 맞아야함
		.failureUrl("/user/login?result=fail")
		.defaultSuccessUrl("/", true)
		.usernameParameter("email")
		.passwordParameter("password");
		
		//
		// 3. 로그아웃 설정
		//
		http
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true);
	}

	// UserDetailService를 설정
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 사용자 세부 서비스를 설정하기 위한 오버라이딩이다.
		auth.userDetailsService(userDetailsService);
	}

	
	

}
