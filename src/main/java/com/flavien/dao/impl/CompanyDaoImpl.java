package com.flavien.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.flavien.dao.CompanyDao;
import com.flavien.dao.utils.CompanySpringMapper;
import com.flavien.exception.PersistenceException;
import com.flavien.models.Company;

/**
 * 
 * Implementation to handle database requests for company object.
 *
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CompanySpringMapper companySpringMapper;


	private final static Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);
	public final static String DB_COMPANY_TABLE = "company";
	public final static String DB_COLUMN_ID = "id";
	public final static String DB_COLUMN_NAME = "name";

	private final static String REQUEST_GET_ALL = "SELECT * FROM " + DB_COMPANY_TABLE;

	private final static String REQUEST_DELETE = "DELETE FROM " + DB_COMPANY_TABLE + " WHERE " + DB_COLUMN_ID
			+ " =?";

	private final static String REQUEST_GET_BY_ID = "SELECT * FROM " + DB_COMPANY_TABLE + " WHERE id=?";
	
	public CompanyDaoImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.CompanyDao#getAll()
	 */
	@Override
	public List<Company> getAll() {
		List<Company> companyList = this.jdbcTemplate.query(REQUEST_GET_ALL,companySpringMapper);
		logger.info("Retrieve all the companies.");
		return companyList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.CompanyDao#getByID(int)
	 */
	@Override
	public Company getByID(int companyId) {		
		Company company = this.jdbcTemplate.queryForObject(REQUEST_GET_BY_ID,  new Object[]{companyId}, companySpringMapper);
		logger.info("Retrieve one company by ID.");
		return company;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.CompanyDao#deleteByID(int, java.sql.Connection)
	 */
	@Override
	public void deleteByID(int companyId) {
		throw new PersistenceException();
		//this.jdbcTemplate.update(REQUEST_DELETE, companyId);
		//logger.info("delete the company "+companyId);
	}
}
