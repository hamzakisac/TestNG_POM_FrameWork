package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class HomePageTest {
	
	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	ElementUtil eUtil;
	
	@BeforeTest
	public void setUp() throws InterruptedException {
		basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init_driver(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
		//homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1, description="Verifies the HomePageTitle")
	public void verifyHomePageTitleTest() {
		eUtil = new ElementUtil(driver);
		eUtil.waitForTitlePresent("Account Setup | HubSpot");
		String title = homePage.getHomePageTitle();
		System.out.println("HomePage Title is : " + title);
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2, description="Verifies the HomePageHeader")
	public void verifyHomePageHeaderTest() {
		String header = homePage.getHomePageHeader();
		System.out.println("HomePageHeader is : " +header);
		Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3, description="Verifies the AccountName")
	public void getAccountNameTest() {
		String accountName = homePage.getLoggedInUserAccountName();
		System.out.println("Account Name is : " + accountName);
		Assert.assertEquals(accountName, AppConstants.ACCOUNT_NAME);
	}
	
	@AfterTest
	public void tearDowntest() {
		driver.quit();
	}
	

}
