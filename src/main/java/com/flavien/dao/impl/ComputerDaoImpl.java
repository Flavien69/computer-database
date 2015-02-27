package com.flavien.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.flavien.dao.ComputerDao;
import com.flavien.dao.utils.ComputerSpringMapper;
import com.flavien.dto.ComputerMapperDTO;
import com.flavien.models.Computer;
import com.flavien.models.Page;

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

	private final static String REQUEST_ADD = "INSERT INTO `" + DB_COMPUTER_TABLE + "` (`"
			+ DB_COLUMN_NAME + "`, `" + DB_COLUMN_INTRODUCED + "`, `" + DB_COLUMN_DISCONTINUED + "`, `"
			+ DB_COLUMN_COMPANY_ID + "`) VALUES" + "(?,?,?,?)";

	private final static String REQUEST_UPDATE = "UPDATE  `" + DB_COMPUTER_TABLE + "` SET "
			+ "`name`=?" + ",`introduced`=?" + ",`discontinued`=?" + ",`company_id`=?" + " WHERE id=?";

	private final static String REQUEST_DELETE = "DELETE FROM " + DB_COMPUTER_TABLE + " WHERE "
			+ DB_COLUMN_ID + " =?";

	private final static String REQUEST_DELETE_BY_COMPANY_ID = "DELETE FROM " + DB_COMPUTER_TABLE + " WHERE "
			+ DB_COLUMN_COMPANY_ID + " =?";

	private final static String REQUEST_GET_BY_PAGE = REQUEST_GET_ALL + " WHERE computer." + DB_COLUMN_NAME
			+ " LIKE ? OR " + CompanyDaoImpl.DB_COMPANY_TABLE + "." + CompanyDaoImpl.DB_COLUMN_NAME
			+ " LIKE ? ORDER BY " + DB_COLUMN_ID + " LIMIT ?,?";

	private final static String REQUEST_FILTER_BY_NAME = REQUEST_GET_ALL + " WHERE " + DB_COLUMN_NAME + "?";

	private final static String REQUEST_COUNT = "SELECT COUNT(*) AS " + DB_COLUMN_COUNT + " FROM "
			+ ComputerDaoImpl.DB_COMPUTER_TABLE + " LEFT JOIN " + CompanyDaoImpl.DB_COMPANY_TABLE + " ON "
			+ ComputerDaoImpl.DB_COMPUTER_TABLE + "." + ComputerDaoImpl.DB_COLUMN_COMPANY_ID + "="
			+ CompanyDaoImpl.DB_COMPANY_TABLE + "." + CompanyDaoImpl.DB_COLUMN_ID + " WHERE "
			+ ComputerDaoImpl.DB_COMPUTER_TABLE + "." + CompanyDaoImpl.DB_COLUMN_NAME + " like ? or "
			+ CompanyDaoImpl.DB_COMPANY_TABLE + "." + CompanyDaoImpl.DB_COLUMN_NAME + " like ?";

	public ComputerDaoImpl() {}

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

		this.jdbcTemplate.update(REQUEST_ADD, computer.getName(), introducedTimestamp, discontinuedTimestamp,
				companyId);
		logger.info("add the computer");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#getByID(int)
	 */
	@Override
	public Computer getByID(int computerId) {
		return this.jdbcTemplate.queryForObject(REQUEST_GET_BY_ID, new Object[] { computerId },
				computerSpringMapper);
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

		this.jdbcTemplate.update(REQUEST_UPDATE, computer.getName(), introducedTimestamp,
				discontinuedTimestamp, companyId, computer.getId());
		logger.info("edit the computer " + computer.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#deleteById(int)
	 */
	@Override
	public void deleteById(int computerId) {
		this.jdbcTemplate.update(REQUEST_DELETE, computerId);
		logger.info("delete the computer with the id " + computerId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#getByPage(com.flavien.models.Page,
	 * java.lang.String)
	 */
	@Override
	public Page getByPage(Page page, String name) {
		String nameRequest = "%" + name + "%";
		List<Computer> computerList = this.jdbcTemplate.query(REQUEST_GET_BY_PAGE, new Object[] {
				nameRequest, nameRequest, page.getIndex() * page.getEntityByPage(), page.getEntityByPage() },
				computerSpringMapper);
		page.setComputerList(ComputerMapperDTO.listToDto(computerList));
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#getAll()
	 */
	@Override
	public List<Computer> getAll() {
		List<Computer> computerList = this.jdbcTemplate.query(REQUEST_GET_ALL, computerSpringMapper);
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
		return this.jdbcTemplate.queryForObject(REQUEST_COUNT, new Object[] { nameRequest, nameRequest },
				Integer.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flavien.dao.ComputerDao#getByName(java.lang.String)
	 */
	@Override
	public List<Computer> getByName(String name) {
		String nameRequest = "%" + name + "%";
		List<Computer> computerList = this.jdbcTemplate.query(REQUEST_FILTER_BY_NAME,
				new Object[] { nameRequest }, computerSpringMapper);
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
		this.jdbcTemplate.update(REQUEST_DELETE_BY_COMPANY_ID, companyId);
		logger.info("delete the computer where id = " + companyId);
	}
}
