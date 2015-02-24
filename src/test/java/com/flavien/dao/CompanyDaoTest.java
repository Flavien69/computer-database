package com.flavien.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.flavien.dao.impl.DaoManager;
import com.flavien.models.Company;
import com.flavien.utils.ScriptRunner;

public class CompanyDaoTest {
	private CompanyDao cut = DaoManager.INSTANCE.getCompanyDaoImpl();

	/**
	 * Permit to clean the test database before each test.
	 */
	@Before
	public void setUp() {
		ScriptRunner.runScript();
	}

	@Test
	public void testGetById() {
		Company company = cut.getByID(2);
		Company companyMatcher = new Company.Builder().id(2).name("Thinking Machines").build();

		Assert.assertEquals(company.getId(), 2);
		Assert.assertEquals(company.getName(), companyMatcher.getName());

		company = cut.getByID(200);
		Assert.assertNull(company);

		company = cut.getByID(-2);
		Assert.assertNull(company);
	}

	@Test
	public void testGetAll() {
		List<Company> companies = cut.getAll();
		Assert.assertEquals(companies.size(), ScriptRunner.COUNT_TOTAL_COMPANY);
	}

//	@Test
//	public void testDeleteById() {
//		Assert.assertEquals(cut.getAll().size(), 5);
//
//		Connection connection = ConnectionManager.getConnection(true, false);
//		cut.deleteByID(5);
//		ConnectionManager.closeConnection(connection, true);
//		Assert.assertEquals(cut.getAll().size(), 4);
//
//	}
}
