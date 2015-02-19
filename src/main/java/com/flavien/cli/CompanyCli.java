package com.flavien.cli;

import java.util.List;

import com.flavien.models.Company;
import com.flavien.service.impl.CompanyServiceImpl;
import com.flavien.service.impl.ComputerServiceImpl;
import com.flavien.service.impl.ServiceManager;

public class CompanyCli {
	
	private static CompanyServiceImpl companyService = ServiceManager.INSTANCE.getCompanyServiceImpl();
	/**
	 * 
	 * Show a list of company 
	 * @author flavien
	 * 
	 */
	public static void showCompany(){
		List<Company> companyList = companyService.getAll();
		
		for(Company company: companyList){
			System.out.println(company.toString());
		}	
	}
	
	/**
	 * 
	 * Delete a company 
	 * @author flavien
	 * 
	 */
	public static void deleteCompany(){
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
