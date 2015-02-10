package com.flavien.cli;

public enum Menu {
	INSTANCE;
	private Menu(){};
	private final String MENU_TEMPLATE= "\t%d] %s";
	
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
	
	public void run(){
		while(true){
			display();
		}
	}
	
	public void redirectUser(int choice){	
		MenuEntries selection = MenuEntries.values()[choice];
		
		switch (selection) {
	        case SHOW_COMPANY: CompanyCli.INSTANCE.showCompany();
            	break;
	                 
	        case SHOW_COMPUTERS: ComputerCli.INSTANCE.showComputers();
            	break;
            	
	        case SHOW_COMPUTERS_PAGINATION: ComputerCli.INSTANCE.showComputersPage();
        		break;
            
	        case UPDATE_COMPUTERS: ComputerCli.INSTANCE.updateComputer();
	        	break;
            
	        case DELETE_COMPUTERS: ComputerCli.INSTANCE.deleteComputer();
            	break;
            
	        case CREATE_COMPUTERS: ComputerCli.INSTANCE.createComputer();
            	break;  
            
	        case QUIT: 	System.exit(0);
	        		   	Utils.getScannerInstance().close();
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
		
		System.out.println("Choose a number :");
		redirectUser(Utils.getIntInput(MenuEntries.values().length));
	}
}
