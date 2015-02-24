package com.flavien.service.impl;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flavien.dao.CompanyDao;
import com.flavien.dao.ComputerDao;
import com.flavien.dao.impl.DaoManager;
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
public class CompanyServiceImpl implements CompanyService{
	private CompanyDao companyDao = DaoManager.INSTANCE.getCompanyDaoImpl();
	private ComputerDao computerDao = DaoManager.INSTANCE.getComputerDaoImpl();
	private final static Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

	public CompanyServiceImpl() {
	}

	public CompanyServiceImpl(CompanyDao companyDao, ComputerDao computerDao) {
		this.companyDao = companyDao;
		this.computerDao = computerDao;
	}

	/* (non-Javadoc)
	 * @see com.flavien.service.CompanyService#getAll()
	 */
	@Override
	public List<Company> getAll() {
		return companyDao.getAll();
	}

	/* (non-Javadoc)
	 * @see com.flavien.service.CompanyService#getByID(int)
	 */
	@Override
	public Company getByID(int companyId) {
		return companyDao.getByID(companyId);
	}

	/* (non-Javadoc)
	 * @see com.flavien.service.CompanyService#deleteByID(int)
	 */
	@Override
	public void deleteByID(int companyId) {
		Connection connection = ConnectionManager.getConnection(true);
		
		try {
			computerDao.deleteByCompanyId(companyId, connection);
			companyDao.deleteByID(companyId, connection);
		} catch (PersistenceException e) {
			logger.debug("rollback the transaction");
			ConnectionManager.rollback(connection);
			throw new ServiceException(e);
		} finally {
			ConnectionManager.closeConnection(connection, true);
		}
	}

}
