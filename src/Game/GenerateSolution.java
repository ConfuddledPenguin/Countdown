package Game;

import java.util.ArrayList;

public class GenerateSolution implements Runnable {
	
	ArrayList<String> solutions;
	ArrayList<Integer> numbers;
	int target;

	private class mathString{
		
		String string;
		double value;
		
		public String toString(){
			return string;
		}
	}
	
	
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
			msa[3] = mathStrings[3];
			msa[4] = mathStrings[4];
			msa[5] = mathStrings[5];
			mathString[] ms2 = calc (msa);
			
			//merge them to give 1024 possible values
			for (int index1 = 0; index1 < ms1.length; index1++){
				
				for (int index2 = 0; index2 < ms2.length; index2++){
					
					ms = new mathString();
					ms.string = ms1[index1] + "+" + ms2[index2];
					ms.value = ms1[index1].value + ms2[index2].value;
					
					calculated[i++] = ms;
					
					ms = new mathString();
					ms.string = ms1[index1] + "-" + ms2[index2];
					ms.value = ms1[index1].value - ms2[index2].value;
					
					calculated[i++] = ms;
					
					ms = new mathString();
					ms.string = ms1[index1] + "/" + ms2[index2];
					ms.value = ms1[index1].value / ms2[index2].value;
					
					calculated[i++] = ms;
					
					ms = new mathString();
					ms.string = ms1[index1] + "*" + ms2[index2];
					ms.value = ms1[index1].value * ms2[index2].value;
					
					calculated[i++] = ms;
				}
			}
		}
		
		if (mathStrings.length == 3){
			
			mathString mswithtwo[] = new mathString[2];
			mswithtwo[0] = mathStrings[0];
			mswithtwo[1] = mathStrings[1];
			
			mathString[] calced = calc(mswithtwo);
			
			for (int index = 0; index < 4; index++){
				
				ms = new mathString();
				ms.string = calced[index] + "+" + mathStrings[2];
				ms.value = calced[index].value + mathStrings[2].value;
				
				calculated[i++] = ms;
				
				ms = new mathString();
				ms.string = calced[index] + "-" + mathStrings[2];
				ms.value = calced[index].value - mathStrings[2].value;
				
				calculated[i++] = ms;
				
				ms = new mathString();
				ms.string = calced[index] + "/" + mathStrings[2];
				ms.value = calced[index].value / mathStrings[2].value;
				
				calculated[i++] = ms;
				
				ms = new mathString();
				ms.string = calced[index] + "*" + mathStrings[2];
				ms.value = calced[index].value * mathStrings[2].value;
				
				calculated[i++] = ms;
			}
				
		}
		
		if (mathStrings.length == 2){
			
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
		
		if (mathStrings.length != 6 ){
			
			throw new Exception("There must be at least six mathStrings");
		}
		
		return calculated;
	}

	public void printSolutions() {
		
		System.out.println("Here are all of the possible correct solutions: ");
		
		for(String i : solutions) {
			
			System.out.println(i);
			
		}
		
	}

}
