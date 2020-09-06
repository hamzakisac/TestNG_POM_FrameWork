package com.qa.hubspot.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

/**
 * 
 * @author hamzakisac
 *
 */

public class ElementUtil extends BasePage{

	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil jScriptUtil;
	Properties properties;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIMEOUT);
		jScriptUtil = new JavaScriptUtil(driver);
	}

	/**
	 * Title wait method
	 * 
	 * @param title
	 * @return
	 */
	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}

	/**
	 * wait element to be present
	 * @param locator
	 * @return
	 */
	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}
	/**
	 * 
	 * @param locator
	 * @return
	 */
	public boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}

	/**
	 * Get title
	 * 
	 * @return
	 */
	public String doGetPageTitle() {

		try {
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("Exception Occured while getting the Title...");
		}
		return null;
	}

	/**
	 * This method is used to create the webElement on the basis of by locator
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator) {
		
		WebElement element = null;

		try {
			element = driver.findElement(locator);
			if(highlightElement) {
				jScriptUtil.flash(element);
			}
		} catch (Exception e) {
			System.out.println("Exception Occured while Creating the WebElement");
		}
		return element;
	}

	/**
	 * This method is used to click the WebElement on the basis of by locator
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {

		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("Exception Occured while Clicking the WebElement");
		}
	}

	/**
	 * This method is used to send value in a field
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {

		try {
			WebElement element = getElement(locator);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("Exception Occured while Sending Value");
		}
	}

	/**
	 * isDisplayed
	 * 
	 * @param locator
	 * @return
	 */
	public boolean doIsDisplayed(By locator) {

		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("Exception Occured isDisplayed method");
		}
		return false;
	}

	/**
	 * isEnabled
	 * 
	 * @param locator
	 * @return
	 */
	public boolean doIsEnabled(By locator) {

		try {
			return getElement(locator).isEnabled();
		} catch (Exception e) {
			System.out.println("Exception Occured with isEnabled method");
		}
		return false;
	}

	/**
	 * isSelected
	 * 
	 * @param locator
	 * @return
	 */
	public boolean doIsSelected(By locator) {

		try {
			return getElement(locator).isSelected();
		} catch (Exception e) {
			System.out.println("Exception Occured isSelected method");
		}
		return false;
	}

	/**
	 * GetText()
	 * 
	 * @param locator
	 * @return
	 */
	public String doGetText(By locator) {

		try {
			return getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("Exception Occured while getting text...");
		}
		return null;
	}
	/**
	 * This method is used to select dropDown Elements by TEXT
	 * 
	 * @param element
	 * @param value
	 */
	public static void selectDropDownValueByText(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	/**
	 * This method is used to Select by INDEX
	 * 
	 * @param element
	 * @param index
	 */
	public static void selectDropDownByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}



}
