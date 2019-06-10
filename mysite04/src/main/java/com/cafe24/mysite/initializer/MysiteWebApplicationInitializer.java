package com.cafe24.mysite.initializer;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.cafe24.mysite.config.AppConfig;
import com.cafe24.mysite.config.WebConfig;
public class MysiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected String[] getServletMappings() {
        //servlet 매핑
		return new String[] {"/"};
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
        // root Application Context
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
        // Web Application Context
		return new Class<?>[] { WebConfig.class };
	}
    
    @Override
	protected Filter[] getServletFilters() {
        // Filter
		return new Filter[] { new CharacterEncodingFilter("UTF-8", true) };
	}

	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		//createDispatcherServlet
		DispatcherServlet dispatcherServlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
		// exception hadler가 없으면 error 던진다 true
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		
		return dispatcherServlet;
	}
    
    
}