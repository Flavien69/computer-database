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
 * Servlet implementation class addComputerServlet
 */
@WebServlet("/addComputer")
public class addComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerServiceImpl computerService;
	private CompanyServiceImpl companyService;    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addComputerServlet() {
		this.computerService = ServiceManager.INSTANCE.getComputerServiceImpl();
		this.companyService = ServiceManager.INSTANCE.getCompanyServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectView = "/views/addComputer.jsp";
		List<Company> companies = this.companyService.getAll();
		request.setAttribute("companies", companies);
		RequestDispatcher dispatch;	
		dispatch = getServletContext().getRequestDispatcher(redirectView);
		dispatch.forward(request, response);	
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectView = "/views/dashboard.jsp";
		RequestDispatcher dispatch;
		Boolean isSuccess = false;

		String name = request.getParameter("name");
		LocalDateTime introduced = Utils.getLocalDateTime(request.getParameter("introduced"));
		LocalDateTime discontinued = Utils.getLocalDateTime(request.getParameter("discontinued"));
		int companyId = Utils.getInt(request.getParameter("companyId"));		
		isSuccess = this.computerService.add(new Computer(name, introduced, discontinued, companyId));
		if (!isSuccess)
			redirectView = "/views/500.jsp";

		dispatch = getServletContext().getRequestDispatcher(redirectView);
		dispatch.forward(request, response);	}

}
