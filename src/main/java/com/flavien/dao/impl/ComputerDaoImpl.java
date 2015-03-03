package com.flavien.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.flavien.dao.ComputerDao;
import com.flavien.dao.utils.ComputerSpringMapper;
import com.flavien.dto.ComputerMapperDTO;
import com.flavien.exception.PersistenceException;
import com.flavien.models.Computer;
import com.flavien.models.Page;
import com.flavien.models.Page.SortCriteria;
import com.flavien.models.Page.SortOrder;

/**
 * 
 * Interface to handle database requests for computer object.
 *
 */
@Repository
public class ComputerDaoImpl implements ComputerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ComputerSpringMapper computerSpringMapper;

	@Autowired
	private ComputerMapperDTO computerMapperDTO;

	private final static Logger logger = LoggerFactory.getLogger(ComputerDaoImpl.class);

	public final static String DB_COMPUTER_TABLE = "computer";
	public final static String DB_COLUMN_ID = "id";
	public final static String DB_COLUMN_NAME = "name";
	public final static String DB_COLUMN_INTRODUCED = "introduced";
	public final static String DB_COLUMN_DISCONTINUED = "discontinued";
	public final static String DB_COLUMN_COMPANY_ID = "company_id";
	public final static String DB_COLUMN_COMPANY_NAME = "companyName";
	public final static String DB_COLUMN_COUNT = "count";

	private final static String REQUEST_GET_ALL = "SELECT " + ComputerDaoImpl.DB_COMPUTER_TABLE + ".*, "
			+ CompanyDaoImpl.DB_COMPANY_TABLE + "." + CompanyDaoImpl.DB_COLUMN_NAME + " AS "
			+ DB_COLUMN_COMPANY_NAME + " FROM " + ComputerDaoImpl.DB_COMPUTER_TABLE + " LEFT JOIN "
			+ CompanyDaoImpl.DB_COMPANY_TABLE + " ON " + ComputerDaoImpl.DB_COMPUTER_TABLE + "."
			+ ComputerDaoImpl.DB_COLUMN_COMPANY_ID + "=" + CompanyDaoImpl.DB_COMPANY_TABLE + "."
			+ CompanyDaoImpl.DB_COLUMN_ID;

	private final static String REQUEST_GET_BY_ID = REQUEST_GET_ALL + " WHERE "
			+ ComputerDaoImpl.DB_COMPUTER_TABLE + ".id=?";

	private final static String REQUEST_ADD = "INSERT INTO `" + DB_COMPUTER_TABLE + "` (`" + DB_COLUMN_NAME
			+ "`, `" + DB_COLUMN_INTRODUCED + "`, `" + DB_COLUMN_DISCONTINUED + "`, `" + DB_COLUMN_COMPANY_ID
			+ "`) VALUES" + "(?,?,?,?)";

	private final static String REQUEST_UPDATE = "UPDATE  `" + DB_COMPUTER_TABLE + "` SET " + "`name`=?"
			+ ",`introduced`=?" + ",`discontinued`=?" + ",`company_id`=?" + " WHERE id=?";

	private final static String REQUEST_DELETE = "DELETE FROM " + DB_COMPUTER_TABLE + " WHERE "
			+ DB_COLUMN_ID + " =?";

	private final static String REQUEST_DELETE_BY_COMPANY_ID = "DELETE FROM " + DB_COMPUTER_TABLE + " WHERE "
			+ DB_COLUMN_COMPANY_ID + " =?";

	private final static String BEGINNING_REQUEST_GET_BY_PAGE = REQUEST_GET_ALL + " WHERE computer." + DB_COLUMN_NAME
			+ " LIKE ? OR " + CompanyDaoImpl.DB_COMPANY_TABLE + "." + CompanyDaoImpl.DB_COLUMN_NAME
			+ " LIKE ?";

	private final static String REQUEST_FILTER_BY_NAME = REQUEST_GET_ALL + " WHERE " + DB_COLUMN_NAME + "?";

	private final static String REQUEST_COUNT = "SELECT COUNT(*) AS " + DB_COLUMN_COUNT + " FROM "
			+ ComputerDaoImpl.DB_COMPUTER_TABLE + " LEFT JOIN " + CompanyDaoImpl.DB_COMPANY_TABLE + " ON "
			+ ComputerDaoImpl.DB_COMPUTER_TABLE + "." + ComputerDaoImpl.DB_COLUMN_COMPANY_ID + "="
			+ CompanyDaoImpl.DB_COMPANY_TABLE + "." + CompanyDaoImpl.DB_COLUMN_ID + " WHERE "
			+ ComputerDaoImpl.DB_COMPUTER_TABLE + "." + CompanyDaoImpl.DB_COLUMN_NAME + " like ? or "
			+ CompanyDaoImpl.DB_COMPANY_TABLE + "." + CompanyDaoImpl.DB_COLUMN_NAME + " like ?";

	public ComputerDaoImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#add(com.flavien.models.Computer)
	 */
	@Override
	public void add(Computer computer) {
		Timestamp introducedTimestamp = null;
		Timestamp discontinuedTimestamp = null;
		Integer companyId = null;

		if (computer.getIntroduced() != null)
			introducedTimestamp = Timestamp.valueOf(computer.getIntroduced());

		if (computer.getDiscontinued() != null)
			introducedTimestamp = Timestamp.valueOf(computer.getDiscontinued());

		if (computer.getCompany() != null && computer.getCompany().getId() != 0)
			companyId = computer.getCompany().getId();

		try {
			this.jdbcTemplate.update(REQUEST_ADD, computer.getName(), introducedTimestamp,
					discontinuedTimestamp, companyId);
		} catch (DataAccessException e) {
			throw new PersistenceException();
		}
		logger.info("add the computer");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#getByID(int)
	 */
	@Override
	public Computer getByID(int computerId) {
		Computer computer = null;
		try {
			computer = this.jdbcTemplate.queryForObject(REQUEST_GET_BY_ID, new Object[] { computerId },
					computerSpringMapper);
		} catch (DataAccessException e) {
			throw new PersistenceException();
		}
		return computer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#update(com.flavien.models.Computer)
	 */
	@Override
	public void update(Computer computer) {
		Timestamp introducedTimestamp = null;
		Timestamp discontinuedTimestamp = null;
		Integer companyId = null;

		if (computer.getIntroduced() != null)
			introducedTimestamp = Timestamp.valueOf(computer.getIntroduced());

		if (computer.getDiscontinued() != null)
			introducedTimestamp = Timestamp.valueOf(computer.getDiscontinued());

		if (computer.getCompany() != null && computer.getCompany().getId() != 0)
			companyId = computer.getCompany().getId();

		try {
			this.jdbcTemplate.update(REQUEST_UPDATE, computer.getName(), introducedTimestamp,
					discontinuedTimestamp, companyId, computer.getId());
		} catch (DataAccessException e) {
			throw new PersistenceException();
		}
		logger.info("edit the computer " + computer.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#deleteById(int)
	 */
	@Override
	public void deleteById(int computerId) {
		try {
			this.jdbcTemplate.update(REQUEST_DELETE, computerId);
		} catch (DataAccessException e) {
			throw new PersistenceException();
		}
		logger.info("delete the computer with the id " + computerId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#getByPage(com.flavien.models.Page,
	 * java.lang.String)
	 */
	@Override
	public Page getByPage(Page page) {
		String nameRequest = "%" + page.getSearch() + "%";
		List<Computer> computerList = new ArrayList<>();
		String orderFeature = null;
		
		if (SortCriteria.COMPANY_NAME.equals(page.getSortCriteria())) {
		  orderFeature = getOrderFeature("company", page.getSortCriteria(), page.getSortOrder());
		} else {
		  orderFeature = getOrderFeature("computer", page.getSortCriteria(), page.getSortOrder());
		}
		String request = BEGINNING_REQUEST_GET_BY_PAGE+" ORDER BY "+ orderFeature+" LIMIT ?,?";
		try {
			computerList = this.jdbcTemplate.query(request, new Object[] { nameRequest,
					nameRequest, page.getIndex() * page.getNbEntityByPage(), page.getNbEntityByPage() },
					computerSpringMapper);
		} catch (DataAccessException e) {
			throw new PersistenceException();
		}
		page.setComputerList(computerMapperDTO.listToDto(computerList));
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#getAll()
	 */
	@Override
	public List<Computer> getAll() {
		List<Computer> computerList = new ArrayList<>();
		try {
			computerList = this.jdbcTemplate.query(REQUEST_GET_ALL, computerSpringMapper);
		} catch (DataAccessException e) {
			throw new PersistenceException();
		}
		logger.info("retrieve all the computers");
		return computerList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#getCount(java.lang.String)
	 */
	@Override
	public int getCount(String name) {
		String nameRequest = "%" + name + "%";
		int count = 0;
		try {
			count = this.jdbcTemplate.queryForObject(REQUEST_COUNT,
					new Object[] { nameRequest, nameRequest }, Integer.class);
		} catch (DataAccessException e) {
			throw new PersistenceException();
		}
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#getByName(java.lang.String)
	 */
	@Override
	public List<Computer> getByName(String name) {
		String nameRequest = "%" + name + "%";
		List<Computer> computerList = new ArrayList<>();
		try {
			computerList = this.jdbcTemplate.query(REQUEST_FILTER_BY_NAME, new Object[] { nameRequest },
					computerSpringMapper);
		} catch (DataAccessException e) {
			throw new PersistenceException();
		}
		return computerList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#deleteByCompanyId(int,
	 * java.sql.Connection)
	 */
	@Override
	public void deleteByCompanyId(int companyId) {
		try {
			this.jdbcTemplate.update(REQUEST_DELETE_BY_COMPANY_ID, companyId);
		} catch (DataAccessException e) {
			throw new PersistenceException();
		}
		logger.info("delete the computer where id = " + companyId);
	}

	private String getOrderFeature(String entity, SortCriteria sortCriterion, SortOrder sortOrder) {

		StringBuilder stringBuilder = new StringBuilder(entity);

		switch (sortCriterion) {

		case ID:
			stringBuilder.append(".id");
			break;
		case NAME:
			stringBuilder.append(".name");
			break;
		case DATE_DISCONTINUED:
			stringBuilder.append(".discontinued");
			break;
		case DATE_INTRODUCED:
			stringBuilder.append(".introduced");
			break;
		case COMPANY_NAME:
			stringBuilder.append(".name");
			break;
		default:
			stringBuilder.append(".id");
		}

		if (sortOrder.equals(SortOrder.DESC)) {
			stringBuilder.append(" desc");
		} else {
			stringBuilder.append(" asc");
		}

		return stringBuilder.toString();

	}
}
