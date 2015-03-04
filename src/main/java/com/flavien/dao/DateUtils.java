package com.flavien.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 
 *  Enable to convert a Timestamp date into a LocalDateTime
 *
 */
public final class DateUtils {

	private DateUtils() {}

	/**
	 * Enable to convert a Timestamp date into a LocalDateTime
	 * 
	 * @param timestamp
	 * @return LocalDateTime 
	 */
	public static LocalDateTime getLocalDate(Timestamp timestamp) {
		if (timestamp != null)
			return timestamp.toLocalDateTime();
		else
			return null;
	}
}
