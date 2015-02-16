package com.flavien.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flavien.models.Page;
import com.flavien.service.impl.ComputerServiceImpl;
import com.flavien.service.impl.ServiceManager;
import com.flavien.utils.Utils;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerServiceImpl computerService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
		this.computerService = ServiceManager.INSTANCE.getComputerServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int index = Utils.getInt(request.getParameter("index"));
		int nbEntityByPage = Utils.getInt(request.getParameter("nbEntityByPage"));
		String name = request.getParameter("search");
		
		if( nbEntityByPage == Utils.ERROR)
			nbEntityByPage = Page.DEFAULT_NB_ENTITY_BY_PAGE;
		
		Page page = this.computerService.getByPage(index, nbEntityByPage, name);
		request.setAttribute("page", page);
		request.setAttribute("search", name);
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/views/dashboard.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idsToDelete = request.getParameter("selection");
		String[] array = idsToDelete.split(",");
		for (String idString : array){
			int id = Utils.getInt(idString);
			if (id != Utils.ERROR)
				this.computerService.deleteById(id);
		}
		response.sendRedirect(request.getContextPath()+"/dashboard");
	}

}
