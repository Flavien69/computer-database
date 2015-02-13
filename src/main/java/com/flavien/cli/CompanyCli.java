package com.flavien.cli;

import java.util.List;

import com.flavien.models.Company;
import com.flavien.service.impl.ServiceManager;

public class CompanyCli {
	
	/**
	 * 
	 * Show a list of company 
	 * @author flavien
	 * 
	 */
	public static void showCompany(){
		List<Company> companyList = ServiceManager.INSTANCE.getCompanyServiceImpl().getAll();
		
		for(Company company: companyList){
			System.out.println(company.toString());
		}	
	}
}
