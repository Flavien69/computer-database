package com.flavien.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Abstract class to init the application context spring in the servlet.
 *
 */
 public abstract class AbstractSpringHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override	
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
}
