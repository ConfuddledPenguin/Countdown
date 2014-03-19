package Game;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An input handler
 * 
 */
public class Input {
	
	private Scanner scan;
	
	public Input(){
		
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
	


}

