package com.flavien.cli.component;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flavien.cli.HelperCli;
import com.flavien.dto.ComputerDTO;
import com.flavien.dto.mapper.ComputerMapperDTO;
import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.models.Page;
import com.flavien.service.CompanyService;
import com.flavien.service.ComputerService;

/**
 * 
 * Command line interface to handle company interaction.
 * 
 */
@Component
public class ComputerCli {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private CompanyCli companyCli;
	
	@Autowired
	private ComputerMapperDTO computerMapperDTO;
	
	public ComputerCli(){}
	
	/**
	 * 
	 * Show a list of computers
	 * 
	 */
	public void showComputers() {
		List<Computer> computerList = computerService.getAll();
		displayComputer(computerMapperDTO.listToDto(computerList));
	}

	/**
	 * 
	 * Show a list of computer page by page using a Page object
	 * 
	 */
	public void showComputersPage() {
		String input;
		Page page = new Page(-1);
		do {
			page.setIndex(page.getIndex()+1);
			page = computerService.getByPage(page);
			displayComputer(computerMapperDTO.listToDto(page.getComputerList()));

			System.out.println("\npage " + page.getIndex() + "/" + page.getNbTotalPage());
			System.out.println("\n'enter' to search the next or 'exit' to return in the menu\n");
			input = HelperCli.getStringInput();
		} while (input == null && page.getComputerList().size() == Page.DEFAULT_NB_ENTITY_BY_PAGE);
	}

	/**
	 * 
	 * Display a list of computer
	 * 
	 */
	public void displayComputer(List<ComputerDTO> computerList) {
		for (ComputerDTO computerDTO : computerList) {
			System.out.println(computerDTO.toString());
		}
	}

	/**
	 * 
	 * Create a computer using the cli interface
	 * 
	 */
	public void createComputer() {

		Computer computer = new Computer.Builder().build();

		System.out.println("\n***************** CREATE A COMPUTER ***********************************\n");
		String name = null;
		do {
			System.out.println("choose a name (field needed)");
			name = HelperCli.getStringInput();
			computer.setName(name);
		} while (name == null);

		System.out.println("Vchoose a date of introduced (" + HelperCli.DATE_FORMAT + " or 'enter' to skip) :");
		computer.setIntroduced(HelperCli.getDateInput());

		System.out.println("choose a date of discontinued (" + HelperCli.DATE_FORMAT + " or 'enter' to skip) :");
		computer.setDiscontinued(HelperCli.getDateInput());

		companyCli.showCompany();

		Boolean isCompanyIdError = false;
		Company company = null;
		do {
			if (!isCompanyIdError)
				System.out.println("\nchoose the company (ID of the company or 'enter' to skip):");
			else
				System.out.println("\nERREUR: choose the company (ID of the company or 'enter' to skip):");

			int computerId = HelperCli.getIntInput(HelperCli.NO_MAX_VALUE);
			if (computerId != HelperCli.RESULT_SKIP) {
				company = companyService.getByID(computerId);
				if (company != null)
					computer.setCompany(company);
			} else
				break;
			isCompanyIdError = true;
		} while (company == null);
		
		computerService.add(computer);
	}

	/**
	 * 
	 * Update a computer using the cli interface
	 * 
	 */
	public void updateComputer() {

		System.out.println("\n***************** UPDATE A COMPUTER ***********************************\n");
		showComputers();

		Computer computer = null;
		Boolean isComputerIdError = false;
		Boolean isCompanyIdError = false;

		do {
			if (!isComputerIdError)
				System.out.println("\nchoose the computer to update (ID of the computer):");
			else
				System.out.println("\nERREUR: choose the computer to update (ID of the computer):");

			computer = computerService.getByID(HelperCli.getIntInput(HelperCli.NO_MAX_VALUE));
			isComputerIdError = true;
		} while (computer == null);

		System.out.println("Choose a name or 'enter' to skip: ");
		String name = HelperCli.getStringInput();
		if (name != null)
			computer.setName(name);

		System.out.println("Choose a date of introduced (" + HelperCli.DATE_FORMAT + " or 'enter' to skip) :");
		LocalDateTime introducedDate = HelperCli.getDateInput();
		if (introducedDate != null)
			computer.setIntroduced(introducedDate);

		System.out.println("Choose a date of discontinued (" + HelperCli.DATE_FORMAT + " or 'enter' to skip) :");
		LocalDateTime discontinued = HelperCli.getDateInput();
		if (introducedDate != null)
			computer.setDiscontinued(discontinued);

		companyCli.showCompany();
		Company company = null;
		do {
			if (!isCompanyIdError)
				System.out.println("\nchoose your company (ID of the company or 'enter' to skip):");
			else
				System.out.println("\nERREUR: choose your company (ID of the company or 'enter' to skip):");

			int computerId = HelperCli.getIntInput(HelperCli.NO_MAX_VALUE);
			if (computerId != HelperCli.RESULT_SKIP) {
				company = companyService.getByID(computerId);
				if (company != null)
					computer.setCompany(company);
			} else
				break;
			isCompanyIdError = true;
		} while (company == null);
		
		computerService.update(computer);
	}

	/**
	 * 
	 * Delete a computer using the cli interface
	 * 
	 */
	public void deleteComputer() {

		System.out.println("\n***************** DELETE A COMPUTER ***********************************\n");
		showComputers();

		System.out.println("\nchoose a computer to delete (ID of the computer):");
		int id = HelperCli.getIntInput(HelperCli.NO_MAX_VALUE);
		while (id == HelperCli.RESULT_SKIP) {

			System.out.println("\nERREUR: choose a computer to delete (ID of the computer):");
		}

		computerService.deleteById(id);
		System.out.println("Computer deleted!\n");
	}

}
