package com.corsails.twocups;

import java.util.Scanner;

public class TwoCupsMain {
	public static void main(String[] args) {
		// Set up CLI-style input
		boolean exitFlag = false;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter three arguments: two cup sizes, and a target amount, all integers and separated by whitespace.");
		System.out.print("> ");
		
		// Main scanner/user input loop
		while (!exitFlag && scanner.hasNextLine()) {
			String input = scanner.nextLine();
			if (input.length() > 0) {
				// Check for exit condition
				if (input.equals("exit")) {
					exitFlag = true;
					System.out.println("Exiting...");
				}
				// Validate the input stream and begin processing it
				else {
					System.out.println("Do stuff here");
				}
			}
			
			if (!exitFlag)
				System.out.print("> ");
		}
		
	}
}
