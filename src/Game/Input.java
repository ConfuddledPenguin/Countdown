package Game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
	
	public String getName() {
		
		try {

			System.out.println("Please enter your name: ");
			
			Scanner scan = new Scanner(System.in);
			
			String input = scan.nextLine();
			
			return input;
			
		} catch(InputMismatchException e) {
			
			return null;
			
		}	//end try
		
	}
	
	public int getNumber() {
		
		return 0;
		
	}
	
	public int getMode() {
		
		try {

			Scanner scan = new Scanner(System.in);

			int input = scan.nextInt();

			return input;

		} catch(InputMismatchException e) {
		
			return 0;

		}
		
	}
	
	public String getWord() {
		
		return null;
		
	}

}

