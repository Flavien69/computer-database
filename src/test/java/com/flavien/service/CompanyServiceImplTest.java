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

import com.flavien.dao.impl.CompanyDaoImpl;
import com.flavien.models.Company;
import com.flavien.service.impl.CompanyServiceImpl;
import com.flavien.utils.ScriptRunner;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceImplTest {
	private CompanyServiceImpl cut;
	@Mock private CompanyDaoImpl companyDao;
	private Company company;
	List<Company> companies;

	 @Before
	 public void setUp(){
		 company = new Company(2, "super company");
		 
		 companies = new ArrayList<>();
		 for(int i = 0; i< ScriptRunner.COUNT_TOTAL_COMPANY; i++)
			 companies.add(company);
		 
		 when(companyDao.getAll()).thenReturn(companies);
		 when(companyDao.getByID(2)).thenReturn(company);

		 cut = new CompanyServiceImpl(companyDao,null);
	 }
	 
	 @Test
	 public void TestGetAll(){
		 List<Company> companies = cut.getAll();
		 Assert.assertEquals(companies.size(), ScriptRunner.COUNT_TOTAL_COMPANY);
	 }
	 
	 @Test
	 public void TestGetById(){
		 Company companyReturn = cut.getByID(2);
		 Assert.assertEquals(companyReturn, company);
		 
		 companyReturn = cut.getByID(300);
		 Assert.assertNull(companyReturn);
	 }
}
