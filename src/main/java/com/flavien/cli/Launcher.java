package com.flavien.cli;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * 
 * Command line interface to Launch the app.
 * 
 */


public class Launcher {

	public static void main(String[] args) {	

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/application-context.xml");		
		Menu m = ctx.getBean(Menu.class);
		m.run();
		ctx.close();
	}

}
