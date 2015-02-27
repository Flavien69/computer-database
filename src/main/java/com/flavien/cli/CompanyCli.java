package com.flavien.cli;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flavien.models.Company;
import com.flavien.service.CompanyService;

/**
 * 
 * Command line interface to handle company interaction.
 * 
 */
@Component
public class CompanyCli {
	
	@Autowired
	private CompanyService companyService;
	
	/**
	 * 
	 * Show a list of company 
	 * 
	 */
	public void showCompany(){
		List<Company> companyList = companyService.getAll();
		
		for(Company company: companyList){
			System.out.println(company.toString());
		}	
	}
	
	/**
	 * 
	 * Delete a company 
	 * 
	 */
	public void deleteCompany(){
		System.out.println("\n***************** DELETE A COMPANY ************************\n");
		showCompany();

		System.out.println("\nchoose a company to delete (ID of the company):");
		int id = Utils.getIntInput(Utils.NO_MAX_VALUE);
		while (id == Utils.RESULT_SKIP) {
			System.out.println("\nERREUR: choose a computer to delete (ID of the computer):");
		}

		companyService.deleteByID(id);
	
		System.out.println("company deleted!\n");
	}
}
