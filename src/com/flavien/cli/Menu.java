package com.flavien.cli;
import java.sql.SQLException;

import com.flavien.dao.DbConnection;

public class Menu {
	private final String MENU_TEMPLATE= "\t%d] %s";
	private CompanyCli companyCli;
	private ComputerCli computerCli;
	
	public enum MenuEntries {
		  SHOW_COMPANY ("Visualiser l'ensemble des company."),
		  SHOW_COMPUTERS ("Visualiser l'ensemble des computers."),
		  SHOW_COMPUTERS_PAGINATION ("Visualiser les computers avec pagination."),
		  UPDATE_COMPUTERS ("Mettre à jour un computer."),
		  DELETE_COMPUTERS ("Supprimer un computer."),
		  CREATE_COMPUTERS ("Creer un computer."),
		  QUIT ("Quitter.");		  
		   
		  private String name;
		   
		  MenuEntries(String name){
		    this.name = name;
		  }
		   
		  public String toString(){
		    return name;
		  }
	}
	
	public Menu(){
		this.computerCli = new ComputerCli();
		this.companyCli = new CompanyCli();
	}
	
	public void run(){
		while(true){
			display();
		}
	}
	
	public void redirectUser(int choice){	
		MenuEntries selection = MenuEntries.values()[choice];
		
		switch (selection) {
	        case SHOW_COMPANY: companyCli.showCompany();
            	break;
	                 
	        case SHOW_COMPUTERS: computerCli.showComputers();
            	break;
            	
	        case SHOW_COMPUTERS_PAGINATION: computerCli.showComputersPage();
        		break;
            
	        case UPDATE_COMPUTERS: computerCli.updateComputer();
	        	break;
            
	        case DELETE_COMPUTERS: computerCli.deleteComputer();
            	break;
            
	        case CREATE_COMPUTERS: computerCli.createComputer();
            	break;  
            
	        case QUIT: 	System.exit(0);
	        		   	Utils.getScannerInstance().close();
						DbConnection.INSTANCE.closeConnection();
            	break;  
	
	        default: System.out.println("Error");
        		break;
		}	
	}
	
	public void display(){
		System.out.println("\n\n*********************** MENU ************************\n");
		
		for (MenuEntries entry : MenuEntries.values()){
			System.out.println(String.format(""+MENU_TEMPLATE+"",entry.ordinal(), entry.toString()));
		}
		System.out.println("\n****************************************************\n");
		
		System.out.println("Veuillez saisir votre choix (numéro):");
		redirectUser(Utils.getIntInput(MenuEntries.values().length));
	}
}
