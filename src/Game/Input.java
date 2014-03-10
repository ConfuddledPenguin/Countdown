package Game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
	
	/* Takes in a String from the user.
	 * Can be user for getting the players name
	 * or the answer for the word round.
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
	public String getSolution() {
		
		return null;
		
	}
	
	/*Takes in an integer from the user
	 * 
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

