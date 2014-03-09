package Game;

public class Conundrum extends Round {
	
	private String answer1;
	private String answer2;
	
	public Conundrum(Dictionary dict, Player pOne, Player pTwo) {
		super(dict, pOne, pTwo);
		
	}
	
	public void play() {

		System.out.println("Conundrum");
		
	}

	public String returnAnswer1() {

		return answer1;
	}
	

	public String returnAnswer2() {

		return answer2;

	}

}
