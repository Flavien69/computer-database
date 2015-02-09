package com.flavien.cli;

import java.util.List;
import com.flavien.dao.instance.ComputerDao;
import com.flavien.models.Computer;

public class ComputerCli {
	
	private ComputerDao computerDao;
	
	public ComputerCli(ComputerDao computerDao){
		this.computerDao = computerDao;
	}
	
	public void showComputers(){
		List<Computer> computerList = this.computerDao.getAllComputers();
		for(Computer computer: computerList){
			System.out.println(computer.toString());
		}	
	}
	
	public void deleteComputer(){
		showComputers();
		System.out.println("\nENTER THE ID OF THE COMPUTER TO DELETE : \n");
		int computerId = Utils.getIntInput();
		if (computerDao.deleteComputerById(computerId))
			System.out.println("the computer "+computerId+" is deleted");
		else
			System.out.println("Fail to delete the computer "+computerId);
	}
	
	public void createComputer(){
		
		Computer computer = new Computer();
		
		System.out.println("\n***************** CREATION D'UN COMPUTER ***********************************\n");
		
		System.out.println("Veuillez choisir un nom:");
		computer.setName(Utils.getStringInput());
			
		System.out.println("Veuillez choisir une date de introduced (dd/MM/YYYY):");
		computer.setIntroduced(Utils.getDateInput());
		computer.setCompany_id(2);
		/*System.out.println("Veuillez choisir une date de introduced (dd/MM/YYYY):");
		computer.setDiscontinued(Utils.getDateInput());*/
		
		this.computerDao.addComputer(computer);
		System.out.println("Computer added!\n");
	}
}
