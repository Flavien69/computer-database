package com.flavien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flavien.dao.implementations.ComputerDaoImpl;
import com.flavien.models.Company;
import com.flavien.models.Computer;

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
		
		Company company = new Company(rs.getInt(ComputerDaoImpl.DB_COLUMN_COMPANY_ID),rs.getString(ComputerDaoImpl.DB_COLUMN_COMPANY_NAME));
		
		return new Computer(
			rs.getInt(ComputerDaoImpl.DB_COLUMN_ID),
			rs.getString(ComputerDaoImpl.DB_COLUMN_NAME),
			DateUtils.getLocalDate(rs.getTimestamp(ComputerDaoImpl.DB_COLUMN_INTRODUCED)),
			DateUtils.getLocalDate(rs.getTimestamp(ComputerDaoImpl.DB_COLUMN_DISCONTINUED)),
			company);
	}

}
