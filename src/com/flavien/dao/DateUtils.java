package com.flavien.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public final class DateUtils {

	private DateUtils() {}

	public static LocalDateTime getLocalDate(Timestamp timestamp) {
		if (timestamp != null)
			return timestamp.toLocalDateTime();
		else
			return null;
	}
}
