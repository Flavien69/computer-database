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

import com.flavien.dto.ComputerDTO;
import com.flavien.dto.ComputerMapperDTO;
import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.service.impl.CompanyServiceImpl;
import com.flavien.service.impl.ComputerServiceImpl;
import com.flavien.service.impl.ServiceManager;
import com.flavien.utils.Utils;

/**
 * Servlet implementation class addComputerServlet
 * Using this servlet to add a computer in the database.
 */
@WebServlet("/addComputer")
public class addComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerServiceImpl computerService;
	private CompanyServiceImpl companyService;    
	
    /**
     * Initialization of the services.
     */
    public addComputerServlet() {
		this.computerService = ServiceManager.INSTANCE.getComputerServiceImpl();
		this.companyService = ServiceManager.INSTANCE.getCompanyServiceImpl();
    }

	/**
	 * Using to redirect the user in the addComputer page with the companies initialisation.
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
	 * Using to add a computer in the database.
	 * Redirect to the dashboard if success or to the 500 error page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch;
		Boolean isSuccess = false;

		// Get all the parameters from the view.
		String name = request.getParameter("name");
		String introducedString = request.getParameter("introduced");
		String discontinuedStirng = request.getParameter("discontinued");
		int companyId = Utils.getInt(request.getParameter("companyId"));
		
		//LocalDateTime introduced = Utils.getLocalDateTime(request.getParameter("introduced"));
		//LocalDateTime discontinued = Utils.getLocalDateTime(request.getParameter("discontinued"));
		
		// Add the computer in the database.
		ComputerDTO computerDTO = new ComputerDTO(name, introducedString, discontinuedStirng, companyId);
		isSuccess = this.computerService.add(ComputerMapperDTO.fromDto(computerDTO));
		if (!isSuccess){
			dispatch = getServletContext().getRequestDispatcher("/views/500.jsp");
			dispatch.forward(request, response);
		}
		else
			response.sendRedirect(request.getContextPath()+"/dashboard");	
		}

}
