package com.flavien.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Utils {
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
				e.printStackTrace();
				System.exit(1);
			}
		} else {
			return ERROR;
		}

		return valeur;
	}

	public static LocalDateTime getLocalDateTime(String dateInString) {
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern(DATE_FORMAT);
			if(dateInString!= null && !dateInString.isEmpty() && isMatch(DATE_REGEX, dateInString)){
				try {
					dateInString = dateInString.replaceAll("T", " ");
					LocalDateTime dateTime = LocalDateTime.parse(dateInString,
							formatter);
					return dateTime;
	
				} catch (Exception e) {
					throw new RuntimeException();
				}
			} 
		return null;
	}
	
	private static Boolean isMatch(String regex, String input) {
		Pattern p = Pattern.compile(regex);
		return p.matcher(input).matches();
	}
}
