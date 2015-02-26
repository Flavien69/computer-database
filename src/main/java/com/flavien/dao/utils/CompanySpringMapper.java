package com.flavien.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.flavien.dao.impl.CompanyDaoImpl;
import com.flavien.models.Company;

@Component
public class CompanySpringMapper implements RowMapper<Company>{

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Company.Builder()
		.id(rs.getInt(CompanyDaoImpl.DB_COLUMN_ID))
		.name(rs.getString(CompanyDaoImpl.DB_COLUMN_NAME))
		.build();
	}
}
