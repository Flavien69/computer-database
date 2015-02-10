package com.flavien.dao.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.flavien.dao.DbConnection;
import com.flavien.models.Computer;
import com.flavien.models.Page;

public enum ComputerDao {
	INSTANCE;
	private Connection connection;

	private static final String DB_NAME = "computer-database-db";
	private final static String DB_COMPUTER_TABLE = "computer";
	private final static String DB_COLUMN_ID = "id";
	private final static String DB_COLUMN_NAME = "name";
	private final static String DB_COLUMN_INTRODUCED = "introduced";
	private final static String DB_COLUMN_DISCONTINUED = "discontinued";
	private final static String DB_COLUMN_COMPANY_ID = "company_id";

	private ComputerDao() {}

	public Computer getComputerFromResult(ResultSet rs) throws SQLException {

		return new Computer(
				rs.getInt(DB_COLUMN_ID),
				rs.getString(DB_COLUMN_NAME),
				DateUtils.getLocalDate(rs.getTimestamp(DB_COLUMN_INTRODUCED)),
				DateUtils.getLocalDate(rs.getTimestamp(DB_COLUMN_DISCONTINUED)),
				rs.getInt(DB_COLUMN_COMPANY_ID));

	}

	public Boolean addComputer(Computer computer) {
		java.sql.Statement query;
		PreparedStatement preparedStatement = null;
		Boolean isSuccess = false;

		try {
			connection = DbConnection.INSTANCE.getConnectionInstance();

			query = connection.createStatement();
			String sql;
			sql = "INSERT INTO `" + DB_NAME + "`.`" + DB_COMPUTER_TABLE
					+ "` (`" + DB_COLUMN_NAME + "`, `" + DB_COLUMN_INTRODUCED
					+ "`, `" + DB_COLUMN_DISCONTINUED + "`, `"
					+ DB_COLUMN_COMPANY_ID + "`) VALUES" + "(?,?,?,?)";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, computer.getName());
			if (computer.getIntroduced() == null)
				preparedStatement.setTimestamp(2, null);
			else
				preparedStatement.setTimestamp(2,
						Timestamp.valueOf(computer.getIntroduced()));

			if (computer.getDiscontinued() == null)
				preparedStatement.setTimestamp(3, null);
			else
				preparedStatement.setTimestamp(3,
						Timestamp.valueOf(computer.getDiscontinued()));

			if (computer.getCompany_id() != 0)
				preparedStatement.setInt(4, computer.getCompany_id());
			else
				preparedStatement.setNull(4, java.sql.Types.BIGINT);

			int rs = preparedStatement.executeUpdate();

			if (rs > 0)
				isSuccess = true;

			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	public Computer getComputerByID(int computerId) {
		java.sql.Statement query;
		PreparedStatement preparedStatement = null;
		Computer computer = null;

		try {
			connection = DbConnection.INSTANCE.getConnectionInstance();

			query = connection.createStatement();

			String sql = "SELECT * FROM " + DB_COMPUTER_TABLE + " WHERE id=?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, computerId);

			java.sql.ResultSet rs = preparedStatement.executeQuery();

			if (rs.first())
				computer = getComputerFromResult(rs);

			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public Boolean updateComputer(Computer computer) {

		Boolean isSuccess = false;
		PreparedStatement preparedStatement = null;
		java.sql.Statement query;
		try {
			connection = DbConnection.INSTANCE.getConnectionInstance();

			query = connection.createStatement();

			String sql = "UPDATE  `" + DB_NAME + "`.`" + DB_COMPUTER_TABLE
					+ "` SET " + "`name`=?" + ",`introduced`=?"
					+ ",`discontinued`=?" + ",`company_id`=?" + " WHERE id=?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, computer.getName());

			if (computer.getIntroduced() == null)
				preparedStatement.setTimestamp(2, null);
			else
				preparedStatement.setTimestamp(2,
						Timestamp.valueOf(computer.getIntroduced()));

			if (computer.getDiscontinued() == null)
				preparedStatement.setTimestamp(3, null);
			else
				preparedStatement.setTimestamp(3,
						Timestamp.valueOf(computer.getDiscontinued()));
			preparedStatement.setInt(4, computer.getCompany_id());
			preparedStatement.setInt(5, computer.getId());

			int rs = preparedStatement.executeUpdate();

			if (rs > 0) {
				isSuccess = true;
			}
			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	public Boolean deleteComputer(Computer computer) {

		Boolean isSuccess = false;
		java.sql.Statement query;
		try {
			connection = DbConnection.INSTANCE.getConnectionInstance();

			query = connection.createStatement();

			String sql = "DELETE FROM`" + DB_NAME + "`.`" + DB_COMPUTER_TABLE
					+ "` WHERE `" + DB_COMPUTER_TABLE + "`.`" + DB_COLUMN_ID
					+ "` = '" + computer.getId() + "'";

			int rs = query.executeUpdate(sql);

			switch (rs) {
			case 0:
				isSuccess = false;
				break;

			case 1:
				isSuccess = true;
				break;
			default:
				isSuccess = false;
				break;
			}

			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	public Boolean deleteComputerById(int computerId) {

		Boolean isSuccess = false;
		java.sql.Statement query;
		try {
			connection = DbConnection.INSTANCE.getConnectionInstance();

			query = connection.createStatement();

			String sql = "DELETE FROM`" + DB_NAME + "`.`" + DB_COMPUTER_TABLE
					+ "` WHERE `" + DB_COMPUTER_TABLE + "`.`" + DB_COLUMN_ID
					+ "` = '" + computerId + "'";

			int rs = query.executeUpdate(sql);

			switch (rs) {
			case 0:
				isSuccess = false;
				break;

			case 1:
				isSuccess = true;
				break;
			default:
				isSuccess = false;
				break;
			}

			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	public Page getComputersByPage(int index) {
		Page page = new Page();
		List<Computer> computerList = new ArrayList<Computer>();

		try {
			connection = DbConnection.INSTANCE.getConnectionInstance();

			java.sql.Statement query;

			query = connection.createStatement();

			java.sql.ResultSet rs = query.executeQuery("SELECT * FROM "
					+ DB_COMPUTER_TABLE + " ORDER BY " + DB_COLUMN_ID
					+ " LIMIT " + index * Page.NB_ENTITY_BY_PAGE + ","
					+ Page.NB_ENTITY_BY_PAGE);

			while (rs.next()) {
				Computer computer = getComputerFromResult(rs);
				computerList.add(computer);
			}
			rs.close();
			query.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setComputerList(computerList);
		page.setIndex(index);
		return page;
	}

	public List<Computer> getAllComputers() {
		List<Computer> computerList = new ArrayList<Computer>();

		try {
			connection = DbConnection.INSTANCE.getConnectionInstance();

			java.sql.Statement query;

			query = connection.createStatement();

			java.sql.ResultSet rs = query.executeQuery("SELECT * FROM "
					+ DB_COMPUTER_TABLE);

			while (rs.next()) {
				Computer computer = getComputerFromResult(rs);
				computerList.add(computer);
			}
			rs.close();
			query.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computerList;
	}
}
