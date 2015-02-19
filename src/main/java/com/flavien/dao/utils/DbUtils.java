package com.flavien.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flavien.exception.PersistenceException;

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
			if(rs != null)
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);
		}
	}

	public static void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			if(preparedStatement != null)
				preparedStatement.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);
		}
	}
	
	public static void rollback(Connection connection) {
		try {
			if(connection != null)
				connection.rollback();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);
		}
	}
}
