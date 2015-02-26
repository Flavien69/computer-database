package com.flavien.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.flavien.exception.PersistenceException;
import com.flavien.utils.PropertyValues;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * 
 * Class that enable to get a database connection.
 *
 */
@Component
public class ConnectionManager {
	private final static Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

	private static BoneCP connectionPool = null;
	private static final String DB_HOST = "localhost";
	private static final String DB_PORT = "3306";
	public static final String DB_NAME = PropertyValues.INSTANCE.getDbName();
	private static final String DB_ARGUMENT = "?zeroDateTimeBehavior=convertToNull";
	private static final String DB_PATH = DB_NAME + DB_ARGUMENT;
	public static final String DB_USER = PropertyValues.INSTANCE.getDbUser();
	public static final String DB_PWD = PropertyValues.INSTANCE.getDbPwd();
	private static int maxConnectionsPerPartition = PropertyValues.INSTANCE.getMaxConnectionsPerPartition();
	private static int minConnectionsPerPartition = PropertyValues.INSTANCE.getMinConnectionsPerPartition();
	private static int partitionCount = PropertyValues.INSTANCE.getPartitionCount();
	private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();

	/**
	 * Initialize the connection pool once.
	 */
	static {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_PATH);
			config.setUsername(DB_USER);
			config.setPassword(DB_PWD);
			config.setDisableConnectionTracking(true);

			// 1*5 = 5 connection will be available
			config.setMinConnectionsPerPartition(minConnectionsPerPartition);
			config.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
			config.setPartitionCount(partitionCount);

			connectionPool = new BoneCP(config);
			logger.info("Total connections ==> " + connectionPool.getTotalCreatedConnections());
			ConnectionManager.setConnectionPool(connectionPool);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);
		}
	}

	/**
	 * This method must be called only once when the application stops. Don't
	 * need to call it every time when you get a connection from the Connection
	 * Pool
	 */
	public static void shutdownConnPool() {
		try {
			BoneCP connectionPool = ConnectionManager.getConnectionPool();
			logger.info("contextDestroyed");
			if (connectionPool != null) {
				connectionPool.shutdown();
				logger.info(".Connection Pooling shut downed!");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);
		}
	}

	/**
	 * Get a thread-safe connection from the BoneCP connection pool.
	 * Synchronization of the method will be done inside BoneCP source
	 * 
	 * @return connection
	 */
	public static Connection getConnection() {

		Connection connection = null;
		
		if (connectionThreadLocal.get() != null) {
			logger.debug("get a connection from the threadlocal " + connectionThreadLocal.get().hashCode());
			return connectionThreadLocal.get();
		}

		try {
			connection = connectionPool.getConnection();
			connectionThreadLocal.set(connection);
			connection.setAutoCommit(true);
			logger.debug("put a connection to the threadlocal " + connectionThreadLocal.get().hashCode());

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);
		}
		return connection;

	}

	/**
	 * Init a transactional connection and put it in the threadlocal
	 * 
	 */
	public static void initTransaction() {
		Connection connection = null;
		try {
			connection = connectionPool.getConnection();
			connection.setAutoCommit(false);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);
		}
		connectionThreadLocal.set(connection);
		logger.debug("put a connection for transaction to the threadlocal " + connectionThreadLocal.get().hashCode());
	}
	
	/**
	 * Clost a transactional connection and remove it from the threadlocal
	 * 
	 */
	public static void closeTransaction() {
		Connection connection = connectionThreadLocal.get();
		try {
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);
		}
		connectionThreadLocal.remove();
	}

	/**
	 * Close the PreparedStatement in params if not null
	 * 
	 * @param stmt
	 */
	public static void closePreparedStatement(PreparedStatement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);
		}

	}

	/**
	 * Close the rseultset in params if not null
	 * 
	 * @param rSet
	 */
	public static void closeResultSet(ResultSet rSet) {
		try {
			if (rSet != null)
				rSet.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);
		}

	}

	/**
	 * release the connection - The connection is not closed it is released and
	 * it will stay in pool.
	 * 
	 */
	public static void closeConnection() {
		logger.info("test");
		Connection connection = connectionThreadLocal.get();
		boolean isAutocomit;
		try {
			isAutocomit = connection.getAutoCommit();
			logger.info(isAutocomit+"");

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);
		}
		if (isAutocomit) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new PersistenceException(e);
			}
			connectionThreadLocal.remove();
			logger.info("remove from the threadlocal "+ connectionThreadLocal.hashCode());
		}
	}

	public static BoneCP getConnectionPool() {
		return connectionPool;
	}

	public static void setConnectionPool(BoneCP connectionPool) {
		ConnectionManager.connectionPool = connectionPool;
	}

	/**
	 * Use to rollback a transaction if an exception is throw
	 * 
	 */
	public static void rollback() {
		try {
			connectionThreadLocal.get().rollback();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new PersistenceException(e);
		}
	}

}