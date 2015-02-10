package com.flavien.dao.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flavien.dao.CompanyMapper;
import com.flavien.dao.DbConnection;
import com.flavien.models.Company;

public enum CompanyDao {
	INSTANCE;

	private Connection connection;

	public final static String DB_COMPANY_TABLE = "company";
	public final static String DB_COLUMN_ID = "id";
	public final static String DB_COLUMN_NAME = "name";

	private final static String REQUEST_GET_ALL = "SELECT * FROM "
			+ DB_COMPANY_TABLE;

	private final static String REQUEST_GET_BY_ID = "SELECT * FROM "
			+ DB_COMPANY_TABLE + " WHERE id=?";

	private CompanyDao() {
	}

	public List<Company> getAll() {
		List<Company> companyList = new ArrayList<Company>();
		PreparedStatement preparedStatement = null;

		try {
			connection = DbConnection.INSTANCE.getConnection();

			preparedStatement = connection.prepareStatement(REQUEST_GET_ALL);
			java.sql.ResultSet rs = preparedStatement.executeQuery();
			companyList = CompanyMapper.INSTANCE.getList(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
		}
		return companyList;
	}

	public Company getByID(int companyId) {
		PreparedStatement preparedStatement = null;
		Company company = null;

		try {
			connection = DbConnection.INSTANCE.getConnection();
			preparedStatement = connection.prepareStatement(REQUEST_GET_BY_ID);
			preparedStatement.setInt(1, companyId);
			java.sql.ResultSet rs = preparedStatement.executeQuery();

			if (rs.first()) {
				company = CompanyMapper.INSTANCE.getObject(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
		}
		return company;
	}
}
