package com.flavien.service.impl;

import java.sql.Connection;
import java.util.List;

import com.flavien.dao.CompanyDao;
import com.flavien.dao.ComputerDao;
import com.flavien.dao.impl.DaoManager;
import com.flavien.dao.utils.ConnectionManager;
import com.flavien.exception.PersistenceException;
import com.flavien.models.Company;
import com.flavien.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {
	private CompanyDao companyDao = DaoManager.INSTANCE.getCompanyDaoImpl();
	private ComputerDao computerDao = DaoManager.INSTANCE.getComputerDaoImpl();

	public CompanyServiceImpl() {
	}

	public CompanyServiceImpl(CompanyDao companyDao, ComputerDao computerDao) {
		this.companyDao = companyDao;
		this.computerDao = computerDao;
	}

	@Override
	public List<Company> getAll() {
		return companyDao.getAll();
	}

	@Override
	public Company getByID(int companyId) {
		return companyDao.getByID(companyId);
	}

	@Override
	public void deleteByID(int companyId) {
		Connection connection = ConnectionManager.getConnection(true);
		
		try {
			computerDao.deleteByCompanyId(companyId, connection);
			companyDao.deleteByID(companyId, connection);
		} catch (PersistenceException e) {
			ConnectionManager.rollback(connection);
		} finally {
			ConnectionManager.closeConnection(connection, true);
		}
	}

}
