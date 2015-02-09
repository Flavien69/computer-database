package com.flavien.cli;
import com.flavien.dao.fabric.DaoFabric;

public class Menu {
	private final String MENU_TEMPLATE= "\t%d] %s";
	private CompanyCli companyCli;
	private ComputerCli computerCli;
	
	public enum MenuEntries {
		  SHOW_COMPANY ("Visualiser l'ensemble des company."),
		  SHOW_COMPUTERS ("Visualiser l'ensemble des computers."),
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
		this.computerCli = new ComputerCli(DaoFabric.getInstance().createComputerDao());
		this.companyCli = new CompanyCli(DaoFabric.getInstance().createCompanyDao());
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
            
	        case UPDATE_COMPUTERS: System.out.println(MenuEntries.UPDATE_COMPUTERS.toString());
	        	break;
            
	        case DELETE_COMPUTERS: computerCli.deleteComputer();
            	break;
            
	        case CREATE_COMPUTERS: computerCli.createComputer();
            	break;  
            
	        case QUIT: System.exit(0);
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
