package com.flavien.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flavien.models.Page;
import com.flavien.service.ComputerService;
import com.flavien.service.impl.ServiceManager;
import com.flavien.utils.Utils;

/**
 * Servlet implementation class DashboardServlet
 * Using this servlet list the computers filtering by name and per page.
 * Using to delete computers from the database.
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
	private ComputerService computerService;

    /**
     * Initialization of the services.
     */
    public DashboardServlet() {
		this.computerService = ServiceManager.INSTANCE.getComputerServiceImpl();
    }

	/**
	 * Using to get a page of computers filtering by name.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get all the parameters from the view.
		final int index = Utils.getInt(request.getParameter("index"));
		int nbEntityByPage = Utils.getInt(request.getParameter("nbEntityByPage"));
		String name = request.getParameter("search");
		if (name == null)
			name = "";
		
		if( nbEntityByPage == Utils.ERROR)
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
