package com.flavien.integration;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


import com.flavien.utils.ScriptRunner;

public class DashboardTest {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp(){
		// On instancie notre driver, et on configure notre temps d'attente
		ScriptRunner.runScript();
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/computer-database/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// On se connecte au site
		driver.get(baseUrl);
	}

	@After
	public void tearDown(){
		driver.quit();
	}

	@Test
	public void testSearchByName() {

		WebElement resultTab = driver.findElement(By.id("results"));
		List<WebElement> results = resultTab.findElements(By.tagName("tr"));

		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));

		Assert.assertEquals(100, results.size());

		searchbox.clear();
		searchbox.sendKeys("test");
		searchsubmit.click();
		
		resultTab = driver.findElement(By.id("results"));
		results = resultTab.findElements(By.tagName("tr"));
		
		Assert.assertEquals(6, results.size());
	}
	
	@Test
	public void testPagination() {

		WebElement resultTab = driver.findElement(By.id("results"));
		List<WebElement> results = resultTab.findElements(By.tagName("tr"));
		WebElement paginationBtn10 = driver.findElement(By.linkText("10"));
		Assert.assertEquals(100, results.size());
		
		paginationBtn10.click();
		Assert.assertEquals(100, results.size());

		
		WebElement paginationBtn50 = driver.findElement(By.linkText("50"));	
		paginationBtn50.click();
		resultTab = driver.findElement(By.id("results"));
		results = resultTab.findElements(By.tagName("tr"));		
		Assert.assertEquals(50, results.size());
		
		WebElement paginationBtn100 = driver.findElement(By.linkText("100"));	
		paginationBtn100.click();
		resultTab = driver.findElement(By.id("results"));
		results = resultTab.findElements(By.tagName("tr"));		
		Assert.assertEquals(100, results.size());
	}

}
