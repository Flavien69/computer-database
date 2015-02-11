package com.flavien.cli;

public class Menu {
	private static final String MENU_TEMPLATE= "\t%d] %s";
	
	public enum MenuEntries {
		  SHOW_COMPANY ("Show all the company."),
		  SHOW_COMPUTERS ("Show all the computers."),
		  SHOW_COMPUTERS_PAGINATION ("Show computers page by page."),
		  UPDATE_COMPUTERS ("Update a computer."),
		  DELETE_COMPUTERS ("Delete a computer."),
		  CREATE_COMPUTERS ("Create a computer."),
		  QUIT ("Quit.");		  
		   
		  private String name;
		   
		  MenuEntries(String name){
		    this.name = name;
		  }
		   
		  public String toString(){
		    return name;
		  }
	}
	
	/**
	 * 
	 * Main entry of the menu class.
	 * @author flavien
	 * 
	 */
	public static void run(){
		while(true){
			display();
		}
	}
	
	/**
	 * 
	 * Permit to redirect the user using the choice made by the user in the menu.
	 * @author flavien
	 * 
	 */
	public static void redirectUser(int choice){	
		MenuEntries selection = MenuEntries.values()[choice];
		
		switch (selection) {
	        case SHOW_COMPANY: CompanyCli.showCompany();
            	break;
	                 
	        case SHOW_COMPUTERS: ComputerCli.showComputers();
            	break;
            	
	        case SHOW_COMPUTERS_PAGINATION: ComputerCli.showComputersPage();
        		break;
            
	        case UPDATE_COMPUTERS: ComputerCli.updateComputer();
	        	break;
            
	        case DELETE_COMPUTERS: ComputerCli.deleteComputer();
            	break;
            
	        case CREATE_COMPUTERS: ComputerCli.createComputer();
            	break;  
            
	        case QUIT: 	System.exit(0);
	        		   	Utils.getScannerInstance().close();
            	break;  
	
	        default: System.out.println("Error");
        		break;
		}	
	}
	
	/**
	 * 
	 * Show the menu and wait the input of the user.
	 * @author flavien
	 * 
	 */
	public static void display(){
		System.out.println("\n\n*********************** MENU ************************\n");
		
		for (MenuEntries entry : MenuEntries.values()){
			System.out.println(String.format(""+MENU_TEMPLATE+"",entry.ordinal(), entry.toString()));
		}
		System.out.println("\n****************************************************\n");
		
		System.out.println("Choose a number :");
		redirectUser(Utils.getIntInput(MenuEntries.values().length));
	}
}
