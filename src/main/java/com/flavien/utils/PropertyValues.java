package com.flavien.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton to get properties values from outside file.
 *
 */
public enum PropertyValues {
	INSTANCE;

	private String dbName;
	private String dbUser;
	private String dbPwd;
	private int maxConnectionsPerPartition;
	private int minConnectionsPerPartition;
	private int partitionCount;

	private PropertyValues() {
		Properties prop = new Properties();
		String propFileName = "config/db.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		try {
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName
						+ "' not found in the classpath");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		dbName = prop.getProperty("DB_NAME");
		dbUser = prop.getProperty("DB_USER");
		dbPwd = prop.getProperty("DB_PWD");
		maxConnectionsPerPartition = Integer.parseInt(prop.getProperty("bonecp.maxConnectionsPerPartition"));
		minConnectionsPerPartition = Integer.parseInt(prop.getProperty("bonecp.minConnectionsPerPartition"));
		partitionCount = Integer.parseInt(prop.getProperty("bonecp.partitionCount"));
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

	public int getMaxConnectionsPerPartition() {
		return maxConnectionsPerPartition;
	}

	public int getMinConnectionsPerPartition() {
		return minConnectionsPerPartition;
	}

	public int getPartitionCount() {
		return partitionCount;
	}
	
}
