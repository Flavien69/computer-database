package com.flavien.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Utils {
	private final static Logger logger = LoggerFactory.getLogger(Utils.class);
	public static final String INT_REGEX = "^[0-9]*$";
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String DATE_REGEX = "^(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])[\\s||T][0-9][0-9]:[0-9][0-9]$";
	public static final int ERROR = 0;

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

	public static LocalDateTime getLocalDateTime(String dateInString) {
		if(dateInString.isEmpty())
			return null;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		dateInString = dateInString.replaceAll("T", " ");
		try{
			LocalDateTime dateTime = LocalDateTime.parse(dateInString, formatter);
			return dateTime;
		}
		catch(Exception e){
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static String getErrorFromViolation(ConstraintViolation constraintViolation){
		return "Value '" + constraintViolation.getInvalidValue() + "' is invalid for the field '"
				+ constraintViolation.getPropertyPath() + "' : " + constraintViolation.getMessage();
	}
	
	public static boolean isLocalDateTime(String dateInString) {
		return isMatch(DATE_REGEX, dateInString);
	}

	private static Boolean isMatch(String regex, String input) {
		Pattern p = Pattern.compile(regex);
		return p.matcher(input).matches();
	}
}
