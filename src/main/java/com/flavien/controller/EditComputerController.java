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
import com.flavien.dto.mapper.ComputerMapperDTO;
import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.service.CompanyService;
import com.flavien.service.ComputerService;

@Controller
@RequestMapping("/edit-computer")
public class EditComputerController {
	private final static Logger logger = LoggerFactory.getLogger(EditComputerController.class);

	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerMapperDTO computerMapperDTO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(@RequestParam("id") int id, ModelMap map) {
		List<Company> companies = this.companyService.getAll();
		Computer computer = this.computerService.getByID(id);
		ComputerDTO computerDTO = computerMapperDTO.toDto(computer);

		map.addAttribute("computer", computerDTO);
		map.addAttribute("companies", companies);

		logger.info("Get the computer to edit");
		return "editComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@ModelAttribute("computer") @Valid ComputerDTO computerDTO,
			BindingResult bindingResult, ModelMap map) {
		
		if (bindingResult.hasErrors()) {
			List<Company> companies = this.companyService.getAll();
			map.addAttribute("companies", companies);
			return "editComputer";
		}
		
		this.computerService.update(computerMapperDTO.fromDto(computerDTO));
		logger.info("Edit the computer");
		return "redirect:/dashboard";
	}
}
