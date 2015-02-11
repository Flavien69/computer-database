package com.flavien.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flavien.dao.utils.CompanyMapper;
import com.flavien.dao.utils.DbConnection;
import com.flavien.dao.utils.DbUtils;
import com.flavien.models.Company;

public enum CompanyDaoImpl {
	INSTANCE;

	private Connection connection;

	public final static String DB_COMPANY_TABLE = "company";
	public final static String DB_COLUMN_ID = "id";
	public final static String DB_COLUMN_NAME = "name";

	private final static String REQUEST_GET_ALL = "SELECT * FROM "
			+ DB_COMPANY_TABLE;

	private final static String REQUEST_GET_BY_ID = "SELECT * FROM "
			+ DB_COMPANY_TABLE + " WHERE id=?";

	private CompanyDaoImpl() {
	}

	public List<Company> getAll() {
		List<Company> companyList = new ArrayList<Company>();
		PreparedStatement preparedStatement = null;
		java.sql.ResultSet rs = null;
		try {
			connection = DbConnection.INSTANCE.getConnection();

			preparedStatement = connection.prepareStatement(REQUEST_GET_ALL);
			rs = preparedStatement.executeQuery();
			companyList = CompanyMapper.INSTANCE.getList(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
			DbUtils.closePreparedStatement(preparedStatement);
			DbUtils.closeResultSet(rs);
		}
		return companyList;
	}

	public Company getByID(int companyId) {
		PreparedStatement preparedStatement = null;
		Company company = null;
		java.sql.ResultSet rs = null;
		
		try {
			connection = DbConnection.INSTANCE.getConnection();
			preparedStatement = connection.prepareStatement(REQUEST_GET_BY_ID);
			preparedStatement.setInt(1, companyId);
			rs = preparedStatement.executeQuery();

			if (rs.first()) {
				company = CompanyMapper.INSTANCE.getObject(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
			DbUtils.closePreparedStatement(preparedStatement);
			DbUtils.closeResultSet(rs);
		}
		return company;
	}
}
