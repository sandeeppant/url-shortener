package com.connectid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.connectid.utils.PropertyFile;

public class CatFactRestAPI {

	public static final String FACT = "/fact";
	public static final String FACTS = "/facts";

	public static Map<String, String> configPropertiesMap;

	public static void initializeProperties() {
		configPropertiesMap = PropertyFile.readProperty();
	}

	public static String getFacts() {
		String responseAsString = "";
		try {
			// Create URL object with the API endpoint
			URL url = new URL(configPropertiesMap.get("fact.base.url") + FACTS);

			// Open connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set request method
			connection.setRequestMethod("GET");

			// Set the request header
			connection.setRequestProperty("Accept", "application/json");

			// Read the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			responseAsString = response.toString();

			// Close the connection
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseAsString;
	}
	
	public static String getFact() {
		String responseAsString = "";
		try {
			// Create URL object with the API endpoint
			URL url = new URL(configPropertiesMap.get("fact.base.url") + FACT);

			// Open connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set request method
			connection.setRequestMethod("GET");

			// Set the request header
			connection.setRequestProperty("Accept", "application/json");

			// Read the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			responseAsString = response.toString();

			// Close the connection
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseAsString;
	}
}
