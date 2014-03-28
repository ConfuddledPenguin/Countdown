package Game;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An input handler
 * 
 */
public class IO {
	
	private Scanner scan;
	
	public IO(){
		
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
		
			System.out.println("please enter a number : ");
			return getNumber();

		}
		
	}
	

	/**
	 * Prints out a line of - to seperate things
	 */
	public void printLine(){
		
		System.out.println("----------------------------------------------------\n");
	}

}

