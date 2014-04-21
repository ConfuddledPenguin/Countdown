package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class executes a full game of countdown
 *
 */
public class Countdown {
	
	private Player playerOne;
	private Player playerTwo;
	private Round currentRound;
	private GameObjects objects;
	private char[] gameOrder;
	private String gameOrderString;
	private UserIO io;
	private boolean customGame;
	private boolean timerActive = true;
	
	//bunch of constants for the gameOrder and gameOrderString
	private static char WORD = 'W';
	private static char NUMBER = 'N';
	private static char CONUNDRUM = 'C';
	
	private long timestamp;

	/**
	 * The constructor for the countdown class. Takes a GameObjects object
	 * @param go The GameObjects
	 */
	public Countdown(GameObjects go) {

		this.objects = go;
		playerOne = go.pOne;
		playerTwo = go.pTwo;
		
		io = new UserIO();
		
		timestamp = System.nanoTime();
		
	}
	
	/**
	 * The constructor for the countdown class. Takes a GameObjects object
	 * @param go The GameObjects
	 * @param timerActive Is the timer to be active?
	 */
	public Countdown(GameObjects go, boolean timerActive) {

		this.objects = go;
		playerOne = go.pOne;
		playerTwo = go.pTwo;
		this.timerActive = timerActive;
		
		io = new UserIO();
		
		timestamp = System.nanoTime();
		
	}

	/**
	 * Play a full game of countdown
	 */
	public void play() {

		gameOrderString = "WWNWWWWNNWWWWNC";
		gameOrder = gameOrderString.toCharArray();
		
		customGame = false;
		
		play(0);
	}
	
	/**
	 * Plays the game from a starting pos
	 * 
	 * Handles the actual playing of the game
	 * 
	 * @param start The round number to start from
	 */
	private void play(int start){
		
		//Say hi :)
		printWelcome();
		
		//Loop for the number of rounds i.e 15
		int roundNo = start;
		while ( roundNo < gameOrder.length){
			
			//IF user wishes to continue or quit the game
			if ( roundNo != 0){
				
				System.out.println("Ready to continue> (Yes/No) :");
				boolean resume = io.getYesNo(); // should be continue not resume. But continue is a keyword and using it would be bad
				if (!resume){
					break;
				}
			}
			
			// if word round
			if ( gameOrder[roundNo] == WORD ){
				
				//Let the player know what is going on
				io.printLines(1);
				if (roundNo != 0)
					System.out.println("Next up we have a word round\n");
				else if (roundNo == gameOrder.length)
					System.out.println("Sadly we are nearing the end\nwith just a word round left to play\n");
				else
					System.out.println("The first round, as always, is a word round\n");
				
				currentRound = new WordRound(objects, timerActive);
				currentRound.play();
			}
			
			// If its a numbers round
			if ( gameOrder[roundNo] == NUMBER ){
				
				//Let the player know what is going on
				io.printLines(1);
				if (roundNo != 0)
					System.out.println("Next up we have a number roundn");
				else if (roundNo == gameOrder.length)
					System.out.println("Sadly we are nearing the end\nwith just a number round left to play\n");
				else
					System.out.println("The first round, is a number round\n");
				
				currentRound = new  NumberRound(objects, timerActive);
				currentRound.play();
			}
			
			//If its a conundrum round
			if (gameOrder[roundNo] == CONUNDRUM){
				
				//Let the player know what is going on
				io.printLines(1);
				if (roundNo != 0)
					System.out.println("Next up we have a conundrum round\n");
				else if (roundNo == gameOrder.length)
					System.out.println("Sadly we are nearing the end.\nWhich of course means we are going to play\n" +
				" a coundrumround\n");
				else
					System.out.println("The first round, is a conundrum round\n");
				
				currentRound = new ConundrumRound(objects, timerActive);
				currentRound.play();
			}
			
			/*
			 * Autosave the game :)
			 * 
			 * Incremented roundNo is passed as it should save the roundNo of the next round not the one
			 * that has just been played.
			 */
			save(++roundNo);
		}// Close game loop
		
		printClose();
	}

	/**
	 * Prints out a nice friendly welcome message
	 * to make the players feel all warm and fuzzy inside
	 */
	private void printWelcome(){
		
		System.out.println("----------------------------------------------------");
		System.out.println("----------------------------------------------------");
		System.out.println("Hello and welcome to countdown");
		System.out.println("---------------------------\n");
		
	}
	
	/**
	 * Prints a final message
	 */
	private void printClose(){
		
		System.out.println("Well that the game over.");
		
		if (playerTwo == null){
			printWinner(playerOne, null);
		}else{
		
			if (playerOne.getScore() >= playerTwo.getScore())
				printWinner(playerOne, playerTwo);
			else
				printWinner(playerTwo, playerOne);
		}
		
		System.out.println("We hope to see you again soon for another game.");
	}
	
	/**
	 * Announces the winner of the game
	 * 
	 * @param winner the games winner
	 * @param loser the games loser
	 */
	private void printWinner( Player winner, Player loser){
		
		System.out.println("Its seems that " + winner.getName() + " has won.");
		
		if (loser != null){
		
			int pointdiff = winner.getScore() - loser.getScore();
			
			if ( pointdiff < 10){
				System.out.println("It was close though with only a " + pointdiff + " lead.");
				System.out.println("So it was a very exciting game.");
			} else if (pointdiff > 100){
				System.out.println("It seems that " + winner.getName() + " kicked " + loser.getName() + " ass, with a " + pointdiff + 
						" differnce.");
			} else if (pointdiff == 0){
				System.out.println("A draw! Well done both players.");
			} else {
				System.out.println(winner.getName() + "won with a " + pointdiff + "lead over " + loser.getName() + ".\n" +
			"A good game all round");
				
			}
		}
		
		//Add players to the leaderboards
		
		String gametypeString;
		
		if (!customGame)
			gametypeString = LeaderBoard.FULLGAME;
		else
			gametypeString = LeaderBoard.CUSTOMGAME;
				
		
		objects.leaders.addScore(playerOne.getName(), gametypeString, playerOne.getScore());
		
		if (loser != null){
			objects.leaders.addScore(playerTwo.getName(), gametypeString, playerTwo.getScore());
			System.out.println("commiserations " + loser.getName() + " and of course well done " + winner.getName() + ".");
		}
		
	}
	
	/**
	 * Loads the game
	 */
	public void load() {

		io.printLines(1);
		
		
		try{
			
			File xmlFile = new File("saves.xml");
			DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFac.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);
			
			//Quick sanity formating. Should be fine unless someone has been playing with the file . . .
			doc.getDocumentElement().normalize();
			
			//Get all the saves
			NodeList saves = doc.getElementsByTagName("save");
			
			//get the save the user wants
			long timestamp = loadSelector(saves);
			
			Element save = null;
			for (int i = 0; i < saves.getLength(); i++){
				
				Element saveTemp = (Element) saves.item(i);
				
				if (saveTemp.getAttribute("id").equals("" + timestamp)){
					
					save = (Element) saves.item(i);
					break;
				}
			} //close save hunter
			
			if(save == null){
				throw new Exception("error finding save");
			}
			
			/*
			 * Now the save to be loaded has been selected load it
			 * and update the game state.
			 */
			
			//update timestamp
				this.timestamp = timestamp;
			
			//Update the players scores with the saved values
				NodeList players = save.getElementsByTagName("player");
				
				Element pOne = (Element) players.item(0);
				NodeList pointsData = pOne.getElementsByTagName("score");
				int points = Integer.parseInt( pointsData.item(0).getTextContent() );
				playerOne.updateScore(points);
				
				if (playerTwo != null){
					
					Element pTwo = (Element) players.item(1);
					pointsData = pTwo.getElementsByTagName("score");
					points = Integer.parseInt( pointsData.item(0).getTextContent() );
					playerOne.updateScore(points);
				}
			
			//load gamedata
				NodeList gameList = save.getElementsByTagName("game");
				Element game = (Element) gameList.item(0);
				
				NodeList roundstringData = game.getElementsByTagName("roundString");
				gameOrderString = roundstringData.item(0).getTextContent();
				gameOrder = gameOrderString.toCharArray();
				
				NodeList roundNoData = game.getElementsByTagName("roundNo");
				int roundNo = Integer.parseInt( roundNoData.item(0).getTextContent() );
				
				NodeList customGameData = game.getElementsByTagName("customGame");
				String custom = customGameData.item(0).getTextContent();
				if (custom.equals("TRUE"))
					customGame = true;
				else if (custom.equals("FALSE"))
					customGame = false;
				else
					throw new Exception("Error parsing customGame");
			
			//free everything for garbage collection
				gameList = null;
				game = null;
				
				pOne = null;
				pointsData = null;				
				
				roundstringData = null;
				roundNoData = null;
				customGameData = null;
				custom = null;
			
			//And finally play the game
				play(roundNo);
			
			
		} catch (FileNotFoundException e){
			
			System.out.println("No saves exist");
		} catch (Exception e){
			System.err.println("The savefile has become corrupted. I hope you had backups");
			e.printStackTrace();
		}
	}
	
	/**
	 * Allows the player(s) to select a game to load
	 * 
	 * @param saves the nodelist of save
	 */
	private long loadSelector(NodeList saves){
		
		HashMap<Integer, Long> saveMap = new HashMap<>();
		
		System.out.println("Please choose a save to load: ");
		System.out.println("\n Number\t:\tType\t\t:\troundNo");
		
		for (int i = 0; i < saves.getLength(); i++){
			
			Node save = saves.item(i);
			
			NodeList saveData = save.getChildNodes();
			
			//if a two player game length of save is 7
			//if one player game length of save is 5
			
			//if two player
			if (playerTwo != null){
				
				if (saveData.getLength() == 7){
					
					//if two player game and its a two player save then cont
					Element pOneData = (Element) saveData.item(1);
					Element pTwoData = (Element) saveData.item(3);
					
					String pOneName = pOneData.getElementsByTagName("name").item(0).getTextContent();
					String pTwoName = pTwoData.getElementsByTagName("name").item(0).getTextContent();
					
					//If the names of the current session match the names in the save
					if ( playerOne.getName().equals(pOneName) || playerTwo.getName().equals(pTwoName)){
						
						loadSelectorPrinter(save, saveMap, i);
					}
				}
				
			}
			
			//if one player
			if (playerTwo == null){
				
				if (saveData.getLength() == 5){
					
					//if two player game and its a two player save then cont
					Element pOneData = (Element) saveData.item(1);
					
					String pOneName = pOneData.getElementsByTagName("name").item(0).getTextContent();
					
					//If the names of the current session match the names in the save
					if ( playerOne.getName().equals(pOneName)){
						
						loadSelectorPrinter(save, saveMap, i);
					}
				}
				
			}
			
			
		}

		System.out.println("note: other games can not be loaded by the current players");
		
		int selection;
		do {
			System.out.println("Select one of the options:");
			selection = io.getNumber();
		}while ( selection < 0 || selection >= saves.getLength());
		
		//return the timestamp of the selected save;
		return saveMap.get(selection);
		
	}//close loadSelector()

	/**
	 * Prints out the game details for loadSelector
	 * Here to reduce LOC
	 * 
	 * @param save The savedata
	 * @param saveMap The map to which we are storing the timestamps
	 * @param i the current iteration of the loop
	 */
	private void loadSelectorPrinter(Node save, HashMap<Integer, Long> saveMap, int i){
		
		Element saveElement = (Element) save;
		
		Long timestamp = Long.valueOf(saveElement.getAttribute("id")).longValue();
		saveMap.put(i, timestamp);
		
		NodeList game = saveElement.getElementsByTagName("game");
		NodeList gameData = game.item(0).getChildNodes();
		
		String custom = gameData.item(5).getTextContent();
		String roundNo = gameData.item(3).getTextContent();
		
		String roundtype = "Full game";
		if (custom.equals("TRUE")){
			roundtype = "Custom game";
		}
		
		System.out.println(i + "\t:\t" + roundtype + "\t:\t " + roundNo);
	}
	
	/**
	 * Saves the game
	 */
	private void save(int roundNoValue) {

		Element save = null;
		
		String filepath = "saves.xml";
		File f = new File(filepath);
		
		if ( !f.exists())
			createSaveFile();
		
		
		try {
			
			//create tools
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			
			Node root = doc.getFirstChild();
			
			NodeList saves = root.getChildNodes();
			
			//Check if save of this game already exists
			boolean found = false;
			for (int i = 0; i < saves.getLength(); i++){
				
				Node temp = saves.item(i);
				
				if ( temp instanceof Element){
					save = (Element) temp;
					
					if (save.getAttribute("id").equals("" + timestamp)){
						found = true;
						break;
					}
				}
			}//close save finder
			
			if (found){	//If save exits, update existing save
				
				/* If the game is completed then delete it
				 */
				if (roundNoValue == gameOrder.length){
					System.out.println("GAME DONE DELETING");
					Node saveNode = (Node) save;
					root.removeChild(saveNode);
					
				} else { // else update the game		
				
					NodeList saveDataNodes = save.getChildNodes();
					
					//Update player info
					
					/*
					 * If player two exists then the gamedata will be in position 4 of the list
					 * otherwise it will be in position 5. So while updating the player
					 * we shall calc the position while updating player
					 * 
					 * The weird positioning is due to the DOM model seeing text between the nodes
					 */
					int pos = 3;
					
					playerUpdater(saveDataNodes, playerOne);
					
					if (playerTwo != null){
						playerUpdater(saveDataNodes, playerTwo);
						pos = 5;
					}
					
					
					//Next update the game data
					Node gameData = saveDataNodes.item(pos);
	
					NodeList gameDataNodes = gameData.getChildNodes();
					
					//Loop through them until value to be updated is found
					for (int j = 0; j < gameDataNodes.getLength(); j++){
						
						Node current = gameDataNodes.item(j);
						
						if (current.getNodeName().equals("roundNo"))
							current.setTextContent("" + roundNoValue);
					}
					
					
				} // Close update/delete if
				
				
			}else{ // If save doesn't exist create a new one
				
				//create save
				save = doc.createElement("save");
				save.setAttribute("id", "" + timestamp);
				
	
				//save the players
				playerSaver(doc, save, playerOne);
				if (playerTwo != null)
					playerSaver(doc, save, playerTwo);
				
				
				//save the game details
				Element game = doc.createElement("game");
				
					Element roundString = doc.createElement("roundString");
					roundString.appendChild(doc.createTextNode(gameOrderString));
					game.appendChild(roundString);
				
					Element roundNo = doc.createElement("roundNo");
					roundNo.appendChild(doc.createTextNode("" + roundNoValue));
					game.appendChild(roundNo);
				
					Element customeGameElement = doc.createElement("customGame");
					if (customGame)
						customeGameElement.appendChild(doc.createTextNode("TRUE"));
					else
						customeGameElement.appendChild(doc.createTextNode("FALSE"));
					game.appendChild(customeGameElement);
				
				save.appendChild(game);	
				
				root.appendChild(save);
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
						
		} catch (Exception e){
			System.out.println("The save file has become corrupted! Delete it! I hope you back things up");
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Updates the players score in DOM for writing to disk
	 * 
	 * @param saveDataNodes The nodes of save
	 * @param p The player
	 */
	private void playerUpdater(NodeList saveDataNodes, Player p){
		
		//Grab the nodes in player
		NodeList playerDataNodes = saveDataNodes.item(p.getNumber() -1).getChildNodes();
		
		//Loop through them until value to be updated is found
		for (int j = 0; j < playerDataNodes.getLength(); j++){
			
			Node current = playerDataNodes.item(j);
			
			if (current.getNodeName().equals("score"))
				current.setTextContent("" + p.getScore());
		}
	}
	
	/**
	 * A small helper method for the save method. This saves the 
	 * Player to the DOM, for writing to disk.
	 * 
	 * @param doc The Document
	 * @param save The save Element
	 * @param p the Player to be saved
	 */
	private void playerSaver(Document doc, Element save, Player p){
		
		Element player = doc.createElement("player");
		
			Element number = doc.createElement("number");
			number.appendChild(doc.createTextNode("" + p.getNumber()));
			player.appendChild(number);
			
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(p.getName()));
			player.appendChild(name);
			
			Element score = doc.createElement("score");
			score.appendChild(doc.createTextNode("" + p.getScore()));
			player.appendChild(score);
		
		save.appendChild(player);
	}
	
	/**
	 * Creates the save file if this is the first time this game has been played
	 */
	private void createSaveFile(){
		
		
		try {
			
			//create tools
			DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFac.newDocumentBuilder();
			
			//create doc
			Document doc = docBuilder.newDocument();
			
			//root
			Element rootElem = doc.createElement("savedGames");
			doc.appendChild(rootElem);
			
			//write file
			TransformerFactory tranFact = TransformerFactory.newInstance();
			Transformer transformer = tranFact.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("saves.xml"));
			transformer.transform(source, result);
			
		} catch (Exception e){
			System.out.println("Error writing save file. Sorry about that");
		}
		
		
	}
	
	/**
	 * Allows the playing of a custom game of countdown
	 * In this game type the user specifies which rounds
	 * they would like to play
	 */
	public void playCustomGame(){
		
		io.printLines(2);
		customGame = true;
		
		System.out.println("So a custom game, is it?");
		
		boolean unvalid;
		
		do {
			
			unvalid = false;
			
			System.out.println("\nPlease enter a string of type awesome :");
			System.out.println("I may have lied sorry. Please enter a string of rounds you would like to play");
			System.out.println("W for word, N for number, C for conundrum. For example: WWNNWC");
			
			gameOrderString = io.getString().toUpperCase();
			gameOrder = gameOrderString.toCharArray();
			
			for ( int i = 0; i < gameOrder.length; i++){
				
				char c = gameOrder[i];
				
				if ( !(c == WORD || c == NUMBER || c == CONUNDRUM) ){
					
					System.out.println("Error!!!!!! try again!!!\n");
					unvalid = true;
					break;
				}
			}
					
		}while(unvalid);
		
		play(0);
		
	}

}