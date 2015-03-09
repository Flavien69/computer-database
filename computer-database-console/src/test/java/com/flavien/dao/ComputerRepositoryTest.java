package com.flavien.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flavien.dao.repository.ComputerRepository;
import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.utils.ScriptRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-context-dao-test.xml" })
public class ComputerRepositoryTest {
	@Autowired
	private ComputerRepository cut;
	@Mock
	private Company company;

	/**
	 * Permit to clean the test database before each test.
	 */
	@Before
	public void setUp() {
		ScriptRunner.runScript();
	}

	@Test
	public void testGetById() {
		Computer computer = cut.findOne(2);
		Computer computerMatcher = new Computer.Builder().id(2).name("CM-2a").build();

		Assert.assertEquals(computer.getId(), 2);
		Assert.assertEquals(computer.getName(), computerMatcher.getName());
	}

	@Test
	public void testGetCount() {
		int count = cut.getCount("");
		Assert.assertEquals(count, ScriptRunner.COUNT_TOTAL_COMPUTER);

		count = cut.getCount("dsf dsf");
		Assert.assertEquals(count, 0);
	}

	@Test
	public void testDelete() {
		cut.delete(3);
		int count = cut.getCount("");
		Assert.assertEquals(count, ScriptRunner.COUNT_TOTAL_COMPUTER - 1);
	}

	@Test
	public void testAdd() {
		Computer computer = new Computer.Builder().name("test").build();

		cut.save(computer);
		int count = cut.getCount("");
		Assert.assertEquals(count, ScriptRunner.COUNT_TOTAL_COMPUTER + 1);
	}

	@Test
	public void testGetAll() {
		List<Computer> computers = cut.findAll();
		Assert.assertEquals(computers.size(), ScriptRunner.COUNT_TOTAL_COMPUTER);
	}

	@Test
	public void testDeleteByCompany() {
		Assert.assertEquals(cut.findAll().size(), ScriptRunner.COUNT_TOTAL_COMPUTER);

		cut.deleteByCompanyId(3);
		Assert.assertEquals(cut.findAll().size(), ScriptRunner.COUNT_TOTAL_COMPUTER - 2);
	}
}
