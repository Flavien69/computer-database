package com.flavien.utils;

import java.util.regex.Pattern;

public class Utils {
	public static final String INT_REGEX = "^[0-9]*$";
	public static final int ERROR = -1;

	public static int getInt(String valeurString) {
		int valeur = 0;

		if (isMatch(INT_REGEX, valeurString)) {
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

	private static Boolean isMatch(String regex, String input) {
		Pattern p = Pattern.compile(regex);
		return p.matcher(input).matches();
	}
}
