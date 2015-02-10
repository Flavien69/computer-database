package com.flavien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flavien.dao.instance.CompanyDao;
import com.flavien.models.Company;

public enum CompanyMapper implements RowMappable<Company>{
	INSTANCE;	
	private CompanyMapper(){};
	
	@Override
	public List<Company> getList(ResultSet rs) throws SQLException {
		List<Company> companyList = new ArrayList<>();
		while (rs.next()) {
			Company company = getObject(rs);
			companyList.add(company);
		}
		return companyList;
	}

	@Override
	public Company getObject(ResultSet rs) throws SQLException {
		return new Company(rs.getInt(CompanyDao.DB_COLUMN_ID),
				rs.getString(CompanyDao.DB_COLUMN_NAME));
	}

}