package com.flavien.service.impl;

import java.util.List;

import com.flavien.dao.impl.CompanyDaoImpl;
import com.flavien.models.Company;
import com.flavien.service.CompanyService;

public enum CompanyServiceImpl implements CompanyService{
	INSTANCE;
	private CompanyDaoImpl companyDao = CompanyDaoImpl.INSTANCE;
	
	private CompanyServiceImpl() {}
	
	@Override
	public List<Company> getAll() {
		return companyDao.getAll();		
	}

	@Override
	public Company getByID(int companyId) {
		return companyDao.getByID(companyId);
	}

}
