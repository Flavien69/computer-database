package com.flavien.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum PropertyValues {
	INSTANCE;
	
	private String dbName;
	private String dbUser;
	private String dbPwd;
	
	private PropertyValues(){
		Properties prop = new Properties();
		String propFileName = "config/db.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
		try{
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		dbName = prop.getProperty("DB_NAME");
		dbUser = prop.getProperty("DB_USER");
		dbPwd = prop.getProperty("DB_PWD");
	}

	public String getDbName() {
		return dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDbPwd() {
		return dbPwd;
	}
}
