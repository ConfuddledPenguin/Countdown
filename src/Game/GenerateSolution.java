package Game;

import java.util.ArrayList;

public class GenerateSolution implements Runnable {
	
	ArrayList<String> solutions;
	ArrayList<Integer> numbers;
	int target;

	public GenerateSolution(ArrayList<Integer> n, int t) {
		
		numbers = n;
		target = t;

	}

	public void run() {

		generateSolutions();

	}
	
	private void generateSolutions() {
		
		System.out.println("Adding Solutions");
		
		solutions.add("Hello World");
		
	}

	public void printSolutions() {
		
		System.out.println("Here are all of the possible correct solutions: ");
		
		for(String i : solutions) {
			
			System.out.println(i);
			
		}
		
	}

}
