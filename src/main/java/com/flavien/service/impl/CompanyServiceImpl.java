package com.flavien.service.impl;

import java.util.List;

import com.flavien.dao.impl.CompanyDaoImpl;
import com.flavien.dao.impl.DaoManager;
import com.flavien.models.Company;
import com.flavien.service.CompanyService;

public class CompanyServiceImpl implements CompanyService{
	private CompanyDaoImpl companyDao = DaoManager.INSTANCE.getCompanyDaoImpl();
	
	public CompanyServiceImpl() {}
	
	@Override
	public List<Company> getAll() {
		return companyDao.getAll();		
	}

	@Override
	public Company getByID(int companyId) {
		return companyDao.getByID(companyId);
	}

}
