package com.flavien.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utils class to help implementing classes.
 *
 */

public class Utils {
	private final static Logger logger = LoggerFactory.getLogger(Utils.class);
	public static final String INT_REGEX = "^[0-9]*$";
	//public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
	//public static final String DATE_REGEX = "^(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])[\\s||T][0-9][0-9]:[0-9][0-9]$";
	public static final int ERROR = 0;

	/**
	 * Convert a string to int.
	 * 
	 * @param valeurString
	 * @return Int
	 * @throws RuntimeException
	 */
	public static int getInt(String valeurString) {
		int valeur = 0;

		if (valeurString != null && !valeurString.isEmpty() && isMatch(INT_REGEX, valeurString)) {
			try {
				valeur = Integer.parseInt(valeurString);

			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new RuntimeException(e);
			}
		} else {
			return ERROR;
		}

		return valeur;
	}

	/**
	 * Convert a string to LocalDateTime.
	 * 
	 * @param dateInString
	 * @return LocalDateTime
	 * @throws RuntimeException
	 */
	public static LocalDateTime getLocalDateTime(String dateInString, String pattern) {
		if (dateInString.isEmpty())
			return null;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		dateInString = dateInString.replaceAll("T", " ");
		try {
			LocalDateTime dateTime = LocalDateTime.parse(dateInString, formatter);
			return dateTime;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static String getErrorFromViolation(
			@SuppressWarnings("rawtypes") ConstraintViolation constraintViolation) {
		return "Value '" + constraintViolation.getInvalidValue() + "' is invalid for the field '"
				+ constraintViolation.getPropertyPath() + "' : " + constraintViolation.getMessage();
	}

	public static boolean isLocalDateTime(String dateInString, String regex) {
		return isMatch(regex, dateInString);
	}

	private static Boolean isMatch(String regex, String input) {
		Pattern p = Pattern.compile(regex);
		return p.matcher(input).matches();
	}
}
