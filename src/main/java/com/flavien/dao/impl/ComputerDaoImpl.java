package com.flavien.dao.impl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.flavien.dao.ComputerDao;
import com.flavien.dao.utils.ComputerMapper;
import com.flavien.dao.utils.DbConnection;
import com.flavien.dao.utils.DbUtils;
import com.flavien.models.Computer;
import com.flavien.utils.PropertyValues;

public class ComputerDaoImpl implements ComputerDao {
	private Connection connection;

	public static final String DB_NAME = PropertyValues.INSTANCE.getDbName();
	public final static String DB_COMPUTER_TABLE = "computer";
	public final static String DB_COLUMN_ID = "id";
	public final static String DB_COLUMN_NAME = "name";
	public final static String DB_COLUMN_INTRODUCED = "introduced";
	public final static String DB_COLUMN_DISCONTINUED = "discontinued";
	public final static String DB_COLUMN_COMPANY_ID = "company_id";
	public final static String DB_COLUMN_COMPANY_NAME = "companyName";
	public final static String DB_COLUMN_COUNT = "count";

	private final static String REQUEST_GET_ALL = "SELECT "
			+ ComputerDaoImpl.DB_COMPUTER_TABLE + ".*, "
			+ CompanyDaoImpl.DB_COMPANY_TABLE + "."
			+ CompanyDaoImpl.DB_COLUMN_NAME + " AS " + DB_COLUMN_COMPANY_NAME
			+ " FROM " + ComputerDaoImpl.DB_COMPUTER_TABLE + " LEFT JOIN "
			+ CompanyDaoImpl.DB_COMPANY_TABLE + " ON "
			+ ComputerDaoImpl.DB_COMPUTER_TABLE + "."
			+ ComputerDaoImpl.DB_COLUMN_COMPANY_ID + "="
			+ CompanyDaoImpl.DB_COMPANY_TABLE + "."
			+ CompanyDaoImpl.DB_COLUMN_ID;

	private final static String REQUEST_GET_BY_ID = REQUEST_GET_ALL + " WHERE "
			+ ComputerDaoImpl.DB_COMPUTER_TABLE + ".id=?";

	private final static String REQUEST_ADD = "INSERT INTO `" + DB_NAME + "`.`"
			+ DB_COMPUTER_TABLE + "` (`" + DB_COLUMN_NAME + "`, `"
			+ DB_COLUMN_INTRODUCED + "`, `" + DB_COLUMN_DISCONTINUED + "`, `"
			+ DB_COLUMN_COMPANY_ID + "`) VALUES" + "(?,?,?,?)";

	private final static String REQUEST_UPDATE = "UPDATE  `" + DB_NAME + "`.`"
			+ DB_COMPUTER_TABLE + "` SET " + "`name`=?" + ",`introduced`=?"
			+ ",`discontinued`=?" + ",`company_id`=?" + " WHERE id=?";

	private final static String REQUEST_DELETE = "DELETE FROM "
			+ DB_COMPUTER_TABLE + " WHERE " + DB_COLUMN_ID + " =?";
	
	private final static String REQUEST_MULTIPLE_DELETE = "DELETE FROM "
			+ DB_COMPUTER_TABLE + " WHERE " + DB_COLUMN_ID + " in (?)";
	
	private final static String REQUEST_GET_BY_PAGE = REQUEST_GET_ALL
			+ " WHERE computer."+DB_COLUMN_NAME+" LIKE ? ORDER BY " + DB_COLUMN_ID + " LIMIT ?,?";

	private final static String REQUEST_FILTER_BY_NAME = REQUEST_GET_ALL
			+ " WHERE " + DB_COLUMN_NAME + "?";

	private final static String REQUEST_COUNT = "SELECT COUNT(*) AS "
			+ DB_COLUMN_COUNT + " FROM " + DB_COMPUTER_TABLE +" WHERE name like ?";

	public ComputerDaoImpl() {
	}

	public boolean add(Computer computer) {

		if (computer == null)
			return false;

		PreparedStatement preparedStatement = null;
		boolean isSuccess = false;

		try {
			connection = DbConnection.INSTANCE.getConnection();
			preparedStatement = connection.prepareStatement(REQUEST_ADD);

			preparedStatement.setString(1, computer.getName());
			if (computer.getIntroduced() == null)
				preparedStatement.setNull(2, java.sql.Types.TIMESTAMP);
			else
				preparedStatement.setTimestamp(2,
						Timestamp.valueOf(computer.getIntroduced()));

			if (computer.getDiscontinued() == null)
				preparedStatement.setNull(3, java.sql.Types.TIMESTAMP);
			else
				preparedStatement.setTimestamp(3,
						Timestamp.valueOf(computer.getDiscontinued()));

			if (computer.getCompany() != null
					&& computer.getCompany().getId() != 0)
				preparedStatement.setInt(4, computer.getCompany().getId());
			else
				preparedStatement.setNull(4, java.sql.Types.BIGINT);

			int rs = preparedStatement.executeUpdate();

			if (rs > 0)
				isSuccess = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
			DbUtils.closePreparedStatement(preparedStatement);
		}
		return isSuccess;
	}

	public Computer getByID(int computerId) {

		if (computerId < 0)
			return null;

		PreparedStatement preparedStatement = null;
		Computer computer = null;
		ResultSet rs = null;

		try {
			connection = DbConnection.INSTANCE.getConnection();
			preparedStatement = connection.prepareStatement(REQUEST_GET_BY_ID);
			preparedStatement.setInt(1, computerId);
			rs = preparedStatement.executeQuery();

			if (rs.first())
				computer = ComputerMapper.INSTANCE.getObject(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
			DbUtils.closePreparedStatement(preparedStatement);
			DbUtils.closeResultSet(rs);
		}
		return computer;
	}

	public boolean update(Computer computer) {

		boolean isSuccess = false;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbConnection.INSTANCE.getConnection();
			preparedStatement = connection.prepareStatement(REQUEST_UPDATE);
			preparedStatement.setString(1, computer.getName());

			if (computer.getIntroduced() == null)
				preparedStatement.setNull(2, java.sql.Types.TIMESTAMP);
			else
				preparedStatement.setTimestamp(2,
						Timestamp.valueOf(computer.getIntroduced()));

			if (computer.getDiscontinued() == null)
				preparedStatement.setNull(3, java.sql.Types.TIMESTAMP);
			else
				preparedStatement.setTimestamp(3,
						Timestamp.valueOf(computer.getDiscontinued()));
			if (computer.getCompany() != null
					&& computer.getCompany().getId() != 0)
				preparedStatement.setInt(4, computer.getCompany().getId());
			else
				preparedStatement.setNull(4, java.sql.Types.BIGINT);

			preparedStatement.setInt(5, computer.getId());

			int rs = preparedStatement.executeUpdate();

			if (rs > 0) {
				isSuccess = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
			DbUtils.closePreparedStatement(preparedStatement);
		}

		return isSuccess;
	}

	public boolean deleteById(int computerId) {

		if (computerId < 0)
			return false;

		boolean isSuccess = false;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbConnection.INSTANCE.getConnection();

			preparedStatement = connection.prepareStatement(REQUEST_DELETE);
			preparedStatement.setInt(1, computerId);

			int rs = preparedStatement.executeUpdate();
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
			DbUtils.closePreparedStatement(preparedStatement);
		}
		return isSuccess;
	}

	public List<Computer> getByPage(int index, int nbEntityByPage, String name) {
		List<Computer> computerList = new ArrayList<Computer>();

		if (index < 0)
			return computerList;

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DbConnection.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement(REQUEST_GET_BY_PAGE);
			if (name == null)
				name = "";
			preparedStatement.setString(1, "%" + name + "%");
			preparedStatement.setInt(2, index * nbEntityByPage);
			preparedStatement.setInt(3, nbEntityByPage);
			rs = preparedStatement.executeQuery();
			computerList = ComputerMapper.INSTANCE.getList(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
			DbUtils.closePreparedStatement(preparedStatement);
		}
		return computerList;
	}

	public List<Computer> getAll() {
		List<Computer> computerList = new ArrayList<Computer>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = DbConnection.INSTANCE.getConnection();
			preparedStatement = connection.prepareStatement(REQUEST_GET_ALL);
			rs = preparedStatement.executeQuery();
			computerList = ComputerMapper.INSTANCE.getList(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
			DbUtils.closePreparedStatement(preparedStatement);
			DbUtils.closeResultSet(rs);
		}
		return computerList;
	}

	@Override
	public int getCount(String name) {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			connection = DbConnection.INSTANCE.getConnection();
			preparedStatement = connection.prepareStatement(REQUEST_COUNT);
			if(name == null)
				name = "";
			preparedStatement.setString(1, "%"+ name + "%");
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt(DB_COLUMN_COUNT);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
			DbUtils.closePreparedStatement(preparedStatement);
		}
		return count;
	}

	@Override
	public List<Computer> getByName(String name) {
		List<Computer> computerList = new ArrayList<Computer>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = DbConnection.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement(REQUEST_FILTER_BY_NAME);
			preparedStatement.setString(1, name);
			rs = preparedStatement.executeQuery();
			computerList = ComputerMapper.INSTANCE.getList(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
			DbUtils.closePreparedStatement(preparedStatement);
			DbUtils.closeResultSet(rs);
		}
		return computerList;
	}

	@Override
	public boolean deleteMultipleById(String computersId) {	
		boolean isSuccess = false;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbConnection.INSTANCE.getConnection();

			preparedStatement = connection.prepareStatement(REQUEST_MULTIPLE_DELETE);
			preparedStatement.setString(1, computersId);
			int rs = preparedStatement.executeUpdate();
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
			DbUtils.closePreparedStatement(preparedStatement);
		}
		return isSuccess;	}
}
