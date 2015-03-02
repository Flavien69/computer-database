package com.flavien.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.flavien.models.Page;
import com.flavien.service.ComputerService;
import com.flavien.utils.Utils;

/**
 * Servlet implementation class DashboardServlet Using this servlet list the
 * computers filtering by name and per page. Using to delete computers from the
 * database.
 */
@WebServlet("/dashboardx")
public class DashboardServlet extends AbstractSpringHttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

	@Autowired
	private ComputerService computerService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		// Get all the parameters from the view.
		final int index = Utils.getInt(request.getParameter("index"));
		int nbEntityByPage = Utils.getInt(request.getParameter("nbEntityByPage"));
		final String name = request.getParameter("search") == null ? "" : request.getParameter("search");

		if (nbEntityByPage == Utils.ERROR)
			nbEntityByPage = Page.DEFAULT_NB_ENTITY_BY_PAGE;

		// Get a page and send back to user to the view
		Page page = new Page();
		page.setIndex(index);
		page.setEntityByPage(nbEntityByPage);
		page = this.computerService.getByPage(page, name);

		request.setAttribute("page", page);
		request.setAttribute("search", name);

		logger.info("return the page to the dashboard");
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/views/dashboard.jsp");
		dispatch.forward(request, response);
	}
}
