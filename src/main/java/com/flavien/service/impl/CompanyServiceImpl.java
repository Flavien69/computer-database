package com.flavien.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flavien.dao.CompanyDao;
import com.flavien.dao.ComputerDao;
import com.flavien.exception.PersistenceException;
import com.flavien.exception.ServiceException;
import com.flavien.models.Company;
import com.flavien.service.CompanyService;

/**
 * 
 * Class that implement the company service API.
 * 
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private ComputerDao computerDao;
	
	public CompanyServiceImpl() {
	}

	
	public CompanyServiceImpl(CompanyDao companyDao, ComputerDao computerDao) {
		this.companyDao = companyDao;
		this.computerDao = computerDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.service.CompanyService#getAll()
	 */
	@Override
	public List<Company> getAll() {
		return companyDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.service.CompanyService#getByID(int)
	 */
	@Override
	public Company getByID(int companyId) {
		return companyDao.getByID(companyId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.service.CompanyService#deleteByID(int)
	 */
	@Override
	@Transactional(rollbackFor=PersistenceException.class)
	public void deleteByID(int companyId) {
		try{
		computerDao.deleteByCompanyId(companyId);
		companyDao.deleteByID(companyId);
		}catch(PersistenceException e){
			throw new ServiceException(e);
		}
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public void setComputerDao(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}
}
