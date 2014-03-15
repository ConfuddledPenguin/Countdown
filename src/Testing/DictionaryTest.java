package Testing;

import static org.junit.Assert.*;

import java.sql.Array;
import java.util.ArrayList;

import org.junit.Test;

import Game.Dictionary;

public class DictionaryTest {

	@Test
	public void testGetBestWord() {
		
		Dictionary test = new Dictionary();
		
		System.out.println("abc");
		ArrayList<String> temp = test.getBestWords("abc");
		for (String s: temp){
			System.out.println(s);
		}
		System.out.println();
		
		System.out.println("cba");
		temp = test.getBestWords("cba");
		for (String s: temp){
			System.out.println(s);
		}
		System.out.println();
		
		for (int i = 0; i < 10; i++){
			String word = test.getAnagram();
			System.out.println(word);
			temp = test.getBestWords(word);
			for (String s: temp){
				System.out.println(s);
			}
			System.out.println();
		}
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
