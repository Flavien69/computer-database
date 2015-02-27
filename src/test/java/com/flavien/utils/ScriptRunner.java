package com.flavien.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScriptRunner {

	public static final int COUNT_TOTAL_COMPUTER = 20;
	public static final int COUNT_TOTAL_COMPANY = 5;

	public static void runScript() {
		String line;
		try {
			Process p = Runtime.getRuntime().exec(
					"./src/test/resources/db/script.sh");

			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}