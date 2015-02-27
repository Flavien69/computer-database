package com.flavien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flavien.dto.validators.DtoValidator;
import com.flavien.models.Company;
import com.flavien.service.CompanyService;
import com.flavien.service.ComputerService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DtoValidator dtoValidator;
	
	@RequestMapping(method = RequestMethod.GET)
    public String helloWorld(ModelMap map) {
		List<Company> companies = this.companyService.getAll();
		map.addAttribute("companies", companies);
        return "addComputer";
    }
}
