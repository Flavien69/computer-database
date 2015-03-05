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

import com.flavien.dao.repository.ComputerRepository;
import com.flavien.dto.mapper.ComputerMapperDTO;
import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.models.Page;
import com.flavien.service.impl.ComputerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ComputerServiceTest {
	private ComputerService cut;
	@Mock
	private ComputerRepository computerRepository;
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
		page.setNbEntityByPage(10);
		page.setSearch("test");
		page.setComputerList(computerMapperDTO.listToDto(computers));

		when(computerRepository.findAll()).thenReturn(computers);
		when(computerRepository.findOne(3)).thenReturn(computer);
		when(computerRepository.getCount("test")).thenReturn(10);

		cut = new ComputerServiceImpl(computerRepository);
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
}
