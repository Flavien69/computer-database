package com.flavien.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ComputerTest {
	
	 private Computer cut;
	 @Mock private Company company;
	 
	 @Before
	 public void setUp(){
		 cut = new Computer(0,"computerTest",null,null,company);
	 }
	 
	 @Test
	 public void testComputerName(){
		 Assert.assertEquals(cut.getName(), "computerTest");
	 }
}
