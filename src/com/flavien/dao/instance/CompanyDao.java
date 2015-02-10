package com.flavien.dao.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flavien.dao.DbConnection;
import com.flavien.models.Company;

public enum CompanyDao {
	INSTANCE;

	private Connection connection;

	private final static String DB_COMPANY_TABLE = "company";
	private final static String DB_COLUMN_ID = "id";
	private final static String DB_COLUMN_NAME = "name";

	public Company getCompanyFromResult(ResultSet rs) throws SQLException {
		return new Company(rs.getInt(DB_COLUMN_ID),
				rs.getString(DB_COLUMN_NAME));
	}

	public List<Company> getAllCompany() {
		List<Company> companyList = new ArrayList<Company>();

		try {
			connection = DbConnection.INSTANCE.getConnectionInstance();

			java.sql.Statement query;

			query = connection.createStatement();

			java.sql.ResultSet rs = query.executeQuery("SELECT * FROM "
					+ DB_COMPANY_TABLE);
			while (rs.next()) {
				Company company = getCompanyFromResult(rs);

				companyList.add(company);
			}
			rs.close();
			query.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companyList;
	}

	public Company getCompanyByID(int companyId) {
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
				company = new Company(rs.getInt(DB_COLUMN_ID),
						rs.getString(DB_COLUMN_NAME));
			}

			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
}
