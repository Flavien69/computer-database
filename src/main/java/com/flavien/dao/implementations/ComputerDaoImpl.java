package com.flavien.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.flavien.dao.ComputerMapper;
import com.flavien.dao.DbConnection;
import com.flavien.dao.DbUtils;
import com.flavien.dao.interfaces.ComputerDao;
import com.flavien.models.Computer;
import com.flavien.models.Page;

public enum ComputerDaoImpl implements ComputerDao {
	INSTANCE;
	private Connection connection;

	public static final String DB_NAME = "computer-database-db";
	public final static String DB_COMPUTER_TABLE = "computer";
	public final static String DB_COLUMN_ID = "id";
	public final static String DB_COLUMN_NAME = "name";
	public final static String DB_COLUMN_INTRODUCED = "introduced";
	public final static String DB_COLUMN_DISCONTINUED = "discontinued";
	public final static String DB_COLUMN_COMPANY_ID = "company_id";
	public final static String DB_COLUMN_COMPANY_NAME = "companyName";

	private final static String REQUEST_GET_ALL = "SELECT "
			+ ComputerDaoImpl.DB_COMPUTER_TABLE + ".*, "
			+ CompanyDaoImpl.DB_COMPANY_TABLE + "." + CompanyDaoImpl.DB_COLUMN_NAME
			+ " AS " + DB_COLUMN_COMPANY_NAME + " FROM "
			+ ComputerDaoImpl.DB_COMPUTER_TABLE + " LEFT JOIN "
			+ CompanyDaoImpl.DB_COMPANY_TABLE + " ON "
			+ ComputerDaoImpl.DB_COMPUTER_TABLE + "."
			+ ComputerDaoImpl.DB_COLUMN_COMPANY_ID + "="
			+ CompanyDaoImpl.DB_COMPANY_TABLE + "." + CompanyDaoImpl.DB_COLUMN_ID;

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

	private final static String REQUEST_GET_BY_PAGE = REQUEST_GET_ALL
			+ " ORDER BY " + DB_COLUMN_ID + " LIMIT ?,"
			+ Page.NB_ENTITY_BY_PAGE;

	private ComputerDaoImpl() {
	}

	public boolean add(Computer computer) {
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

			if (computer.getCompany() != null && computer.getCompany().getId() != 0)
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
			if (computer.getCompany() != null && computer.getCompany().getId() != 0)
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

	public Page getByPage(int index) {
		Page page = new Page();
		List<Computer> computerList = new ArrayList<Computer>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DbConnection.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement(REQUEST_GET_BY_PAGE);
			preparedStatement.setInt(1, index * Page.NB_ENTITY_BY_PAGE);
			rs = preparedStatement.executeQuery();
			computerList = ComputerMapper.INSTANCE.getList(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.INSTANCE.closeConnection(connection);
			DbUtils.closePreparedStatement(preparedStatement);
			DbUtils.closeResultSet(rs);
		}
		page.setComputerList(computerList);
		page.setIndex(index);
		return page;
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
}
