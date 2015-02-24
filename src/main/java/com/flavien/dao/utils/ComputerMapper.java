package com.flavien.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.flavien.dao.impl.ComputerDaoImpl;
import com.flavien.models.Company;
import com.flavien.models.Computer;

/**
 * 
 * Singleton to convert a resultset to a computer object
 *
 */
public enum ComputerMapper implements RowMappable<Computer>{
	INSTANCE;	
	private ComputerMapper(){};
	

	/* (non-Javadoc)
	 * @see com.flavien.dao.utils.RowMappable#getObject(java.sql.ResultSet)
	 */
	@Override
	public Computer getObject(ResultSet rs) throws SQLException {
		
		Company company = new Company.Builder()
				.id(rs.getInt(ComputerDaoImpl.DB_COLUMN_COMPANY_ID))
				.name(rs.getString(ComputerDaoImpl.DB_COLUMN_COMPANY_NAME))
				.build();
		
		return new Computer.Builder()
			.id(rs.getInt(ComputerDaoImpl.DB_COLUMN_ID))
			.name(rs.getString(ComputerDaoImpl.DB_COLUMN_NAME))
			.introduced(DateUtils.getLocalDate(rs.getTimestamp(ComputerDaoImpl.DB_COLUMN_INTRODUCED)))
			.discontinued(DateUtils.getLocalDate(rs.getTimestamp(ComputerDaoImpl.DB_COLUMN_DISCONTINUED)))
			.company(company)
			.build();
	}

}
