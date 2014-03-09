package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.Dictionary;

public class DictionaryTest {

	@Test
	public void testGetBestWord() {
		
	}

	@Test
	public void testCheckWord() {

		Dictionary test = new Dictionary();
		
		assertEquals("Should be true", true, test.checkWord("fish"));
		assertEquals("Should be true", true, test.checkWord("cheese"));
		assertEquals("Should be true", true, test.checkWord("apple"));
		assertEquals("Should be true", true, test.checkWord("fishes"));
		
		assertEquals("Should be false", false, test.checkWord("aaaaa"));
		assertEquals("Should be false", false, test.checkWord("fhdsheid"));
		
		assertEquals("Should be false", false, test.checkWord("aaaaaaaaaa"));
		assertEquals("Should be false", false, test.checkWord(""));
	}

	@Test
	public void testGetAnagram() {
		
		Dictionary test = new Dictionary();
		
		//Impossible to test
		test.getAnagram();
	}

}
