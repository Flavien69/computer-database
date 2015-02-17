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

import com.flavien.dao.impl.ComputerDaoImpl;
import com.flavien.dto.ComputerMapperDTO;
import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.models.Page;
import com.flavien.service.impl.ComputerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ComputerServiceImplTest {
	private ComputerServiceImpl cut;
	@Mock private ComputerDaoImpl computerDao;
	private Computer computer;
	List<Computer> computers;
	private static final int COUNT_TOTAL = 20;

	 @Before
	 public void setUp(){
		 computer = new Computer(2,"test",null,null,new Company(2, "super company"));
		 
		 computers = new ArrayList<>();
		 for(int i = 0; i< COUNT_TOTAL; i++)
			 computers.add(computer);
		 
		 when(computerDao.getAll()).thenReturn(computers);
		 when(computerDao.getByID(3)).thenReturn(computer);
		 when(computerDao.add(computer)).thenReturn(true);
		 when(computerDao.deleteById(1)).thenReturn(true);
		 
		 when(computerDao.getByPage(1,10,"test")).thenReturn(computers);
		 when(computerDao.getCount("test")).thenReturn(10);

		 cut = new ComputerServiceImpl(computerDao);
	 }
	 
	 @Test
	 public void TestGetAll(){
		 List<Computer> computers = cut.getAll();
		 Assert.assertEquals(computers.size(), COUNT_TOTAL);
	 }
	 
	 @Test
	 public void TestDeleteById(){
		 boolean isSuccess = cut.deleteById(1);
		 Assert.assertEquals(isSuccess, true);
		 
		 boolean isFailed = cut.deleteById(100);
		 Assert.assertEquals(isFailed, false);
	 }
	 
	 @Test
	 public void TestGetById(){
		 Computer computerReturn = cut.getByID(3);
		 Assert.assertEquals(computerReturn, computer);
		 
		 computerReturn = cut.getByID(300);
		 Assert.assertNull(computerReturn);
	 }
	 
	 @Test
	 public void TestGetPage(){
		 Page p = cut.getByPage(1, 10, "test");
		 Assert.assertEquals(p.getComputerList().size(), ComputerMapperDTO.listToDto(computers).size());
		 
	 }
}
