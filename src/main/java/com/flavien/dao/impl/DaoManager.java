package com.flavien.dao.impl;

public enum DaoManager {
	INSTANCE;
	private static CompanyDaoImpl companyDao;
	private static ComputerDaoImpl computerDao;
	
	static{
		companyDao = new CompanyDaoImpl();
		computerDao = new ComputerDaoImpl();
	}
	
	private DaoManager(){}
	
	public CompanyDaoImpl getCompanyDaoImpl(){
		return companyDao;
	}
	
	public ComputerDaoImpl getComputerDaoImpl(){
		return computerDao;
	}
}