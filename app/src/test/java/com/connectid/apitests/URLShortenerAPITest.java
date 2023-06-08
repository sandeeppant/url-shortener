package com.connectid.apitests;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.connectid.BitlyAPI;
import com.connectid.utils.ReadCSV;

public class URLShortenerAPITest {
	private static final Logger logger = LogManager.getLogger(URLShortenerAPITest.class);

	@Test
	public void verifyValidShorterURL() throws IOException {
		Map<String, String> map = ReadCSV.readDataByRow("login.csv", "TC01");
		String longUrl = map.get("longUrl");
		
		BitlyAPI.initializeProperties();
		String bitlinkShortURL = "bit.ly/3IYlvxI";
		String groupId = BitlyAPI.getGroups();
		logger.info("Group Id: " + groupId);
		String access_token = BitlyAPI.getAccessToken();
		logger.info("Access Token: " + access_token);
		String longURL = BitlyAPI.getExpandURL(access_token, bitlinkShortURL);
		logger.info("Long URL: " + longURL);
		Assert.assertEquals(longURL, longUrl);
	}
}
