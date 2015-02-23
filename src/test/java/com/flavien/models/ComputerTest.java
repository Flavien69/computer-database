package com.flavien.models;

import static org.mockito.Mockito.when;

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
		 when(company.getName()).thenReturn("excylis");
		 when(company.getId()).thenReturn(2);

		 cut = new Computer.Builder()
		 	.id(0)
		 	.name("computerTest")
		 	.company(company)
		 	.build();
				 
	 }
	 
	 @Test
	 public void testCompute(){
		 Assert.assertEquals(cut.getId(), 0);
		 Assert.assertEquals(cut.getName(), "computerTest");
		 Assert.assertEquals(cut.getCompany().getName(), "excylis");
		 Assert.assertEquals(cut.getCompany().getId(), 2);
	 }
}
