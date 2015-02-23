package com.flavien.service.impl;


/**
 * 
 * Singleton that instanciate service objects.
 * 
 */
public enum ServiceManager {
	INSTANCE;
	
	private ServiceManager(){}
	
	private static ComputerServiceImpl computerService;
	private static CompanyServiceImpl companyService;
	
	static{
		computerService = new ComputerServiceImpl();
		companyService = new CompanyServiceImpl();
	}
		
	/**
	 * Instanciate a companyServiceImpl object.
	 * @return CompanyServiceImpl
	 */
	public CompanyServiceImpl getCompanyServiceImpl(){
		return companyService;
	}
	
	/**
	 * Instanciate a ComputerServiceImpl object.
	 * @return ComputerServiceImpl
	 */
	public ComputerServiceImpl getComputerServiceImpl(){
		return computerService;
	}
}
