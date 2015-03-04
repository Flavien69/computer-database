package com.flavien.cli.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flavien.cli.HelperCli;


/**
 * 
 * Command line interface to handle the main menu interaction.
 * 
 */
@Component
public class Menu {
	private static final String MENU_TEMPLATE = "\t%d] %s";
	
	@Autowired
	public ComputerCli computerCli;
	
	@Autowired
	public CompanyCli companyCli;

	public enum MenuEntries {
		SHOW_COMPANY("Show all the company."), DELETE_COMPANY("Delete a company."), SHOW_COMPUTERS(
				"Show all the computers."), SHOW_COMPUTERS_PAGINATION("Show computers page by page."), UPDATE_COMPUTERS(
				"Update a computer."), DELETE_COMPUTERS("Delete a computer."), CREATE_COMPUTERS(
				"Create a computer."), QUIT("Quit.");

		private String name;

		MenuEntries(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}
	}

	/**
	 * 
	 * Main entry of the menu class.
	 * 
	 */
	public void run() {
		while (true) {
			display();
		}
	}

	/**
	 * 
	 * Permit to redirect the user using the choice made by the user in the
	 * menu.
	 * 
	 */
	public void redirectUser(int choice) {
		MenuEntries selection = MenuEntries.values()[choice];

		switch (selection) {
		case SHOW_COMPANY:
			companyCli.showCompany();
			break;

		case DELETE_COMPANY:
			companyCli.deleteCompany();
			break;

		case SHOW_COMPUTERS:
			computerCli.showComputers();
			break;

		case SHOW_COMPUTERS_PAGINATION:
			computerCli.showComputersPage();
			break;

		case UPDATE_COMPUTERS:
			computerCli.updateComputer();
			break;

		case DELETE_COMPUTERS:
			computerCli.deleteComputer();
			break;

		case CREATE_COMPUTERS:
			computerCli.createComputer();
			break;

		case QUIT:
			System.exit(0);
			HelperCli.getScannerInstance().close();
			break;

		default:
			System.out.println("Error");
			break;
		}
	}

	/**
	 * 
	 * Show the menu and wait the input of the user.
	 * 
	 */
	public void display() {
		System.out.println("\n\n*********************** MENU ************************\n");

		for (MenuEntries entry : MenuEntries.values()) {
			System.out.println(String.format("" + MENU_TEMPLATE + "", entry.ordinal(), entry.toString()));
		}
		System.out.println("\n****************************************************\n");

		System.out.println("Choose a number :");
		redirectUser(HelperCli.getIntInput(MenuEntries.values().length));
	}
}
