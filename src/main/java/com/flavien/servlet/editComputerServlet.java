package com.flavien.servlet;

import java.io.IOException;
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
 * Servlet implementation class editComputerServlet
 */
@WebServlet("/editComputer")
public class editComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerServiceImpl computerService;
	private CompanyServiceImpl companyService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editComputerServlet() {
		this.computerService = ServiceManager.INSTANCE.getComputerServiceImpl();
		this.companyService = ServiceManager.INSTANCE.getCompanyServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		int companyId = Utils.getInt(request.getParameter("companyId"));
		if(name.equals("dd"))
			name = "dd";

	}

}
