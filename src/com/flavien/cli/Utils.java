package com.flavien.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utils {

	private static Scanner scannerInstance = null;
	public static final int RESULT_SKIP = -1;
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

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
			try {
				String valeurString = sc.nextLine();
				if (isSkip(valeurString))
					return RESULT_SKIP;
				valeur = Integer.parseInt(valeurString);
				if (valeur < maxValue) {
					return valeur;
				} else {
					erreur = true;
					errorInput(sc,
							"Erreur! Erreur! please enter a number less than "
									+ maxValue);
				}
			} catch (Exception e) {
				erreur = true;
				errorInput(sc, "Erreur! please enter a number");
			}
		} while (erreur);
		return valeur;
	}

	public static int getIntInput() {
		boolean erreur;
		int valeur = 0;
		Scanner sc = getScannerInstance();

		do {
			erreur = false;
			try {
				String valeurString = sc.nextLine();
				if (isSkip(valeurString))
					return RESULT_SKIP;
				valeur = Integer.parseInt(valeurString);
				return valeur;
			} catch (Exception e) {
				erreur = true;
				errorInput(sc, "Erreur! please enter a number");
			}
		} while (erreur);
		return valeur;
	}

	public static LocalDateTime getDateInput() {
		boolean erreur;
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm");
		String dateInString;
		Scanner sc = getScannerInstance();

		do {
			erreur = false;
			dateInString = sc.nextLine();
			if (isSkip(dateInString))
				return null;

			try {
				LocalDateTime dateTime = LocalDateTime.parse(dateInString,
						formatter);
				return dateTime;

			} catch (Exception e) {
				erreur = true;
				errorInput(sc, "invalid date! retry :");
			}
		} while (erreur);
		return null;
	}

	private static void errorInput(Scanner sc, String msg) {
		System.out.println(msg);
	}
}
