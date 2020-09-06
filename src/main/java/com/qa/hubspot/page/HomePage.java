package com.qa.hubspot.page;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage{
	
	WebDriver driver;
	ElementUtil eUtil;
	
	//By header = By.xpath("//i18n-string[@data-key='app.header.title']");
	By header = By.cssSelector("h1.dashboard-selector__title");
	By accountName = By.cssSelector("span.account-name ");
	By mainContacts = By.id("nav-primary-contacts-branch");
	By childContacts = By.id("nav-secondary-contacts");

	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle() {
		return eUtil.doGetPageTitle();
	}
	
	public String getHomePageHeader() {
		return eUtil.doGetText(header);
	}
	
	public String getLoggedInUserAccountName() {
		
		return eUtil.doGetText(accountName);
		}
	
	public void clickOnContacts() {
		eUtil.waitForElementPresent(mainContacts);
		eUtil.doClick(mainContacts);
		eUtil.waitForElementPresent(childContacts);
		eUtil.doClick(childContacts);
	}
	
	public ContactsPage goToContactsPage() {
		return new ContactsPage(driver);
	}
	

	

}
