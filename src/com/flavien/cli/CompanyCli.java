package com.flavien.cli;

import java.util.List;

import com.flavien.dao.instance.CompanyDao;
import com.flavien.models.Company;

public enum CompanyCli {
	INSTANCE;
	
	private CompanyCli(){
	}
	
	public void showCompany(){
		List<Company> companyList = CompanyDao.INSTANCE.getAll();
		
		for(Company company: companyList){
			System.out.println(company.toString());
		}	
	}
}
