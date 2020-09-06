package com.qa.hubspot.page;

import org.openqa.selenium.By;


import org.openqa.selenium.WebDriver;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	ElementUtil eUtil;
	By emailId = By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("loginBtn");
	By signUp = By.linkText("Sign up");
	By loginErrorText = By.cssSelector("div.private-alert__inner"); 
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
	}
	
	// Actions
	public String getLoginPageTitle() {
		eUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
		return eUtil.doGetPageTitle();
	}
	
	public boolean checkSignUpLink() {
		eUtil.waitForElementVisible(signUp);
		return eUtil.doIsDisplayed(signUp);
	}
	
	public HomePage doLogin(Credentials userCred) throws InterruptedException {
		Thread.sleep(10000);
		eUtil.doSendKeys(emailId, userCred.getAppUsername());
		eUtil.doSendKeys(password, userCred.getAppPassword());
		eUtil.doClick(loginBtn);
		
		Thread.sleep(10000);
		return new HomePage(driver);
	}
	
	public boolean checkLoginErrorMessage() {
		return eUtil.doIsDisplayed(loginErrorText);
	}
	
	


}
