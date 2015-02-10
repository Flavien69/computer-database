package com.flavien.dao;

import java.sql.Connection;
import java.sql.SQLException;

public enum DbConnection {
	INSTANCE;
	
	private Connection connection = null;

	private static final String DB_HOST = "localhost";
	private static final String DB_PORT = "3306";
	public static final String DB_NAME = "computer-database-db";
	private static final String DB_ARGUMENT = "?zeroDateTimeBehavior=convertToNull";
	private static final String DB_PATH = DB_NAME+DB_ARGUMENT;
	private static final String DB_USER = "admincdb";
	private static final String DB_PWD = "qwerty1234";
	
	private DbConnection(){}
	
	public Connection getConnectionInstance() throws SQLException {

		if (connection == null) {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ DB_HOST + ":" + DB_PORT + "/" + DB_PATH, DB_USER, DB_PWD);	
		}
		return connection;
	}
	
	public void closeConnection() {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
