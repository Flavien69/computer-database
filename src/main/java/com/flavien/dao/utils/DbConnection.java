package com.flavien.dao.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flavien.utils.PropertyValues;

public enum DbConnection {
	INSTANCE;
	
	private final static Logger logger = LoggerFactory.getLogger(DbConnection.class);
	private static final String DB_HOST = "localhost";
	private static final String DB_PORT = "3306";
	public static final String DB_NAME = PropertyValues.INSTANCE.getDbName();
	private static final String DB_ARGUMENT = "?zeroDateTimeBehavior=convertToNull";
	private static final String DB_PATH = DB_NAME + DB_ARGUMENT;
	public static final String DB_USER = PropertyValues.INSTANCE.getDbUser();
	public static final String DB_PWD = PropertyValues.INSTANCE.getDbPwd();;

	private DbConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/"
					+ DB_PATH, DB_USER, DB_PWD);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return connection;
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
