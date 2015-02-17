package com.flavien.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.service.impl.CompanyServiceImpl;
import com.flavien.service.impl.ComputerServiceImpl;
import com.flavien.service.impl.ServiceManager;
import com.flavien.utils.Utils;

/**
 * Servlet implementation class editComputerServlet.
 * Using this servlet to edit a computer in the database.
 */
@WebServlet("/editComputer")
public class editComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerServiceImpl computerService;
	private CompanyServiceImpl companyService;

	/**
	 * Initialization of the services.
	 */
	public editComputerServlet() {
		this.computerService = ServiceManager.INSTANCE.getComputerServiceImpl();
		this.companyService = ServiceManager.INSTANCE.getCompanyServiceImpl();
	}

	/**
	 * Using to redirect the user in the editComputer page with the companies initialisation.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int computerId = Utils.getInt(request.getParameter("id"));
		String redirectView = "/views/editComputer.jsp";
		RequestDispatcher dispatch;
		
		List<Company> companies = this.companyService.getAll();
		if(computerId != Utils.ERROR){
			Computer computer = this.computerService.getByID(computerId);
			if(computer != null){
				request.setAttribute("computer", computer);
				request.setAttribute("companies", companies);
			}
			else
				redirectView = "/views/404.jsp";
		}
		else
			redirectView = "/views/404.jsp";
		
		dispatch = getServletContext().getRequestDispatcher(redirectView);
		dispatch.forward(request, response);

	}

	/**
	 * Using to edit a computer in the database.
	 * Redirect to the dashboard if success or to the 500 error page.
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch;
		Boolean isSuccess = false;

		// Get all the parameters from the view.
		String name = request.getParameter("name");
		LocalDateTime introduced = Utils.getLocalDateTime(request.getParameter("introduced"));
		LocalDateTime discontinued = Utils.getLocalDateTime(request.getParameter("discontinued"));
		int id = Utils.getInt(request.getParameter("id"));
		int companyId = Utils.getInt(request.getParameter("companyId"));		
		
		// Edit the computer in the database.
		isSuccess = this.computerService.update(new Computer(id,name, introduced, discontinued, companyId));
		if (!isSuccess){
			dispatch = getServletContext().getRequestDispatcher("/views/500.jsp");
			dispatch.forward(request, response);
		}
		else
			response.sendRedirect(request.getContextPath()+"/dashboard");

	}

}
