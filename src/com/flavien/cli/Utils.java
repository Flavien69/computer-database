package com.flavien.cli;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
	
	private static Scanner scannerInstance = null;		
		
	public static synchronized Scanner getScannerInstance(){
		if (scannerInstance == null)
			scannerInstance = new Scanner(System.in);
		
		return scannerInstance;
	}
	
	public static String getStringInput(){
		Scanner sc = getScannerInstance();
	    String input  = sc.next();
		return input;
	}
	
	public static int getIntInput(int maxValue){
		boolean erreur;
		int valeur = 0;
		Scanner sc = getScannerInstance();

		do {
		    erreur = false;
		    try {
			    valeur = sc.nextInt();
				if (valeur < maxValue ){
					return valeur;
				}
				else{
					erreur = true;
			        errorInput(sc, "Erreur! veillez rentrer un chiffre inférieur à "+maxValue);
				}
		    }catch (InputMismatchException e) {
		       erreur = true;
		       errorInput(sc, "Erreur! veillez rentrer un chiffre");
		    }
		} while (erreur);
		return valeur;
	}
	
	public static int getIntInput(){
		boolean erreur;
		int valeur = 0;
		Scanner sc = getScannerInstance();

		do {
		    erreur = false;
		    try {
			    valeur = sc.nextInt();
				return valeur;
		    }catch (InputMismatchException e) {
		       erreur = true;
		       errorInput(sc, "Erreur! veillez rentrer un chiffre");
		    }
		} while (erreur);
		return valeur;
	}
	
	public static LocalDateTime getDateInput(){
		boolean erreur;
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString;
		Scanner sc = getScannerInstance();

		do {
		    erreur = false;
		 
			try {
				dateInString = sc.nextLine();
				Date date = (Date) formatter.parse(dateInString);
				long time = date.getTime();
				return LocalDateTime.of(date.getYear(), date.getMonth(), date.getDay(), date.getMonth(), date.getMinutes(), date.getSeconds());
				
			} catch (ParseException e) {
				erreur = true;
				errorInput(sc,"invalid date! retry :");
			}
		} while (erreur);
		return null;
	}
	
	private static void errorInput(Scanner sc, String msg){
		sc.nextLine();
		System.out.println(msg);
	}
}
