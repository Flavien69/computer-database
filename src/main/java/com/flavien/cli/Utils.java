package com.flavien.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 
 * Utils class to help CLI classes.
 * 
 */
public class Utils {

	private static Scanner scannerInstance = null;
	public static final int RESULT_SKIP = -1;
	public static final int NO_MAX_VALUE = -2;
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String DATE_REGEX = "^(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])\\s[0-9][0-9]:[0-9][0-9]$";
	public static final String INT_REGEX = "^[0-9]*$";

	public static synchronized Scanner getScannerInstance() {
		if (scannerInstance == null)
			scannerInstance = new Scanner(System.in);

		return scannerInstance;
	}

	public static Boolean isSkip(String input) {
		if (input.equals(""))
			return true;

		return false;
	}

	public static String getStringInput() {
		Scanner sc = getScannerInstance();
		String input = sc.nextLine();
		if (isSkip(input))
			return null;
		return input;
	}

	public static int getIntInput(int maxValue) {
		boolean erreur;
		int valeur = 0;
		Scanner sc = getScannerInstance();

		do {
			erreur = false;
			String valeurString = sc.nextLine();
			if (isMatch(INT_REGEX, valeurString)) {
				try {
					if (isSkip(valeurString))
						return RESULT_SKIP;
					valeur = Integer.parseInt(valeurString);
					if (maxValue == NO_MAX_VALUE)
						return valeur;

					if (valeur < maxValue) {
					} else {
						erreur = true;
						errorInput(sc, "Erreur! please enter a number less than " + maxValue);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			} else {
				erreur = true;
				errorInput(sc, "Erreur! please enter a number");
			}

		} while (erreur);
		return valeur;
	}

	public static LocalDateTime getDateInput() {
		boolean erreur;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String dateInString;
		Scanner sc = getScannerInstance();

		do {
			erreur = false;
			dateInString = sc.nextLine();
			if (isSkip(dateInString))
				return null;

			if (isMatch(DATE_REGEX, dateInString)) {
				try {
					LocalDateTime dateTime = LocalDateTime.parse(dateInString, formatter);
					return dateTime;

				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			} else {
				erreur = true;
				errorInput(sc, "invalid date format! retry :");
			}

		} while (erreur);
		return null;
	}

	private static Boolean isMatch(String regex, String input) {
		Pattern p = Pattern.compile(regex);
		return p.matcher(input).matches();
	}

	private static void errorInput(Scanner sc, String msg) {
		System.out.println(msg);
	}
}
