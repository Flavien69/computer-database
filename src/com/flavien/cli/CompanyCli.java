package com.flavien.cli;

import java.util.List;

import com.flavien.dao.fabric.DaoFabric;
import com.flavien.dao.instance.CompanyDao;
import com.flavien.models.Company;

public class CompanyCli {
	
	private CompanyDao companyDao;
	
	public CompanyCli(){
		this.companyDao = DaoFabric.getInstance().createCompanyDao();
	}
	
	public void showCompany(){
		List<Company> companyList = companyDao.getAllCompany();
		
		for(Company company: companyList){
			System.out.println(company.toString());
		}	
	}
}
