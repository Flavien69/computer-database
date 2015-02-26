package com.flavien.cli;

import org.springframework.stereotype.Component;


/**
 * 
 * Command line interface to Launch the app.
 * 
 */
@Component
public class Launcher {

	public static void main(String[] args) {	;
		Menu.run();
	}

}
