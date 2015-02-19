package com.flavien.service.impl;

import java.sql.Connection;
import java.util.List;

import com.flavien.dao.impl.CompanyDaoImpl;
import com.flavien.dao.impl.ComputerDaoImpl;
import com.flavien.dao.impl.DaoManager;
import com.flavien.dao.utils.DbConnection;
import com.flavien.dao.utils.DbUtils;
import com.flavien.exception.PersistenceException;
import com.flavien.models.Company;
import com.flavien.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {
	private CompanyDaoImpl companyDao = DaoManager.INSTANCE.getCompanyDaoImpl();
	private ComputerDaoImpl computerDao = DaoManager.INSTANCE.getComputerDaoImpl();

	public CompanyServiceImpl() {
	}

	public CompanyServiceImpl(CompanyDaoImpl companyDao, ComputerDaoImpl computerDao) {
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
		Connection connection = DbConnection.INSTANCE.getConnection(true);
		try {
			computerDao.deleteByCompanyId(companyId, connection);
			companyDao.deleteByID(companyId, connection);
		} catch (PersistenceException e) {
			DbUtils.rollback(connection);
		} finally {
			DbConnection.INSTANCE.closeConnection(connection, true);
		}
	}

}
