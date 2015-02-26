package com.flavien.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flavien.dao.CompanyDao;
import com.flavien.dao.ComputerDao;
import com.flavien.dao.utils.ConnectionManager;
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

	private CompanyDao companyDao;
	private ComputerDao computerDao;
	private final static Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

	public CompanyServiceImpl() {
	}

	@Autowired
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
	@Transactional
	public void deleteByID(int companyId) {
		ConnectionManager.initTransaction();
		computerDao.deleteByCompanyId(companyId);
		companyDao.deleteByID(companyId);
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public void setComputerDao(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}
}
