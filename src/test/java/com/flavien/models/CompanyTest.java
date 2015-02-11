package com.flavien.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompanyTest{
	 private Company cut;
	 
	 @Before
	 public void setUp(){
		 cut = new Company(0,"companyTest");
	 }

	@Test
	public void testName() {
		Assert.assertEquals(cut.getName(), "companyTest");
	}

	@Test
	public void testId() {
		Assert.assertEquals(cut.getId(), 0);
	}
}
