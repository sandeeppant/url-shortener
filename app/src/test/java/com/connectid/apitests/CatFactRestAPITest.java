package com.connectid.apitests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.connectid.CatFactRestAPI;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class CatFactRestAPITest {
	private static final Logger logger = LogManager.getLogger(CatFactRestAPITest.class);

	@Test
	public void verifyCatFactRestAPI() throws IOException {
		CatFactRestAPI.initializeProperties();
		String response = CatFactRestAPI.getFacts();
		JSONArray names = JsonPath.read(response, "$.data[*].fact");
		Assert.assertNotNull(names);
		logger.info("factName: " + names);
		String response1 = CatFactRestAPI.getFact();
		String factName1 = JsonPath.read(response1, "$.fact");
		Assert.assertNotNull(factName1);
		logger.info("factName1: " + factName1);
	}
}
