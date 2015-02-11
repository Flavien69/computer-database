package com.flavien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flavien.dao.implementations.CompanyDaoImpl;
import com.flavien.models.Company;

public enum CompanyMapper implements RowMappable<Company>{
	INSTANCE;	
	private CompanyMapper(){};
	
	public List<Company> getList(ResultSet rs) throws SQLException {
		List<Company> companyList = new ArrayList<>();
		while (rs.next()) {
			Company company = getObject(rs);
			companyList.add(company);
		}
		return companyList;
	}

	public Company getObject(ResultSet rs) throws SQLException {
		return new Company(rs.getInt(CompanyDaoImpl.DB_COLUMN_ID),
				rs.getString(CompanyDaoImpl.DB_COLUMN_NAME));
	}

}