package com.flavien.cli;

import java.util.List;

import com.flavien.dao.instance.CompanyDao;
import com.flavien.models.Company;

public class CompanyCli {
	
	private CompanyDao companyDao;
	
	public CompanyCli(){
		this.companyDao = CompanyDao.INSTANCE;
	}
	
	public void showCompany(){
		List<Company> companyList = companyDao.getAll();
		
		for(Company company: companyList){
			System.out.println(company.toString());
		}	
	}
}
