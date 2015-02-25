package com.flavien.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.flavien.dao.impl.DaoManager;
import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.models.Page;
import com.flavien.utils.ScriptRunner;

public class ComputerDaoTest {
	private ComputerDao cut = DaoManager.INSTANCE.getComputerDaoImpl();
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
		Computer computer = cut.getByID(2);
		Computer computerMatcher = new Computer.Builder().id(2).name("CM-2a").build();

		Assert.assertEquals(computer.getId(), 2);
		Assert.assertEquals(computer.getName(), computerMatcher.getName());

		computer = cut.getByID(200);
		Assert.assertNull(computer);

		computer = cut.getByID(-2);
		Assert.assertNull(computer);
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
		cut.deleteById(3);
		int count = cut.getCount("");
		Assert.assertEquals(count, ScriptRunner.COUNT_TOTAL_COMPUTER - 1);
	}

	@Test
	public void testAdd() {
		Computer computer =new Computer.Builder().name("test").build(); 

		cut.add(computer);
		int count = cut.getCount("");
		Assert.assertEquals(count, ScriptRunner.COUNT_TOTAL_COMPUTER + 1);
	}

	@Test
	public void testGetByPage() {
		Page page = new Page();
		page.setIndex(10000);
		page.setEntityByPage(10);
		Page p = cut.getByPage(page, "");
		Assert.assertEquals(p.getComputerList().size(), 0);

		p.setIndex(0);
		p = cut.getByPage(p, "");
		Assert.assertEquals(p.getComputerList().size(), 10);
	}

	@Test
	public void testGetAll() {
		List<Computer> computers = cut.getAll();
		Assert.assertEquals(computers.size(), ScriptRunner.COUNT_TOTAL_COMPUTER);
	}

	@Test
	public void testDeleteByCompany() {
		Assert.assertEquals(cut.getAll().size(), ScriptRunner.COUNT_TOTAL_COMPUTER);

		cut.deleteByCompanyId(3);
		Assert.assertEquals(cut.getAll().size(), ScriptRunner.COUNT_TOTAL_COMPUTER - 2);
	}
}
