package com.flavien.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;

import com.flavien.dao.repository.CompanyRepository;
import com.flavien.dao.repository.ComputerRepository;
import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.service.impl.CompanyServiceImpl;
import com.flavien.utils.ScriptRunner;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-context-dao-test.xml" })
public class CompanyServiceTest {
	private CompanyService cut;
	@Mock
	private CompanyRepository companyRepository;
	@Mock
	private ComputerRepository computerRepository;
	@Mock
	private Connection connection;
	private Company company;
	private Computer computer;
	private List<Company> companies = new ArrayList<>();
	private List<Computer> computers = new ArrayList<>();

	
	@Before
	public void setUp() {
		company = new Company.Builder().id(2).name("super company").build();
		computer = new Computer.Builder().id(10).name("test").build();

		for (int i = 0; i < ScriptRunner.COUNT_TOTAL_COMPANY; i++)
			companies.add(company);

		for (int i = 0; i < ScriptRunner.COUNT_TOTAL_COMPUTER; i++)
			computers.add(computer);

		//Mock the companyDao
		when(companyRepository.findAll()).thenReturn(companies);
		when(companyRepository.findOne(2)).thenReturn(company);
		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) {
				companies.remove(2);
				return null;
			}
		}).when(companyRepository).delete(any(int.class));
		
		//Mock the computerDao
		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) {
				computers.remove(2);
				computers.remove(5);
				return null;
			}
		}).when(computerRepository).deleteByCompanyId(any(int.class));

		cut = new CompanyServiceImpl(companyRepository, computerRepository);
	}

	@Test
	public void deleteById() {
		cut.deleteByID(2);
		Assert.assertEquals(companies.size(), ScriptRunner.COUNT_TOTAL_COMPANY -1);
		Assert.assertEquals(computers.size(), ScriptRunner.COUNT_TOTAL_COMPUTER -2);
	}
	
	@Test
	public void TestGetAll() {
		List<Company> companies = cut.getAll();
		Assert.assertEquals(companies.size(), ScriptRunner.COUNT_TOTAL_COMPANY);
	}

	@Test
	public void TestGetById() {
		Company companyReturn = cut.getByID(2);
		Assert.assertEquals(companyReturn, company);

		companyReturn = cut.getByID(300);
		Assert.assertNull(companyReturn);
	}

}
