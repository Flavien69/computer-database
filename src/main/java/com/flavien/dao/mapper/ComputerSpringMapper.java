package com.flavien.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.flavien.dao.DateUtils;
import com.flavien.dao.impl.ComputerDaoImpl;
import com.flavien.models.Company;
import com.flavien.models.Computer;

@Component
public class ComputerSpringMapper implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Company company = new Company.Builder().id(rs.getInt(ComputerDaoImpl.DB_COLUMN_COMPANY_ID))
				.name(rs.getString(ComputerDaoImpl.DB_COLUMN_COMPANY_NAME)).build();

		return new Computer.Builder()
				.id(rs.getInt(ComputerDaoImpl.DB_COLUMN_ID))
				.name(rs.getString(ComputerDaoImpl.DB_COLUMN_NAME))
				.introduced(DateUtils.getLocalDate(rs.getTimestamp(ComputerDaoImpl.DB_COLUMN_INTRODUCED)))
				.discontinued(DateUtils.getLocalDate(rs.getTimestamp(ComputerDaoImpl.DB_COLUMN_DISCONTINUED)))
				.company(company).build();
	}

}
