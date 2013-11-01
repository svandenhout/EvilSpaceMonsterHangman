package nl.mprog.evilspacemonsterhangman.models;

import java.util.List;

public class EvilHangman extends Hangman {
	
    public final int ALREADY_USED = 0;
    public final int INVALID_INPUT = 1;
    public final int WRONG_GUESS = 2;
    public final int CORRECT_GUESS = 3;
    public final int GAME_WON = 4;
    public final int GAME_LOST = 5;
    public final int INPUT_ERROR = 6;
	
	private String[] EvilWordList;
	
	public EvilHangman(List<String> wordList, int wordLength, int wrongGuesses) {
		super(wordLength, wrongGuesses);
		super.getCurrentWordState();
	}
	
	public int doEvilUserInput(int key) {
		
		return INPUT_ERROR;
	}
}