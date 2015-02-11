package com.flavien.dao.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.flavien.utils.PropertyValues;

public enum DbConnection {
	INSTANCE;

	private static final String DB_HOST = "localhost";
	private static final String DB_PORT = "3306";
	public static final String DB_NAME = PropertyValues.INSTANCE.getDbName();
	private static final String DB_ARGUMENT = "?zeroDateTimeBehavior=convertToNull";
	private static final String DB_PATH = DB_NAME+DB_ARGUMENT;
	private static final String DB_USER = "admincdb";
	private static final String DB_PWD = "qwerty1234";
	
	private DbConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		Connection connection = null;
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
						+ DB_HOST + ":" + DB_PORT + "/" + DB_PATH, DB_USER, DB_PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
