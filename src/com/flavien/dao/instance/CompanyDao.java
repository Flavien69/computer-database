package com.flavien.dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.flavien.models.Company;

public class CompanyDao {

	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;
	
	private final static String dB_COMPANY_TABLE = "company";
	private final static String dB_COLUMN_ID = "id";
	private final static String dB_COLUMN_NAME = "name";

	public CompanyDao(String DB_HOST, String DB_PORT, String DB_NAME,
			String DB_USER, String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}

	public ArrayList<Company> getAllCompany() {
		ArrayList<Company> companyList = new ArrayList<Company>();

		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			java.sql.Statement query;

			query = connection.createStatement();

			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM "+dB_COMPANY_TABLE);
			while (rs.next()) {
				Company company = new Company(
						rs.getInt(dB_COLUMN_ID), rs.getString(dB_COLUMN_NAME));

				companyList.add(company);
			}
			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companyList;
	}
}
