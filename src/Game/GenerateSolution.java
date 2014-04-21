package Game;

import java.util.ArrayList;

public class GenerateSolution implements Runnable {
	
	ArrayList<String> solutions;
	ArrayList<Integer> numbers;
	
	int a; int b; int c; int d; int e; int f;
	
	int target;

	public GenerateSolution(ArrayList<Integer> n, int t) {
		
		solutions = new ArrayList<String>();
		
		numbers = n;
		target = t;

	}

	public void run() {

		generateSolutions();

	}

	private void generateSolutions() {
		
		
		
	}

	public void printSolutions() {
		
		System.out.println("Here are all of the possible correct solutions: ");
		
		for(String i : solutions) {
			
			System.out.println(i);
			
		}
		
	}

}
