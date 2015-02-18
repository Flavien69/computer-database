package com.flavien.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.flavien.dao.impl.ComputerDaoImpl;
import com.flavien.dao.impl.DaoManager;
import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.utils.ScriptRunner;
import com.flavien.utils.Utils;
 
public class ComputerDaoImplTest {
	 private ComputerDaoImpl cut = DaoManager.INSTANCE.getComputerDaoImpl();
	 @Mock private Company company;
	 
	 
	 /**
	  * Permit to clean the test database before each test.
	  */
	 @Before
	 public void setUp(){
		 ScriptRunner.runScript();
	 }
	 
	 @Test
	 public void testGetById(){
		 Computer computer = cut.getByID(2);
		 Computer computerMatcher = new Computer(2,"CM-2a",null,null,null);	
		 
		 Assert.assertEquals(computer.getId(), 2);
		 Assert.assertEquals(computer.getName(), computerMatcher.getName());
		 
		 computer = cut.getByID(200);
		 Assert.assertNull(computer);
		 
		 computer = cut.getByID(-2);
		 Assert.assertNull(computer);
	 }
	 
	 @Test
	 public void testGetCount(){
		 int count = cut.getCount("");		 
		 Assert.assertEquals(count, ScriptRunner.COUNT_TOTAL_COMPUTER);
		 
		 count = cut.getCount("dsf dsf");		 
		 Assert.assertEquals(count, 0);
	 }
	 
	 @Test
	 public void testDelete(){		 
		 boolean isSuccess = cut.deleteById(3);
		 int count = cut.getCount("");	
		 Assert.assertEquals(count, ScriptRunner.COUNT_TOTAL_COMPUTER-1);
		 Assert.assertEquals(true, isSuccess);
		 
		 isSuccess = cut.deleteById(-1);
		 Assert.assertEquals(false, isSuccess);
	 }
	 
	 @Test
	 public void testAdd(){
		 Computer computer = new Computer();
		 boolean isSuccess = false;
		 
		 isSuccess = cut.add(computer);
		 Assert.assertEquals(isSuccess, false);
		 
		 computer.setName("mac");
		 
		 isSuccess = cut.add(computer);
		 Assert.assertEquals(isSuccess, true);		 
		 
		 int count = cut.getCount(null);		 
		 Assert.assertEquals(count, ScriptRunner.COUNT_TOTAL_COMPUTER+1);
	 }
	 
	 @Test
	 public void testGetByPage(){
		 List<Computer> computers = cut.getByPage(10000,10,"");
		 Assert.assertEquals(computers.size(), 0);
		 
		 computers = cut.getByPage(0,10,"");
		 Assert.assertEquals(computers.size(), 10);
	 }
	 
	 @Test
	 public void testGetAll(){
		 List<Computer> computers = cut.getAll();
		 Assert.assertEquals(computers.size(), ScriptRunner.COUNT_TOTAL_COMPUTER);
	 }	 
}
