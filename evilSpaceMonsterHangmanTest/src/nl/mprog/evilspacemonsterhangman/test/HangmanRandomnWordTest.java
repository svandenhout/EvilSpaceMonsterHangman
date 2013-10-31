package nl.mprog.evilspacemonsterhangman.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import nl.mprog.evilspacemonsterhangman.models.*;

public class HangmanRandomnWordTest extends TestCase {
	private Hangman hangman;
	private ArrayList<String> wl = new ArrayList<String>();
	
	/*
	 * test setCurrentWord() by adding null as an argument it should pick 
	 * a randomn word out of the wordlist.
	 */
	
	protected void setUp() throws Exception {
		wl.add("hallo");
		wl.add("abcde");
		wl.add("pinda");
		wl.add("blaas");
		hangman = new Hangman(5, 8);
		hangman.setWordList(wl);
		
		super.setUp();
	}
	

	public void testAdd() {
		hangman.setCurrentWord("xyz12");
		hangman.setCurrentWord(null);
		String currentWord = hangman.getCurrentWord();
		assertTrue(!currentWord.equals("xyz12"));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
