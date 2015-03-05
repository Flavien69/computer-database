package com.flavien.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flavien.dao.repository.CompanyRepository;
import com.flavien.dao.repository.ComputerRepository;
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
	private CompanyRepository companyRepository;

	@Autowired
	private ComputerRepository computerRepository;

	public CompanyServiceImpl() {
	}

	public CompanyServiceImpl(CompanyRepository companyRepository, ComputerRepository computerRepository) {
		this.computerRepository = computerRepository;
		this.companyRepository = companyRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.service.CompanyService#getAll()
	 */
	@Override
	public List<Company> getAll() {
		return companyRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.service.CompanyService#getByID(int)
	 */
	@Override
	public Company getByID(int companyId) {
		return companyRepository.findOne(companyId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.service.CompanyService#deleteByID(int)
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteByID(int companyId) {
		computerRepository.deleteByCompanyId(companyId);
		companyRepository.delete(companyId);

	}
}
