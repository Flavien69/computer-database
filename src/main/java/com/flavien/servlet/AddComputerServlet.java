package com.flavien.servlet;

import java.io.IOException;
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

import com.flavien.dao.impl.CompanyDaoImpl;
import com.flavien.dto.ComputerDTO;
import com.flavien.dto.ComputerMapperDTO;
import com.flavien.dto.validators.ComputerDtoValidator;
import com.flavien.models.Company;
import com.flavien.service.impl.CompanyServiceImpl;
import com.flavien.service.impl.ComputerServiceImpl;
import com.flavien.service.impl.ServiceManager;
import com.flavien.utils.Utils;

/**
 * Servlet implementation class addComputerServlet Using this servlet to add a
 * computer in the database.
 */
@WebServlet("/add-computer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(AddComputerServlet.class);

	private ComputerServiceImpl computerService;
	private CompanyServiceImpl companyService;

	/**
	 * Initialization of the services.
	 */
	public AddComputerServlet() {
		this.computerService = ServiceManager.INSTANCE.getComputerServiceImpl();
		this.companyService = ServiceManager.INSTANCE.getCompanyServiceImpl();
	}

	/**
	 * Using to redirect the user in the addComputer page with the companies
	 * initialisation.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String redirectView = "/views/addComputer.jsp";
		List<Company> companies = this.companyService.getAll();
		request.setAttribute("companies", companies);
		RequestDispatcher dispatch;
		dispatch = getServletContext().getRequestDispatcher(redirectView);
		
		logger.info("Get the view for the add computer");
		dispatch.forward(request, response);
	}

	/**
	 * Using to add a computer in the database. Redirect to the dashboard if
	 * success or to the 500 error page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		// Get all the parameters from the view.
		final String name = request.getParameter("name");
		final String introducedString = request.getParameter("introduced");
		final String discontinuedString = request.getParameter("discontinued");
		final int companyId = Utils.getInt(request.getParameter("companyId"));

		ComputerDTO computerDTO = new ComputerDTO(name, introducedString, discontinuedString, companyId);

		// validate the DTO
		List<String> errors = ComputerDtoValidator.validate(computerDTO);
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			doGet(request, response);
			return;
		}

		// Add the computer in the database.
		this.computerService.add(ComputerMapperDTO.fromDto(computerDTO));
		logger.info("add a computer success");

		response.sendRedirect(request.getContextPath() + "/dashboard");
	}

}
