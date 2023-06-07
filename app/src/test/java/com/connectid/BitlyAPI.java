package com.connectid;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.connectid.utils.PropertyFile;
import com.jayway.jsonpath.JsonPath;

public class BitlyAPI {

	public static final String GROUPS = "/v4/groups";
	public static final String ACCESS_TOKEN = "/oauth/access_token";
	public static final String EXPAND_LINK = "/v4/expand";

	public static Map<String, String> configPropertiesMap;

	public static void initializeProperties() {
		configPropertiesMap = PropertyFile.readProperty();
	}

	public static String getGroups() {
		String groupId = "";
		try {
			// Create URL object with the API endpoint
			URL url = new URL(configPropertiesMap.get("base.url") + GROUPS);

			// Open connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set request method
			connection.setRequestMethod("GET");

			// Set the request header
			connection.setRequestProperty("Host", configPropertiesMap.get("host"));
			connection.setRequestProperty("Authorization", configPropertiesMap.get("authorization"));
			connection.setRequestProperty("Accept", "application/json");

			// Read the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			groupId = JsonPath.read(response.toString(), "$.groups[0].guid");

			// Close the connection
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return groupId;
	}

	public static String getAccessToken() {
		String accessToken = "";
		try {
			// Create URL object with the API endpoint
			URL url = new URL(configPropertiesMap.get("base.url") + ACCESS_TOKEN);

			// Open connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set request method
			connection.setRequestMethod("POST");

			// Set the Basic Authentication header
			String clientId = configPropertiesMap.get("client.id");
			String clientSecret = configPropertiesMap.get("client.secret");
			String auth = clientId + ":" + clientSecret;
			byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
			String authHeaderValue = "Basic " + new String(encodedAuth);
			connection.setRequestProperty("Authorization", authHeaderValue);

			// Set the "Content-Type" header
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			// Enable output and disable caching
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			// Create the form data
			Map<String, String> formData = new HashMap<>();
			formData.put("grant_type", "password");
			formData.put("username", configPropertiesMap.get("username"));
			formData.put("password", configPropertiesMap.get("password"));

			// Build the form data string
			StringBuilder formDataStringBuilder = new StringBuilder();
			for (Map.Entry<String, String> entry : formData.entrySet()) {
				if (formDataStringBuilder.length() > 0) {
					formDataStringBuilder.append("&");
				}
				formDataStringBuilder.append(entry.getKey()).append("=").append(entry.getValue());
			}

			// Convert the form data string to bytes
			byte[] formDataBytes = formDataStringBuilder.toString().getBytes(StandardCharsets.UTF_8);

			// Set the content length
			connection.setRequestProperty("Content-Length", String.valueOf(formDataBytes.length));

			// Write the form data to the request body
			try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
				outputStream.write(formDataBytes);
			}

			// Send the request and retrieve the response
			// int responseCode = connection.getResponseCode();

			// Read the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// Print the response code and response body
			// System.out.println("Response Code: " + responseCode);
			// System.out.println("Response Body: " + response.toString());

			accessToken = JsonPath.read(response.toString(), "$.access_token");

			// Close the connection
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return accessToken;
	}

	public static String getExpandURL(String accessToken, String bitlinkShortUrl) {
		String longUrl = "";
		try {
			// Create URL object with the API endpoint
			URL url = new URL(configPropertiesMap.get("base.url") + EXPAND_LINK);

			// Open connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set request method
			connection.setRequestMethod("POST");

			// Set the "Content-Type" and "Accept" headers
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			// Set the Bitly API access token
			connection.setRequestProperty("Authorization", "Bearer " + accessToken);

			// Enable output and disable caching
			connection.setDoOutput(true);

			// Set the request body data
			String requestBody = "{\"bitlink_id\": \"" + bitlinkShortUrl + "\"}";

			// Write the request body to the connection's output stream
			try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
				outputStream.writeBytes(requestBody);
			}

			// Send the request and retrieve the response
			// int responseCode = connection.getResponseCode();

			// Read the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// Print the response code and response body
			// System.out.println("Response Code: " + responseCode);
			// System.out.println("Response Body: " + response.toString());

			// Close the connection
			connection.disconnect();

			longUrl = JsonPath.read(response.toString(), "$.long_url");

			// Close the connection
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return longUrl;
	}
}
