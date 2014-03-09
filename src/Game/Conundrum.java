package Game;

public class Conundrum extends Round {
	
	private String answer1;
	private String answer2;
	
	public Conundrum(Dictionary dict, Player pOne, Player pTwo) {
		super(dict, pOne, pTwo);
		
		play();
	}
	
	public void play() {

		System.out.println("Conundrum");
		
		String anagram = dict.getAnagram();

		
	}

}
