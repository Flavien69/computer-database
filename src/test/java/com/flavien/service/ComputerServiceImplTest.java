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
import com.flavien.models.Computer;
import com.flavien.service.impl.ComputerServiceImpl;
import com.flavien.service.impl.ServiceManager;

@RunWith(MockitoJUnitRunner.class)
public class ComputerServiceImplTest {
	private ComputerServiceImpl cut = ServiceManager.INSTANCE.getComputerServiceImpl();
	@Mock private ComputerDaoImpl computerDao;
	@Mock private Computer computer;
	private static final int COUNT_TOTAL = 15;

	 @Before
	 public void setUp(){
		 List<Computer> computers = new ArrayList<>();
		 for(int i = 0; i< COUNT_TOTAL; i++)
			 computers.add(computer);
		 
		 when(computerDao.getAll()).thenReturn(computers);
		 when(computerDao.getByID(3)).thenReturn(computer);
		 when(computerDao.add(computer)).thenReturn(true);
		 when(computerDao.deleteById(1)).thenReturn(true);
		 when(computerDao.getByPage(1)).thenReturn(computers);
	 }
	 
	 @Test
	 public void TestGetAll(){
		 List<Computer> computers = cut.getAll();
		 //Assert.assertEquals(computers.size(), COUNT_TOTAL);
	 }
	 
//	 @Test
//	 public void TestDeleteById(){
//		 boolean isSuccess = cut.deleteById(1);
//		 Assert.assertEquals(isSuccess, true);
//	 }
	 
//	 @Test
//	 public void TestGetById(){
//		 Computer computerReturn = cut.getByID(3);
//		 Assert.assertEquals(computerReturn, computer);
//	 }
}
