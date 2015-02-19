package com.flavien.dao.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that permits to close the resultset and the preparedStatement
 * 
 * @author flavien
 * 
 */
public final class DbUtils {
	private final static Logger logger = LoggerFactory.getLogger(DbUtils.class);

	private DbUtils() {
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
