package org.cdac.web;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextHandler implements ServletContextListener {

	
	
	@Override
	public void contextDestroyed (ServletContextEvent sce) {
		System.out.println("Application Destroyed");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Application started");
	}
}
