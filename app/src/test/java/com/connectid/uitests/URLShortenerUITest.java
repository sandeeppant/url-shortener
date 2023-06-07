package com.connectid.uitests;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.connectid.BaseUITest;
import com.connectid.utils.ReadCSV;

public class URLShortenerUITest extends BaseUITest {
	private static final Logger logger = LogManager.getLogger(URLShortenerUITest.class);

	@Test
	public void verifyValidShorterURL() throws IOException {
		// Create a new instance of the ChromeDriver
		WebDriver driver = getDriver();
		Map<String, String> map = ReadCSV.readDataByRow("login.csv", "TC01");
		String shortUrl = map.get("shortUrl");
		String longUrl = map.get("longUrl");
		String longUrlTitle = map.get("longUrlTitle");
		try {
			// Open the browser and navigate to your URL shortener application
			driver.get(shortUrl);

			// Get the current URL after redirection
			String redirectedUrl = driver.getCurrentUrl();
			String redirectedUrlTitle = driver.getTitle();

			// Print the redirected URL
			logger.info("Redirected URL and Title: " + redirectedUrl + " [" + redirectedUrlTitle + "]");

			// Compare the redirected URL with the original long URL
			Assert.assertEquals(redirectedUrl, longUrl);
			Assert.assertEquals(redirectedUrlTitle, longUrlTitle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the browser
			driver.quit();
		}
	}

	@Test
	public void verifyInValidShorterURL() throws IOException {
		// Create a new instance of the ChromeDriver
		WebDriver driver = getDriver();
		Map<String, String> map = ReadCSV.readDataByRow("login.csv", "TC02");
		String shortUrl = map.get("shortUrl");
		String longUrl = map.get("longUrl");
		String longUrlTitle = map.get("longUrlTitle");
		try {
			
			// Open the browser and navigate to your URL shortener application
			driver.get(shortUrl);

			// Get the current URL after redirection
			String redirectedUrl = driver.getCurrentUrl();
			String redirectedUrlTitle = driver.getTitle();

			// Print the redirected URL
			logger.info("Redirected URL and Title: " + redirectedUrl + "[" + redirectedUrlTitle + "]");

			// Compare the redirected URL with the original long URL
			Assert.assertEquals(redirectedUrl, longUrl);
			Assert.assertEquals(redirectedUrlTitle, longUrlTitle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the browser
			driver.quit();
		}
	}
}
