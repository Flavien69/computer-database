package com.flavien.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flavien.dao.CompanyDao;
import com.flavien.dao.utils.CompanyMapper;
import com.flavien.dao.utils.ConnectionManager;
import com.flavien.exception.PersistenceException;
import com.flavien.models.Company;

/**
 * 
 * Implementation to handle database requests for company object.
 *
 */
public class CompanyDaoImpl implements CompanyDao{

	private Connection connection;
	private final static Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);
	public final static String DB_COMPANY_TABLE = "company";
	public final static String DB_COLUMN_ID = "id";
	public final static String DB_COLUMN_NAME = "name";

	private final static String REQUEST_GET_ALL = "SELECT * FROM "
			+ DB_COMPANY_TABLE;
	
	private final static String REQUEST_DELETE = "DELETE FROM " + DB_COMPANY_TABLE + " WHERE "
			+ DB_COLUMN_ID + " =?";

	private final static String REQUEST_GET_BY_ID = "SELECT * FROM "
			+ DB_COMPANY_TABLE + " WHERE id=?";

	public CompanyDaoImpl() {
	}

	public List<Company> getAll() {
		List<Company> companyList = new ArrayList<Company>();
		PreparedStatement preparedStatement = null;
		java.sql.ResultSet rs = null;
		connection = ConnectionManager.getConnection(false);

		try {
			preparedStatement = connection.prepareStatement(REQUEST_GET_ALL);
			rs = preparedStatement.executeQuery();
			companyList = CompanyMapper.INSTANCE.getList(rs);

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);

		} finally {
			ConnectionManager.closeConnection(connection, false);
			ConnectionManager.closePreparedStatement(preparedStatement);
			ConnectionManager.closeResultSet(rs);
		}
		logger.info("Retrieve all the companies.");
		return companyList;
	}

	public Company getByID(int companyId) {

		PreparedStatement preparedStatement = null;
		Company company = null;
		java.sql.ResultSet rs = null;
		connection = ConnectionManager.getConnection(false);

		try {
			preparedStatement = connection.prepareStatement(REQUEST_GET_BY_ID);
			preparedStatement.setInt(1, companyId);
			rs = preparedStatement.executeQuery();

			if (rs.first()) {
				company = CompanyMapper.INSTANCE.getObject(rs);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);

		} finally {
			ConnectionManager.closeConnection(connection, false);
			ConnectionManager.closePreparedStatement(preparedStatement);
			ConnectionManager.closeResultSet(rs);
		}

		logger.info("Retrieve one company by ID.");
		return company;
	}

	@Override
	public void deleteByID(int companyId, Connection connection) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(REQUEST_DELETE);
			preparedStatement.setInt(1, companyId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);

		} finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}		
	}
}
