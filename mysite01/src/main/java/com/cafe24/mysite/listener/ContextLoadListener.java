package com.cafe24.mysite.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//@WebListener
public class ContextLoadListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		String contextConfigLocation =
				servletContextEvent.getServletContext().
				getInitParameter("contextConfigLocation");
		System.out.println("Container starts..... " + contextConfigLocation);
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

}
