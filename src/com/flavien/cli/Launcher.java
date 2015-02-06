package com.flavien.cli;

import java.util.ArrayList;
import java.util.List;

import com.flavien.dao.fabric.DaoFabric;
import com.flavien.dao.instance.CompanyDao;
import com.flavien.dao.instance.ComputerDao;
import com.flavien.models.Company;
import com.flavien.models.Computer;

public class Launcher {

	public static void main(String[] args) {
		CompanyDao companyDao = DaoFabric.getInstance().createCompanyDao();
		ComputerDao computerDao = DaoFabric.getInstance().createComputerDao();

		List<Computer> computerList = new ArrayList<Computer>();
		List<Company> companyList = new ArrayList<Company>();
		
		companyList = companyDao.getAllCompany();
		computerList = computerDao.getAllComputers();
		
		for(Company company: companyList){
			System.out.println(company.toString());
		}
		
		for(Computer computer: computerList){
			System.out.println(computer.toString());
		}
	}

}
