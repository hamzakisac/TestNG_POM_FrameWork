package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	public ChromeOptions cOptions;
	public FirefoxOptions fOptions;
	public Properties prop;
	
	public OptionsManager (Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		cOptions = new ChromeOptions();
		if (prop.getProperty("incognito").equals("yes")) cOptions.addArguments("--incognito"); 
		if (prop.getProperty("headless").equals("yes")) cOptions.addArguments("--headless");
		return cOptions;	
	}
	
	public FirefoxOptions getFireFoxOptions() {
		fOptions = new FirefoxOptions();
		if (prop.getProperty("incognito").equals("yes")) fOptions.addArguments("-private");
		if (prop.getProperty("headless").equals("yes")) fOptions.addArguments("--headless");
		return fOptions;
	}

}
