package com.flavien.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flavien.dto.ComputerDTO;
import com.flavien.dto.ComputerMapperDTO;
import com.flavien.dto.validators.ComputerDtoValidator;
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
@WebServlet("/edit-computer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(EditComputerServlet.class);

	private ComputerServiceImpl computerService;
	private CompanyServiceImpl companyService;

	/**
	 * Initialization of the services.
	 */
	public EditComputerServlet() {
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
			ComputerDTO computerDTO = ComputerMapperDTO.toDto(computer);
			if(computer != null){
				request.setAttribute("computer", computerDTO);
				request.setAttribute("companies", companies);
			}
			else
				redirectView = "/views/404.jsp";
		}
		else
			redirectView = "/views/404.jsp";
		
		logger.info("Get the computer to edit");
		dispatch = getServletContext().getRequestDispatcher(redirectView);
		dispatch.forward(request, response);

	}

	/**
	 * Using to edit a computer in the database.
	 * Redirect to the dashboard if success or to the 500 error page.
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Get all the parameters from the view.
		String name = request.getParameter("name");
		String introducedString = request.getParameter("introduced");
		String discontinuedString = request.getParameter("discontinued");
		int id = Utils.getInt(request.getParameter("id"));
		int companyId = Utils.getInt(request.getParameter("companyId"));	
		
		ComputerDTO computerDTO = new ComputerDTO(id,name, introducedString, discontinuedString, companyId);
		
		// validate the DTO
		List<String> errors = ComputerDtoValidator.validate(computerDTO);
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			doGet(request, response);
			return;
		}
		
		// Edit the computer in the database.
		this.computerService.update(ComputerMapperDTO.fromDto(computerDTO));
		logger.info("Edit the computer");
		response.sendRedirect(request.getContextPath()+"/dashboard");

	}

}
