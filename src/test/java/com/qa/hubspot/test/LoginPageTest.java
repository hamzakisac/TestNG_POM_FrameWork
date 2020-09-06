package com.qa.hubspot.test;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.sun.org.glassfish.gmbal.Description;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Epic("Epic - 101 : create login features")
@Feature("US - 501 : Create test for login on Hubspot")
//US -> User Stories
public class LoginPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
	
	Logger log = Logger.getLogger(LoginPageTest.class);
	

	@BeforeMethod(alwaysRun = true)
	@Parameters(value= {"browser"})
	public void setUp(String browser) {
		String browserName = null;
		basePage = new BasePage();
		prop = basePage.init_properties();
		
		if (browser.equals(null)) {
			browserName = prop.getProperty("browser");		
		}else {
			browserName = browser;
		}

		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		log.info("url is launched " + prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1,groups = "sanity", description = "Verifies the LoginPageTitle", enabled = true)
	@Description("Verify Login Page")
	@Severity(SeverityLevel.NORMAL)
	public void verifyPageTitleTest() {
		log.info("Starting ---------------------------------->>>>> verifyLoginPageTest");
		String title = loginPage.getLoginPageTitle();
		System.out.println("LoginPageTitle is " + title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE, "*Title Changed*");
		log.info("Ending ---------------------------------->>>>> verifyLoginPageTest");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
	}

	@Test(priority = 2, description = "Verifies if Sign Up link is displayed", enabled = true)
	@Description("Verify SignUpLink")
	@Severity(SeverityLevel.NORMAL)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.checkSignUpLink());
	}

	@Test(priority = 3, description = "Login with Invalid Credentials" , enabled = true)
	@Description("Verify LoginPageCredentials")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() throws InterruptedException {
		HomePage homePage = loginPage.doLogin(userCred);
		String accountName = homePage.getLoggedInUserAccountName();
		System.out.println("Logged in Account Name : " + accountName);
		Assert.assertEquals(accountName, AppConstants.ACCOUNT_NAME);
		// loginPage.doLogin(prop.getProperty("username"),
		// prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] getLoginInvalidData() {

		Object data[][] = { { "hamza@gmail.com" , "Test@123" }, 
							{"hamza@gmail.com" , " " }, 
							{" " , "Test@123" },
							{"hamza" , "hamza" }, 
							{" " , " " } };
		return data;
	}

	@Test(priority = 4, dataProvider = "getLoginInvalidData", enabled = false)
	public void login_invalidTestCase(String username, String pwd) throws InterruptedException {
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
