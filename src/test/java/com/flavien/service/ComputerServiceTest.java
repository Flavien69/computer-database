package com.flavien.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.flavien.dao.ComputerDao;
import com.flavien.dto.ComputerMapperDTO;
import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.models.Page;
import com.flavien.service.impl.ComputerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ComputerServiceTest {
	private ComputerService cut;
	@Mock
	private ComputerDao computerDao;
	private Computer computer;
	private List<Computer> computers;
	@Autowired
	private ComputerMapperDTO computerMapperDTO;
	private Page page;
	private static final int COUNT_TOTAL = 20;

	@Before
	public void setUp() {
		Company company = new Company.Builder().id(2).name("super company").build();
		computer = new Computer.Builder().id(2).name("test").company(company).build();
		page = new Page();

		computers = new ArrayList<>();
		for (int i = 0; i < COUNT_TOTAL; i++)
			computers.add(computer);

		page.setIndex(1);
		page.setEntityByPage(10);
		page.setComputerList(computerMapperDTO.listToDto(computers));

		when(computerDao.getAll()).thenReturn(computers);
		when(computerDao.getByID(3)).thenReturn(computer);
		when(computerDao.getByPage(page, "test")).thenReturn(page);
		when(computerDao.getCount("test")).thenReturn(10);

		cut = new ComputerServiceImpl(computerDao);
	}

	@Test
	public void TestGetAll() {
		List<Computer> computers = cut.getAll();
		Assert.assertEquals(computers.size(), COUNT_TOTAL);
	}

	@Test
	public void TestGetById() {
		Computer computerReturn = cut.getByID(3);
		Assert.assertEquals(computerReturn, computer);

		computerReturn = cut.getByID(300);
		Assert.assertNull(computerReturn);
	}

	@Test
	public void TestGetPage() {

		Page p = cut.getByPage(page, "test");
		Assert.assertEquals(p.getComputerList().size(), computerMapperDTO.listToDto(computers).size());

	}
}
