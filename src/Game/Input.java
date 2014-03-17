package Game;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An input handler
 * 
 */
public class Input {
	
	/**
	 * Takes in a string from the user
	 * @return the user entered string
	 */
	public String getWord() {
		
		try {
			
			Scanner scan = new Scanner(System.in);
			
			String input = scan.nextLine();
			
			return input;
			
		} catch(InputMismatchException e) {
			
			return null;
			
		}	//end try
		
	}
	
	/* Takes in the users solution to the number round,
	 * tokenises it and then returns it as a String.
	 */
	/**
	 * 
	 * @return
	 */
	public String getSolution() {
		
		return null;
		
	}
	
	/**
	 * Takes in a number from the user
	 * @return the user entered number
	 */
	public int getNumber() {
		
		try {

			Scanner scan = new Scanner(System.in);

			int input = scan.nextInt();

			return input;

		} catch(InputMismatchException e) {
		
			return 0;

		}
		
	}

}

