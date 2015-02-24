package com.flavien.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic interface that map the result from de database to a java object
 * 
 * @param <T>
 *
 */
public interface RowMappable<T> {
	
	/**
	 * Permit to convert a ResultSet to a list<T>
	 * @param rs
	 * @return List<T>
	 * @throws SQLException
	 */
	public default List<T> getList(ResultSet rs) throws SQLException{
		List<T> objectList = new ArrayList<T>();
		while (rs.next()) {
			T t = getObject(rs);
			objectList.add(t);
		}
		return objectList;
	}
	
	/**
	 * Permit to convert a ResultSet to a java object 
	 * @param rs
	 * @return T
	 * @throws SQLException
	 */
	public T getObject(ResultSet rs) throws SQLException;
}
