package com.connectid.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyFile {

	public static Map<String, String> readProperty() {

		Map<String, String> configPropertiesMap = new HashMap<String, String>();

		// Create a Properties object
		Properties properties = new Properties();

		try {
			// Load the property file
			ClassLoader classLoader = PropertyFile.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("config.properties");
			properties.load(inputStream);
			inputStream.close();

			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				configPropertiesMap.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configPropertiesMap;
	}
}
