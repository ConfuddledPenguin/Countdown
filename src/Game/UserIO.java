package Game;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An IO handler
 * 
 * This class means all the user interaction is handled in the same place,
 * meaning we can be sure that it works.
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
			
			System.out.println("Please enter a String: ");
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

			System.err.println("Please enter a number: ");
			scan.nextLine();
			return getNumber();

		}
	}
	
	/**
	 * Gets a yes no answer from the user
	 * 
	 * @return true for yes, false for no
	 */
	public boolean getYesNo(){
		
		try {
			
			String input = scan.nextLine().toUpperCase();
			
			if ( input.equals("Y") || input.equals("YES"))
				return true;
			else if ( input.equals("N") || input.equals("NO"))
				return false;
			else 
				throw new InputMismatchException();
			
			
		} catch (InputMismatchException e){
			System.out.println("Please enter Y/N");
			e.printStackTrace();
			return getYesNo();
		}
	}
	
	/**
	 * Close input when done
	 */
	public void close(){
		
		scan.close();
	}
	/**
	 * Clear the input buffer
	 */
	public void clear() {
		
		
		scan.nextLine();
		
		
	}

	/**
	 * Prints out a specified amount of lines of - to separate things
	 * 
	 * @param n The number of lines to be printed
	 */
	public void printLines(int n){
		
		for(int i = 0; i < n - 1; i++)
			System.out.println("--------------------------------------------------------------------------------");
		
		System.out.println("--------------------------------------------------------------------------------\n");
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

