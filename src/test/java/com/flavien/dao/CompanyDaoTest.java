package com.flavien.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.flavien.models.Company;
import com.flavien.utils.ScriptRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-context-dao-test.xml" })
public class CompanyDaoTest {
	@Autowired
	private CompanyDao cut;

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
	}

	@Test
	public void testGetAll() {
		List<Company> companies = cut.getAll();
		Assert.assertEquals(companies.size(), ScriptRunner.COUNT_TOTAL_COMPANY);
	}

	@Test
	public void testDeleteById() {
		Assert.assertEquals(cut.getAll().size(), 5);
		cut.deleteByID(5);
		Assert.assertEquals(cut.getAll().size(), 4);

	}
}
