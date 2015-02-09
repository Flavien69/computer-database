package com.flavien.dao.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.flavien.dao.fabric.DaoFabric;
import com.flavien.models.Company;
import com.flavien.models.Computer;

public class CompanyDao {

	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_PATH;
	private String dB_USER;
	private String dB_PWD;
	
	private final static String dB_COMPANY_TABLE = "company";
	private final static String dB_COLUMN_ID = "id";
	private final static String dB_COLUMN_NAME = "name";

	public CompanyDao(String DB_HOST, String DB_PORT, String DB_NAME, String DB_ARGUMENT,
			String DB_USER, String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_PATH = DB_NAME+DB_ARGUMENT;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}
	
	public Company getCompanyFromResult(ResultSet rs) throws SQLException{
		
		Company company = new Company(
				rs.getInt(dB_COLUMN_ID), rs.getString(dB_COLUMN_NAME));
		
		return company;
	}

	public ArrayList<Company> getAllCompany() {
		ArrayList<Company> companyList = new ArrayList<Company>();

		try {
			connection = DaoFabric.getConnectionInstance();

			java.sql.Statement query;

			query = connection.createStatement();

			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM "+dB_COMPANY_TABLE);
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
			connection = DaoFabric.getConnectionInstance();

			query = connection.createStatement();

			String sql = "SELECT * FROM "+dB_COMPANY_TABLE+" WHERE id=?";		
			
			preparedStatement = connection.prepareStatement(sql);
			 
			preparedStatement.setInt(1, companyId);
			
			java.sql.ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.first()) {				
				company = new Company(
						rs.getInt(dB_COLUMN_ID), rs.getString(dB_COLUMN_NAME));
			}
			
			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
}
