package com.flavien.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flavien.dto.ComputerDTO;
import com.flavien.dto.ComputerMapperDTO;
import com.flavien.dto.validators.DtoValidator;
import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.service.CompanyService;
import com.flavien.service.ComputerService;
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

	private ComputerService computerService;
	private CompanyService companyService;

	/**
	 * Initialization of the services.
	 */
	public EditComputerServlet() {
		this.computerService = ServiceManager.INSTANCE.getComputerServiceImpl();
		this.companyService = ServiceManager.INSTANCE.getCompanyServiceImpl();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Get all the parameters from the view.
		final String name = request.getParameter("name");
		final String introducedString = request.getParameter("introduced");
		final String discontinuedString = request.getParameter("discontinued");
		final int id = Utils.getInt(request.getParameter("id"));
		final int companyId = Utils.getInt(request.getParameter("companyId"));	
		
		ComputerDTO computerDTO = new ComputerDTO.Builder()
			.id(id)
			.name(name)
			.introduced(introducedString)
			.discontinued(discontinuedString)
			.company(new Company.Builder().id(companyId).build())
			.build();
		
		// validate the DTO
		List<String> errors = DtoValidator.validate(computerDTO);
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
