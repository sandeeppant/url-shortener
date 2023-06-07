package com.connectid;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseUITest {

	private static final Logger logger = LogManager.getLogger(BaseUITest.class);
	
	private WebDriver driver;

	@BeforeMethod
	public void setup() {
		// Set up the driver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info("Browser is maximized");
	}
	
	@AfterMethod
	public void teardown() {
		// Quit the driver
		driver.quit();
		logger.info("Closing all the windows");
	}
	
	public WebDriver getDriver()
	{
		return driver;
	}
}
