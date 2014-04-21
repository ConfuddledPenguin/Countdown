package Game;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Generate a solution to the number round
 * 
 * It does this by generating all possible
 * combinations of the number with +-/*, then check if
 * any of them are right 
 */
public class GenerateSolution implements Runnable {

	ArrayList<String> solutions;
	int[] numbers;
	int target;

	/**
	 * 
	 */
	private class mathString{

		String string;
		double value;

		public String toString(){
			return string;
		}
	}

	/**
	 * Constructor for GenerateSolution
	 * @param list of numbers
	 * @param target number
	 */
	public GenerateSolution(ArrayList<Integer> n, int t) {

		solutions = new ArrayList<String>();

		numbers = new int[6];

		int counter = 0;
		for(int i : n) {

			numbers[counter] = i;
			counter++;

		}

		target = t;

	}

	/**
	 * Method from Runnable interface
	 */
	public void run() {

		try {
			generateSolutions();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Generates all possible permutations of the 6 numbers and calculates
	 * all possible valid solutions
	 * @throws Exception
	 */
	private void generateSolutions() throws Exception {

		//first case

		mathString[] msArray = new mathString[6];

		int count1 = 0;

		for(int n : numbers) {

			mathString ms = new mathString();

			ms.value = n;
			ms.string = String.valueOf(n);

			msArray[count1] = ms;

			count1++;

		}

		for(mathString m : calc(msArray)) {

			if(m.value == target){
				solutions.add(m.string);
				//System.out.println("Stuff has been added");
			}

		}

		//rest of the cases

		while ((numbers = nextPermutation(numbers)) != null ) {

			mathString[] msArray2 = new mathString[6];

			int count2 = 0;

			for(int n : numbers) {

				mathString ms = new mathString();

				ms.value = n;
				ms.string = String.valueOf(n);

				msArray2[count2] = ms;

				count2++;

			}

			for(mathString m : calc(msArray2)) {

				if(m.value == target){
					solutions.add(m.string);
					//System.out.println("added : " + m.string + " : " + m.value + " : " + target);
				}

			}

		}

	}

	/**
	 * modifies c to next permutation or returns null if such permutation does not exist
	 * 
	 * @param c
	 * @return
	 */
	private int[] nextPermutation( int[] c ) {
		// 1. finds the largest k, that c[k] < c[k+1]
		int first = getFirst( c );
		if ( first == -1 ) return null; // no greater permutation
		// 2. find last index toSwap, that c[k] < c[toSwap]
		int toSwap = c.length - 1;
		while ( c[ first ] >= c[ toSwap ] )
			--toSwap;
		// 3. swap elements with indexes first and last
		swap( c, first++, toSwap );
		// 4. reverse sequence from k+1 to n (inclusive) 
		toSwap = c.length - 1;
		while ( first < toSwap )
			swap( c, first++, toSwap-- );
		return c;
	}

	/**
	 * finds the largest k, that c[k] < c[k+1]
	 * if no such k exists (there is not greater permutation), return -1
	 * 
	 * @param c
	 * @return
	 */
	private int getFirst(int[] c) {
		for (int i = c.length - 2; i >= 0; --i)
			if (c[i] < c[i + 1])
				return i;
		return -1;
	}

	/**
	 * swaps two elements (with indexes i and j) in array 
	 * 
	 * @param c
	 * @param i
	 * @param j
	 */
	private void swap(int[] c, int i, int j ) {
		int tmp = c[ i ];
		c[ i ] = c[ j ];
		c[ j ] = tmp;
	}
	
	/**
	 * 
	 * 
	 * @param mathStrings
	 * @return
	 * @throws Exception
	 */
	private mathString[] calc (mathString[] mathStrings) throws Exception{

		mathString calculated[] = new mathString[ (int) Math.pow(4, mathStrings.length -1)];

		int i = 0;
		mathString ms;

		if (mathStrings.length == 6){

			//get all possible combinations for first 3 numbers
			mathString msa[] = new mathString[3];
			msa[0] = mathStrings[0];
			msa[1] = mathStrings[1];
			msa[2] = mathStrings[2];
			mathString[] ms1 = calc (msa);

			//get all possible combinations for last 3 numbers
			msa[0] = mathStrings[3];
			msa[1] = mathStrings[4];
			msa[2] = mathStrings[5];
			mathString[] ms2 = calc (msa);

			//merge them to give 1024 possible values
			for (int index1 = 0; index1 < ms1.length; index1++){

				for (int index2 = 0; index2 < ms2.length; index2++){

					ms = new mathString();
					ms.string = "(" + ms1[index1] + ")+(" + ms2[index2] + ")";
					ms.value = ms1[index1].value + ms2[index2].value;

					calculated[i++] = ms;

					ms = new mathString();
					ms.string = "(" + ms1[index1] + ")-(" + ms2[index2] + ")";
					ms.value = ms1[index1].value - ms2[index2].value;

					calculated[i++] = ms;

					ms = new mathString();
					ms.string = "(" + ms1[index1] + ")/(" + ms2[index2] + ")";
					ms.value = ms1[index1].value / ms2[index2].value;

					calculated[i++] = ms;

					ms = new mathString();
					ms.string = "(" + ms1[index1] + ")*(" + ms2[index2] + ")";
					ms.value = ms1[index1].value * ms2[index2].value;

					calculated[i++] = ms;
				}
			}
		}

		else if (mathStrings.length == 3){

			mathString mswithtwo[] = new mathString[2];
			mswithtwo[0] = mathStrings[0];
			mswithtwo[1] = mathStrings[1];

			mathString[] calced = calc(mswithtwo);

			for (int index = 0; index < 4; index++){

				ms = new mathString();
				ms.string = "(" + calced[index] + "+" + mathStrings[2] + ")";
				ms.value = calced[index].value + mathStrings[2].value;

				calculated[i++] = ms;

				ms = new mathString();
				ms.string = "(" + calced[index] + "-" + mathStrings[2] + ")";
				ms.value = calced[index].value - mathStrings[2].value;

				calculated[i++] = ms;

				ms = new mathString();
				ms.string = "(" + calced[index] + "/" + mathStrings[2] + ")";
				ms.value = calced[index].value / mathStrings[2].value;

				calculated[i++] = ms;

				ms = new mathString();
				ms.string = "(" + calced[index] + "*" + mathStrings[2] + ")";
				ms.value = calced[index].value * mathStrings[2].value;

				calculated[i++] = ms;
			}

		}

		else if (mathStrings.length == 2){

			//calculate for possible values

			ms = new mathString();
			ms.string = mathStrings[0] + "+" + mathStrings[1];
			ms.value = mathStrings[0].value + mathStrings[1].value;

			calculated[i++] = ms;

			ms = new mathString();
			ms.string = mathStrings[0] + "-" + mathStrings[1];
			ms.value = mathStrings[0].value - mathStrings[1].value;

			calculated[i++] = ms;

			ms = new mathString();
			ms.string = mathStrings[0] + "/" + mathStrings[1];
			ms.value = mathStrings[0].value / mathStrings[1].value;

			calculated[i++] = ms;

			ms = new mathString();
			ms.string = mathStrings[0] + "*" + mathStrings[1];
			ms.value = mathStrings[0].value * mathStrings[1].value;

			calculated[i++] = ms;
		}

		else if (mathStrings.length != 6 ){

			throw new Exception("There must be at least six mathStrings");
		}

		return calculated;
	}
	
	/**
	 * Prints out all possible solutions for the given 6 numbers
	 */
	public void printSolutions() {

		if(solutions.size() > 0) {
			
			System.out.println("Here is a possible correct solution: ");

			for(String i : solutions) {

				/*
				 * For some reason mathStrings where passing the above even if the weren't
				 * correct. so this hack solves that. This means the above is being used to
				 * get a small list of possible answers that is then being checked here
				 */
				ScriptEngineManager mgr = new ScriptEngineManager();
				ScriptEngine engine = mgr.getEngineByName("JavaScript");

				double calcAnswer = -111111;

				try {

					calcAnswer = (double) engine.eval(i);

				} catch (ScriptException e) {
				}

				if (calcAnswer == target){
					System.out.println(i);
					break;
				}

			}
			
		} else {
			
			System.out.println("There were no perfect solutions :(");
			
		}

	}

}
