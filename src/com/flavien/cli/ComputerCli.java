package com.flavien.cli;

import java.time.LocalDateTime;
import java.util.List;

import com.flavien.dao.fabric.DaoFabric;
import com.flavien.dao.instance.CompanyDao;
import com.flavien.dao.instance.ComputerDao;
import com.flavien.models.Company;
import com.flavien.models.Computer;
import com.flavien.models.Page;

public class ComputerCli {
	
	private ComputerDao computerDao;
	private CompanyDao companyDao;
	private Computer computer;
	
	public ComputerCli(){
		this.computerDao = DaoFabric.getInstance().createComputerDao();
		this.companyDao = DaoFabric.getInstance().createCompanyDao();
	}
	
	public void showComputers(){
		List<Computer> computerList = this.computerDao.getAllComputers();
		displayComputer(computerList);
	}	
	
	public void showComputersPage(){
		 String input;
		 Page page = new Page(-1);
		 do{
			 page = this.computerDao.getComputersByPage(page.getIndex()+1);
			 displayComputer(page.getComputerList());

			 System.out.println("\nVeuillez tapez entrer pour trouver les suivants ou 'exit' pour quitter\n");
			 input = Utils.getStringInput();
		}while(input == null && page.getComputerList().size() == Page.NB_ENTITY_BY_PAGE);
	}
	
	public void displayComputer(List<Computer> computerList){
		for(Computer computer: computerList){
			System.out.println(computer.toString());
		}
	}
	
	public void createComputer(){
		
		Computer computer = new Computer();
		
		System.out.println("\n***************** CREATION D'UN COMPUTER ***********************************\n");
		String name = null;
		do{
			System.out.println("Veuillez choisir un nom:(champs obligatoire)");
			name = Utils.getStringInput();
			computer.setName(name);
		}while(name == null);
			
		System.out.println("Veuillez choisir une date de introduced ("+Utils.DATE_FORMAT+" ou entrer pour passer) :");
		computer.setIntroduced(Utils.getDateInput());

		System.out.println("Veuillez choisir une date de discontinued ("+Utils.DATE_FORMAT+" ou entrer pour passer) :");
		computer.setDiscontinued(Utils.getDateInput());
	
		CompanyCli companyCli = new CompanyCli();
		companyCli.showCompany();
		
		Boolean isCompanyIdError = false;
		Company company = null;
		do{
			if(!isCompanyIdError)
				System.out.println("\nchoisir votre company (l'ID de la company ou enter pour ne pas le changer):");
			else
				System.out.println("\nERREUR: ID INVALID, choisir votre company (l'ID de la company ou enter pour ne pas le changer):");	
			
			int computerId = Utils.getIntInput();
			if (computerId != Utils.RESULT_SKIP){
				company = companyDao.getCompanyByID(computerId);
				if (company != null)
					computer.setCompany_id(company.getId());
			}
			else
				break;
			isCompanyIdError = true;
		}while( company == null);
		
		if (this.computerDao.addComputer(computer))
			System.out.println("Computer added!\n");
		else
			System.out.println("Fail to add the computer!\n");
	}
	
	public void updateComputer(){
				
		System.out.println("\n***************** UPDATE UN COMPUTER ***********************************\n");
		showComputers();
	
		computer = null;
		Boolean isComputerIdError = false;
		Boolean isCompanyIdError = false;
		
		do{
			if(!isComputerIdError)
				System.out.println("\nchoisir votre computer à modifier (l'ID du computer):");	
			else
				System.out.println("\nERREUR: ID INVALID, choisir votre computer à modifier (l'ID du computer):");	
			
			computer = computerDao.getComputerByID(Utils.getIntInput());
			isComputerIdError = true;
		}while( computer == null);
		
		System.out.println("Veuillez choisir un nom: (enter pour ne pas le changer)");
		String name = Utils.getStringInput();
		if (name != null)
			computer.setName(name);
			
		System.out.println("Veuillez choisir une date de introduced ("+Utils.DATE_FORMAT+" ou enter pour ne pas le changer) :");
		LocalDateTime introducedDate = Utils.getDateInput();
		if (introducedDate != null)
			computer.setIntroduced(introducedDate);

		System.out.println("Veuillez choisir une date de discontinued ("+Utils.DATE_FORMAT+" ou enter pour ne pas le changer) :");
		LocalDateTime discontinued = Utils.getDateInput();
		if (introducedDate != null)
			computer.setDiscontinued(discontinued);
	
		CompanyCli companyCli = new CompanyCli();
		companyCli.showCompany();
		Company company = null;
		do{
			if(!isCompanyIdError)
				System.out.println("\nchoisir votre company (l'ID de la company ou enter pour ne pas le changer):");
			else
				System.out.println("\nERREUR: ID INVALID, choisir votre company (l'ID de la company ou enter pour ne pas le changer):");	
			
			int computerId = Utils.getIntInput();
			if (computerId != Utils.RESULT_SKIP){
				company = companyDao.getCompanyByID(computerId);
				if (company != null)
					computer.setCompany_id(company.getId());
			}
			else
				break;
			isCompanyIdError = true;
		}while( company == null);
		
		if (this.computerDao.updateComputer(computer))
			System.out.println("Computer updated!\n");
		else
			System.out.println("Fail to update the computer!\n");
	}
	
	public void deleteComputer(){
		
		System.out.println("\n***************** DELETE UN COMPUTER ***********************************\n");
		showComputers();
	
		computer = null;
		Boolean isComputerIdError = false;
		
		do{
			if(!isComputerIdError)
				System.out.println("\nchoisir votre computer à supprimer (l'ID du computer):");	
			else
				System.out.println("\nERREUR: ID INVALID, choisir votre computer à supprimer (l'ID du computer):");	
			
			computer = computerDao.getComputerByID(Utils.getIntInput());
			isComputerIdError = true;
		}while( computer == null);
		
		if (this.computerDao.deleteComputer(computer))
			System.out.println("Computer deleted!\n");
		else
			System.out.println("Fail to delete the computer!\n");
	}
	
}
