package com.flavien.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.flavien.dao.impl.CompanyDaoImpl;
import com.flavien.models.Company;

/**
 * 
 * Singleton to convert a resultset to a company object
 *
 */
public enum CompanyMapper implements RowMappable<Company>{
	INSTANCE;	
	private CompanyMapper(){};
	
	
	/* (non-Javadoc)
	 * @see com.flavien.dao.utils.RowMappable#getObject(java.sql.ResultSet)
	 */
	@Override
	public Company getObject(ResultSet rs) throws SQLException {
		return new Company.Builder()
			.id(rs.getInt(CompanyDaoImpl.DB_COLUMN_ID))
			.name(rs.getString(CompanyDaoImpl.DB_COLUMN_NAME))
			.build();
	}

}