package com.pccw.system.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {
	private static Properties config = null;

	static {
		InputStream in = PropertiesUtils.class.getClassLoader()
				.getResourceAsStream("config.properties");
		config = new Properties();
		try {
			config.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("No AreaPhone.properties defined error");
		}
	}

	// 根据key读取value
	public static String readValue(String key) {
		try {
			String value = config.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ConfigInfoError" + e.toString());
			return null;
		}
	}

	// 读取properties的全部信息
	public static Map<String, String> readAllProperties() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			@SuppressWarnings("rawtypes")
			Enumeration en = config.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String value = config.getProperty(key);
				map.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ConfigInfoError" + e.toString());
		}
		return map;
	}
	
}
