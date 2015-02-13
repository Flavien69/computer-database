package com.flavien.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flavien.models.Computer;
import com.flavien.service.impl.ComputerServiceImpl;
import com.flavien.service.impl.ServiceManager;

/**
 * Servlet implementation class SynthetiseurFluxServlet
 */
@WebServlet("/computers")
public class ComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerServiceImpl computerService;

	public ComputerServlet() {
		this.computerService = ServiceManager.INSTANCE.getComputerServiceImpl();
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("coucou");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Computer computer = (Computer) request.getSession().getAttribute("computer");
		computerService.add(computer);
		
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/view/addComputer.jsp");
		dispatch.forward(request, response);

	}

}
