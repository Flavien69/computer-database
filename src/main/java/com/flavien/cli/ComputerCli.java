package com.flavien.cli;

import java.time.LocalDateTime;
import java.util.List;

import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.models.Page;
import com.flavien.service.impl.CompanyServiceImpl;
import com.flavien.service.impl.ComputerServiceImpl;
import com.flavien.service.impl.ServiceManager;

public class ComputerCli {
	
	private static CompanyServiceImpl companyService = ServiceManager.INSTANCE.getCompanyServiceImpl();
	private static ComputerServiceImpl computerService = ServiceManager.INSTANCE.getComputerServiceImpl();	

	/**
	 * 
	 * Show a list of computers 
	 * @author flavien
	 * 
	 */
	public static void showComputers() {
		List<Computer> computerList = computerService.getAll();
		displayComputer(computerList);
	}

	/**
	 * 
	 * Show a list of computer page by page using a Page object
	 * @author flavien
	 * 
	 */
	public static void showComputersPage() {
		String input;
		Page page = new Page(-1);
		do {
			page = computerService.getByPage(page.getIndex() + 1);
			displayComputer(page.getComputerList());

			System.out.println("\npage "+page.getIndex()+"/"+page.getNbTotalPage());
			System.out.println("\n'enter' to search the next or 'exit' to return in the menu\n");
			input = Utils.getStringInput();
		} while (input == null
				&& page.getComputerList().size() == Page.NB_ENTITY_BY_PAGE);
	}

	/**
	 * 
	 * Display a list of computer 
	 * @author flavien
	 * 
	 */
	public static void displayComputer(List<Computer> computerList) {
		for (Computer computer : computerList) {
			System.out.println(computer.toString());
		}
	}

	/**
	 * 
	 * Create a computer using the cli interface 
	 * @author flavien
	 * 
	 */
	public static void createComputer() {

		Computer computer = new Computer();

		System.out
				.println("\n***************** CREATE A COMPUTER ***********************************\n");
		String name = null;
		do {
			System.out.println("choose a name (field needed)");
			name = Utils.getStringInput();
			computer.setName(name);
		} while (name == null);

		System.out.println("Vchoose a date of introduced ("
				+ Utils.DATE_FORMAT + " or 'enter' to skip) :");
		computer.setIntroduced(Utils.getDateInput());

		System.out.println("choose a date of discontinued ("
				+ Utils.DATE_FORMAT + " or 'enter' to skip) :");
		computer.setDiscontinued(Utils.getDateInput());

		CompanyCli.showCompany();

		Boolean isCompanyIdError = false;
		Company company = null;
		do {
			if (!isCompanyIdError)
				System.out
						.println("\nchoose the company (ID of the company or 'enter' to skip):");
			else
				System.out
						.println("\nERREUR: choose the company (ID of the company or 'enter' to skip):");

			int computerId = Utils.getIntInput(Utils.NO_MAX_VALUE);
			if (computerId != Utils.RESULT_SKIP) {
				company = companyService.getByID(computerId);
				if (company != null)
					computer.setCompany(company);
			} else
				break;
			isCompanyIdError = true;
		} while (company == null);

		if (computerService.add(computer))
			System.out.println("Computer added!\n");
		else
			System.out.println("Fail to add the computer!\n");
	}

	/**
	 * 
	 * Update a computer using the cli interface 
	 * @author flavien
	 * 
	 */
	public static void updateComputer() {

		System.out.println("\n***************** UPDATE A COMPUTER ***********************************\n");
		showComputers();

		Computer computer = null;
		Boolean isComputerIdError = false;
		Boolean isCompanyIdError = false;

		do {
			if (!isComputerIdError)
				System.out
						.println("\nchoose the computer to update (ID of the computer):");
			else
				System.out
						.println("\nERREUR: choose the computer to update (ID of the computer):");

			computer = computerService.getByID(Utils.getIntInput(Utils.NO_MAX_VALUE));
			isComputerIdError = true;
		} while (computer == null);

		System.out
				.println("Choose a name or 'enter' to skip: ");
		String name = Utils.getStringInput();
		if (name != null)
			computer.setName(name);

		System.out.println("Choose a date of introduced ("
				+ Utils.DATE_FORMAT + " or 'enter' to skip) :");
		LocalDateTime introducedDate = Utils.getDateInput();
		if (introducedDate != null)
			computer.setIntroduced(introducedDate);

		System.out.println("Choose a date of discontinued ("
				+ Utils.DATE_FORMAT + " or 'enter' to skip) :");
		LocalDateTime discontinued = Utils.getDateInput();
		if (introducedDate != null)
			computer.setDiscontinued(discontinued);

		CompanyCli.showCompany();
		Company company = null;
		do {
			if (!isCompanyIdError)
				System.out
						.println("\nchoose your company (ID of the company or 'enter' to skip):");
			else
				System.out
						.println("\nERREUR: choose your company (ID of the company or 'enter' to skip):");

			int computerId = Utils.getIntInput(Utils.NO_MAX_VALUE);
			if (computerId != Utils.RESULT_SKIP) {
				company = companyService.getByID(computerId);
				if (company != null)
					computer.setCompany(company);
			} else
				break;
			isCompanyIdError = true;
		} while (company == null);

		if (computerService.update(computer))
			System.out.println("Computer updated!\n");
		else
			System.out.println("Fail to update the computer!\n");
	}

	/**
	 * 
	 * Delete a computer using the cli interface 
	 * @author flavien
	 * 
	 */
	public static void deleteComputer() {

		System.out
				.println("\n***************** DELETE A COMPUTER ***********************************\n");
		showComputers();

		Boolean isComputerIdError = false;

		do {
			if (isComputerIdError)
				System.out
						.println("\nchoose a computer to delete (ID of the computer):");
			else
				System.out
						.println("\nERREUR: choose a computer to delete (ID of the computer):");

			isComputerIdError = computerService.deleteById(Utils.getIntInput(Utils.NO_MAX_VALUE));
		} while (!isComputerIdError);
		System.out.println("Computer deleted!\n");
	}

}
