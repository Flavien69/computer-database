package com.flavien.cli;

import java.util.List;

import com.flavien.dao.implementations.CompanyDaoImpl;
import com.flavien.models.Company;

public class CompanyCli {
	
	/**
	 * 
	 * Show a list of company 
	 * @author flavien
	 * 
	 */
	public static void showCompany(){
		List<Company> companyList = CompanyDaoImpl.INSTANCE.getAll();
		
		for(Company company: companyList){
			System.out.println(company.toString());
		}	
	}
}
