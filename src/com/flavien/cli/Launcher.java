package com.flavien.cli;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.flavien.models.Computer;

public class Launcher {

	public static void main(String[] args) {	
		Menu m = new Menu();
		//LocalDateTime d = null;
		//Computer c = new Computer(d);
		m.run();
		//m.redirectUser(4);
	}

}
