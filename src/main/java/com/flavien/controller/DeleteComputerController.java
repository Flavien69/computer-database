package com.flavien.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flavien.service.CompanyService;
import com.flavien.service.ComputerService;
import com.flavien.utils.Utils;

@Controller
@RequestMapping("/delete-computer")
public class DeleteComputerController {
	private final static Logger logger = LoggerFactory.getLogger(DeleteComputerController.class);

	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;

	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@RequestParam("selection") String idsToDelete){
		String[] array = idsToDelete.split(",");
		for (String idString : array){
			int id = Utils.getInt(idString);
			if (id != Utils.ERROR)
				this.computerService.deleteById(id);
		}
		logger.info("delete the computer "+idsToDelete);
		return "redirect:/dashboard";		
	}
}