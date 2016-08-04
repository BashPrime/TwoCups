package com.corsails.twocups;

import java.util.ArrayList;
import java.util.HashMap;
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
		scanner.close();
		System.out.println("Exiting...");
		
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
		
		if (cup1 == cup2)
			return false;
		
		// Instance variables
		HashMap<String, Cup> cups = new HashMap<String, Cup>();
		cups.put("larger", new Cup(0, (cup1 > cup2) ? cup1 : cup2));
		cups.put("smaller", new Cup(0, (cup1 < cup2) ? cup1 : cup2));
		int counter = 0;
		boolean targetFlag = false;
		
		while (!targetFlag && counter < 500) {
			// Fill large cup with water
			cups.get("larger").fill();
			
			if (measureCapacities(cups, target)) {
				targetFlag = true;
				break;
			}
				
			// Transfer large cup to small cup
			cups.get("smaller").transferFromOtherCup(cups.get("larger"));

			if (measureCapacities(cups, target)) {
				targetFlag = true;
				break;
			}
			
			// Empty small cup
			cups.get("smaller").empty();
			
			if (measureCapacities(cups, target)) {
				targetFlag = true;
				break;
			}
			
			// Transfer large cup to small cup
			cups.get("smaller").transferFromOtherCup(cups.get("larger"));
			
			if (measureCapacities(cups, target)) {
				targetFlag = true;
				break;
			}			
			
			counter++;
		}
		
		if (targetFlag)
			return true;
		return false;
	}
	
	public static boolean measureCapacities(HashMap<String, Cup> cups, int target) {
		if (cups.get("larger").getAmount() == target || cups.get("smaller").getAmount() == target)
			return true;
		return false;
	}
}