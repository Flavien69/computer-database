package com.flavien.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public List<Computer> getList(ResultSet rs) throws SQLException {
		List<Computer> computerList = new ArrayList<>();
		while (rs.next()) {
			Computer computer = getObject(rs);
			computerList.add(computer);
		}
		return computerList; 
	}

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
