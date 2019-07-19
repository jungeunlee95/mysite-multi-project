package com.cafe24.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.config.app.AppSecurityConfig;
import com.cafe24.config.app.DBConfig;
import com.cafe24.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({
	"com.cafe24.mysite.service", 
	"com.cafe24.mysite.repository", 
	"com.cafe24.mysite.security", 
	"com.cafe24.mysite.aspect"})
@Import({DBConfig.class, MyBatisConfig.class, AppSecurityConfig.class})
public class AppConfig {

}
