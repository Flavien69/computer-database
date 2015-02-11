package com.flavien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Generic interface that map the result from de database to a java object
 * 
 * @author flavien
 * @param <T>
 *
 */
public interface RowMappable<T> {
	
	/**
	 * Permit to convert a ResultSet to a list<T>
	 * @param ResultSet
	 * @return List<T>
	 * @throws SQLException
	 */
	public List<T> getList(ResultSet rs) throws SQLException;
	
	/**
	 * Permit to convert a ResultSet to a java object 
	 * @param ResultSet
	 * @return T
	 * @throws SQLException
	 */
	public T getObject(ResultSet rs) throws SQLException;
}
