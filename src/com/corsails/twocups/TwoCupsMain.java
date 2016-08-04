package com.corsails.twocups;

import java.util.Scanner;

public class TwoCupsMain {
	public static void main(String[] args) throws NumberFormatException {
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
					// Delimit by one or more whitespace characters, then check if there are at least 3 arguments; ignore additional ones
					String[] inputArr = input.split(" +");
					boolean validInputs = true;
					
					if (inputArr.length >= 3) {
						// Validate that the arguments can be parsed into integers, then parse them
						for (int i = 0; validInputs && i < 3; i++) {
							String inputEntry = inputArr[i];
							if (!isInteger(inputEntry)) {
								System.out.println("An error occurred: Cannot parse string '" + inputEntry + "'");
								validInputs = false;
							}
						}
						
						// If the invalid input flag doesn't trigger, the arguments are valid and we can try to measure the target water amount!
						if (validInputs) {
							int cup1 = Integer.parseInt(inputArr[0]);
							int cup2 = Integer.parseInt(inputArr[1]);
							int target = Integer.parseInt(inputArr[2]);
							
							boolean result = measureWater(cup1, cup2, target);
							System.out.println(((result) ? "Yes!" : "No!") + " You " + ((result) ? "can" : "cannot") + " measure " + target + " ounces of water with a " + cup1 + "-ounce and a " + cup2 + "-ounce cup!");
						}				
					}
				}
			}
			
			if (!exitFlag)
				System.out.print("> ");
		}
		
	}
	
	public static boolean isInteger(String string) {
	    try {
	        Integer.valueOf(string);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	public static boolean measureWater(int cup1, int cup2, int target) {
		// Check if we can hit the target with just one cup
		if ((target >= cup1 && target % cup1 == 0) || (target >= cup2 && target % cup2 == 0))
			return true;
		
		// Instance variable
		int total = 0;
		
		// Get the difference between the larger cup and the smaller cup and add to the total measurement. (or just measure with one of the cups if they are the same size)
		// Looking at this more abstractly, to hit the targeted measurement, you fill the larger cup until it's full, and then pour that cup into the smaller cup until you fill up the smaller cup.
		// Whatever amount of water you have left over in the larger cup, you add to the total and attempt to hit the target measurement that way; you're guaranteed to either hit or exceed the target.
		while (total < target)
			total += (cup1 != cup2) ? Math.abs(cup1 - cup2) : cup1;
			
		return (total == target);
	}
}