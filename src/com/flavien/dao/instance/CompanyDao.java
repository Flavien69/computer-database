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

	private CompanyDao() {}

	public List<Company> getAll() {
		List<Company> companyList = new ArrayList<Company>();

		try {
			connection = DbConnection.INSTANCE.getConnectionInstance();

			java.sql.Statement query;

			query = connection.createStatement();

			java.sql.ResultSet rs = query.executeQuery("SELECT * FROM "
					+ DB_COMPANY_TABLE);

			companyList = CompanyMapper.INSTANCE.getList(rs);
			
			rs.close();
			query.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companyList;
	}

	public Company getByID(int companyId) {
		java.sql.Statement query;
		PreparedStatement preparedStatement = null;
		Company company = null;

		try {
			connection = DbConnection.INSTANCE.getConnectionInstance();
			query = connection.createStatement();

			String sql = "SELECT * FROM " + DB_COMPANY_TABLE + " WHERE id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, companyId);
			java.sql.ResultSet rs = preparedStatement.executeQuery();

			if (rs.first()) {
				company = CompanyMapper.INSTANCE.getObject(rs);
			}

			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
}
