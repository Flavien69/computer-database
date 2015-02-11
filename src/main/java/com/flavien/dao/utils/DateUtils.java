package com.flavien.dao.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public final class DateUtils {

	private DateUtils() {}

	/**
	 * Permit to convert a Timestamp date into a LocalDateTime
	 * 
	 * @param Timestamp
	 * @return LocalDateTime from the jodatime library
	 */
	public static LocalDateTime getLocalDate(Timestamp timestamp) {
		if (timestamp != null)
			return timestamp.toLocalDateTime();
		else
			return null;
	}
}
