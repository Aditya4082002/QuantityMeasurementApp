package com.quantitymeasurementapp.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

	private static Properties props = new Properties();

	static {

		try (InputStream in = ApplicationConfig.class.getClassLoader().getResourceAsStream("application.properties")) {

			props.load(in);

		} catch (Exception e) {
			throw new RuntimeException("Config loading failed");
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}
}