package com.flavien.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flavien.dto.validators.DtoValidator;
import com.flavien.models.Page;
import com.flavien.service.CompanyService;
import com.flavien.service.ComputerService;
import com.flavien.utils.Utils;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	private final static Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DtoValidator dtoValidator;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(
			@RequestParam(value = "index", required = false, defaultValue = "0") int index,
			@RequestParam(value = "nbEntityByPage", required = false, defaultValue = "100") int nbEntityByPage,
			@RequestParam(value = "search", required = false, defaultValue = "") String search, ModelMap map) {

		if (nbEntityByPage == Utils.ERROR)
			nbEntityByPage = Page.DEFAULT_NB_ENTITY_BY_PAGE;

		// Get a page and send back to user to the view
		Page page = new Page();
		page.setIndex(index);
		page.setEntityByPage(nbEntityByPage);
		page = this.computerService.getByPage(page, search);

		map.addAttribute("page", page);
		map.addAttribute("search", search);

		logger.info("return the page to the dashboard");

		return "dashboard";
	}
}