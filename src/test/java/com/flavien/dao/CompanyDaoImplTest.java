package com.flavien.dao;

import org.junit.Before;

import com.flavien.dao.impl.CompanyDaoImpl;
import com.flavien.dao.impl.DaoManager;
import com.flavien.utils.ScriptRunner;

public class CompanyDaoImplTest {
	 private CompanyDaoImpl cut = DaoManager.INSTANCE.getCompanyDaoImpl();

	@Before
	 public void setUp(){
		 ScriptRunner.runScript();
	 }
}
