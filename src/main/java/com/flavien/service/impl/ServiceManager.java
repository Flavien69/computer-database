package com.flavien.service.impl;


public enum ServiceManager {
	INSTANCE;
	
	private ServiceManager(){}
	
	private static ComputerServiceImpl computerService;
	private static CompanyServiceImpl companyService;
	
	static{
		computerService = new ComputerServiceImpl();
		companyService = new CompanyServiceImpl();
	}
		
	public CompanyServiceImpl getCompanyServiceImpl(){
		return companyService;
	}
	
	public ComputerServiceImpl getComputerServiceImpl(){
		return computerService;
	}
}
