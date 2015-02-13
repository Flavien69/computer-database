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
import com.flavien.models.Page;
import com.flavien.utils.ScriptRunner;
 
public class ComputerDaoImplTest {
	 private ComputerDaoImpl cut = DaoManager.INSTANCE.getComputerDaoImpl();
	 private static final int COUNT_TOTAL = 20;
	 @Mock private Company company;
	 
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
		 int count = cut.getCount();		 
		 Assert.assertEquals(count, COUNT_TOTAL);
	 }
	 
	 @Test
	 public void testDelete(){
		 cut.deleteById(3);
		 int count = cut.getCount();	
		 Assert.assertEquals(count, COUNT_TOTAL-1);
	 }
	 
	 @Test
	 public void testAdd(){
		 boolean isSuccess = cut.add(new Computer());
		 Assert.assertEquals(isSuccess, true);
		 
		 int count = cut.getCount();		 
		 Assert.assertEquals(count, COUNT_TOTAL+1);
	 }
	 
	 @Test
	 public void testGetByPage(){
		 List<Computer> computers = cut.getByPage(10000);
		 Assert.assertEquals(computers.size(), 0);
		 
		 computers = cut.getByPage(0);
		 Assert.assertEquals(computers.size(), Page.NB_ENTITY_BY_PAGE);
	 }
	 
	 @Test
	 public void testGetAll(){
		 List<Computer> computers = cut.getAll();
		 Assert.assertEquals(computers.size(), COUNT_TOTAL);
	 }	 
}
