package com.flavien.dao;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.flavien.dao.impl.ComputerDaoImpl;
import com.flavien.models.Company;
import com.flavien.models.Computer;
 
public class ComputerDaoImplTest {
	 private ComputerDaoImpl cut;
	 @Mock private Company company;
	 
	 @Before
	 public void setUp(){
		 cut = ComputerDaoImpl.INSTANCE;
	 }
	 
	 @Test
	 public void testComputerName(){
		 Computer computer = cut.getByID(1);
		 Computer computerMatcher = new Computer(1,"MacBook Pro 15.4 inch",null,null,null);
		 
		 Assert.assertEquals(computer.getName(), computerMatcher.getName());
	 }
}
