package com.flavien.cli;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;



/**
 * 
 * Command line interface to Launch the app.
 * 
 */
@Component
public class Launcher {

	public static void main(String[] args) {	
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContextCli.xml");
		Menu.run();
	}

}
