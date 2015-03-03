package com.flavien.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flavien.dto.ComputerDTO;
import com.flavien.dto.ComputerMapperDTO;
import com.flavien.models.Company;
import com.flavien.service.CompanyService;
import com.flavien.service.ComputerService;

@Controller
@RequestMapping("/add-computer")
public class AddComputerController {
	private final static Logger logger = LoggerFactory.getLogger(AddComputerController.class);

	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerMapperDTO computerMapperDTO;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(ModelMap map) {
		List<Company> companies = this.companyService.getAll();
		map.addAttribute("companies", companies);
		map.addAttribute("computerDTO", new ComputerDTO.Builder().build());
		logger.info("Get the view for the add computer");
		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@ModelAttribute("computerDTO") @Valid ComputerDTO computerDTO,
			BindingResult bindingResult, @RequestParam("companyId") int companyId, ModelMap map) {

		if (bindingResult.hasErrors()) {
			List<Company> companies = this.companyService.getAll();
			map.addAttribute("companies", companies);
			return "addComputer";
		}

		Company company = new Company.Builder().id(companyId).build();
		computerDTO.setCompany(company);
		this.computerService.add(computerMapperDTO.fromDto(computerDTO));
		logger.info("add the computer");
		return "redirect:/dashboard";
	}
}