package nl.mprog.evilspacemonsterhangman.models;

import java.util.List;

public class EvilHangman extends Hangman {
	
	private String[] EvilWordList;
	
	public EvilHangman(List<String> wordList, int wordLength, int wrongGuesses) {
		super(wordLength, wrongGuesses);
		super.getCurrentWordState();
	}
	
//	public int doEvilUserInput(int key) {
//		
//	}
}