package Game;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An input handler
 * 
 */
public class UserIO {
	
	private Scanner scan;
	
	public UserIO(){
		
		scan = new Scanner(System.in);
	}
	
	/**
	 * Takes in a string from the user
	 * @return the user entered string
	 */
	public String getString() {
		
		try {
			
			String input = scan.nextLine();
			
			return input;
			
		} catch(InputMismatchException e) {
			
			System.out.println("Please enter a String ");
			return getString();
			
		}	//end try
		
	}

	
	/**
	 * Takes in a number from the user
	 * @return the user entered number
	 */
	public int getNumber() {
		
		try {

			int input = scan.nextInt();
			scan.nextLine();

			return input;

		} catch(InputMismatchException e) {
		
			System.err.println("Please enter a number : ");
			return getNumber();

		}
	}

	/**
	 * Prints out a specified amount of lines of - to separate things
	 * 
	 * @param n The number of lines to be printed
	 */
	public void printLines(int n){
		
		for(int i = 0; i < n - 1; i++)
			System.out.println("----------------------------------------------------");
		
		System.out.println("----------------------------------------------------\n");
	}
	
	/**
	 * Prints out a specified amount of short lines of - to separate things
	 * 
	 * @param n The number of lines to be printed
	 */
	public void printShortLines(int n){
		
		for(int i = 0; i < n - 1; i++)
			System.out.println("---------------------------");
		
		System.out.println("---------------------------\n");
	}

}

