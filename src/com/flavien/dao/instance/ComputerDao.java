package com.flavien.dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.flavien.models.Computer;

public class ComputerDao {

	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_PATH;
	private String dB_USER;
	private String dB_PWD;
	
	private final static String dB_COMPUTER_TABLE = "computer";
	private final static String dB_COLUMN_ID = "id";
	private final static String dB_COLUMN_NAME = "name";
	private final static String dB_COLUMN_INTRODUCED = "introduced";
	private final static String dB_COLUMN_DISCONTINUED = "discontinued";
	private final static String dB_COLUMN_COMPANY_ID = "company_id";

	
	public ComputerDao(String DB_HOST, String DB_PORT, String DB_NAME, String DB_ARGUMENT,
			String DB_USER, String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_PATH = DB_NAME+DB_ARGUMENT;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}

	public void addComputer(Computer computer) {
		java.sql.Statement query;
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_PATH, dB_USER, dB_PWD);

			query = connection.createStatement();

			String sql = "INSERT INTO `"+ dB_NAME +"`.`"+dB_COMPUTER_TABLE+"` (`"+dB_COLUMN_NAME+"`, `"+dB_COLUMN_INTRODUCED+"`, `"+dB_COLUMN_DISCONTINUED+"`, `"+dB_COLUMN_COMPANY_ID+"`) VALUES ('"
					+ computer.getName()
					+ "', '"
					+ computer.getIntroduced()
					+ "', '"
					+ computer.getDiscontinued()
					+ "', "
					+ computer.getCompany_id()
					+ ");";			
			
			int rs = query.executeUpdate(sql);	
			
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateComputer(Computer computer) {

		java.sql.Statement query;
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			query = connection.createStatement();

			String sql = "UPDATE  `"+ dB_NAME +"`.`"+dB_COMPUTER_TABLE+"` SET "
					+"`name`='"+ computer.getName()
					+"',`introduced`='"+ computer.getIntroduced()
					+"',`discontinued`="+ computer.getDiscontinued()
					+",`company_id`='"+ computer.getCompany_id()+"'";
			
			
			int rs = query.executeUpdate(sql);
			
			
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteComputer(Computer computer) {

		java.sql.Statement query;
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			query = connection.createStatement();

			String sql = "DELETE FROM`"+ dB_NAME +"`.`"+dB_COMPUTER_TABLE+"` WHERE `"+dB_COMPUTER_TABLE+"`.`"+dB_COLUMN_ID+"` = '"+computer.getId()+"'";
			
			int rs = query.executeUpdate(sql);
					
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Computer> getAllComputers() {
		ArrayList<Computer> computerList = new ArrayList<Computer>();

		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_PATH, dB_USER, dB_PWD);

			java.sql.Statement query;

			query = connection.createStatement();

			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM "+dB_COMPUTER_TABLE);
			while (rs.next()) {
				Computer computer = new Computer(
						rs.getInt(dB_COLUMN_ID), rs.getString(dB_COLUMN_NAME),
						rs.getTimestamp(dB_COLUMN_INTRODUCED), 
						rs.getTimestamp(dB_COLUMN_DISCONTINUED),
						rs.getInt(dB_COLUMN_COMPANY_ID));

				computerList.add(computer);
			}
			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computerList;
	}
}
