package com.flavien.dao.impl;

/**
 * 
 * Singleton manager to instanciate DAO objects
 *
 */
public enum DaoManager {
	INSTANCE;
	private static CompanyDaoImpl companyDao;
	private static ComputerDaoImpl computerDao;

	static {
		companyDao = new CompanyDaoImpl();
		computerDao = new ComputerDaoImpl();
	}

	private DaoManager() {
	}

	/**
	 * Instanciate a CompanyDaoImpl
	 * @return CompanyDaoImpl
	 */
	public CompanyDaoImpl getCompanyDaoImpl() {
		return companyDao;
	}

	/**
	 * Instanciate a ComputerDaoImpl
	 * @return ComputerDaoImpl
	 */
	public ComputerDaoImpl getComputerDaoImpl() {
		return computerDao;
	}
}
