package com.flavien.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flavien.service.impl.ComputerServiceImpl;
import com.flavien.service.impl.ServiceManager;
import com.flavien.utils.Utils;

/**
 * Servlet implementation class DeleteComputerServlet.
 * Using this servlet to delete a computer in the database.
 */
@WebServlet("/delete-computer")
public class DeleteComputerServlet extends HttpServlet {

	private static final long serialVersionUID = -6224096912496986500L;
	private final static Logger logger = LoggerFactory.getLogger(DeleteComputerServlet.class);
	private ComputerServiceImpl computerService;

    /**
     * Initialization of the services.
     */
	public DeleteComputerServlet(){
		this.computerService = ServiceManager.INSTANCE.getComputerServiceImpl();

	}
	
	/**
	 * Using to delete computers from the database.
	 * Redirect to the dashboard.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idsToDelete = request.getParameter("selection");
		String[] array = idsToDelete.split(",");
		for (String idString : array){
			int id = Utils.getInt(idString);
			if (id != Utils.ERROR)
				this.computerService.deleteById(id);
		}
		logger.info("delete the computer "+idsToDelete);
		response.sendRedirect(request.getContextPath()+"/dashboard");
	}
}
