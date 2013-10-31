package nl.mprog.evilspacemonsterhangman.models;

import java.util.List;
import java.util.Random;

/**
 * The hangman class covers all of the aspects to actually play the game of hangman,
 *
 * Created by steven on 5/28/13.
 */

public class Hangman {
	
    // all of the game states
    public final int ALREADY_USED = 0;
    public final int INVALID_INPUT = 1;
    public final int WRONG_GUESS = 2;
    public final int CORRECT_GUESS = 3;
    public final int GAME_WON = 4;
    public final int GAME_LOST = 5;
    public final int INPUT_ERROR = 6;
    
    private static final int MIN_UNICODE_INDEX = 96;
    private static final int MAX_UNICODE_INDEX = 123;

    private int wordLength;
    private int wrongGuesses;
    private int wrongGuessesDone;

    private List<String> wordList;
    private String currentWord;
    private String currentWordState;
    private String usedLetters;

    /*
     * the constructor takes all of the game settings as arguments
     * appearantly it takes the whole wordlist as well
     */
    public Hangman(int wordLength, int wrongGuesses) {
        this.wordLength = wordLength;
        this.initEmptyCurrentWordState();
        this.wrongGuesses = wrongGuesses;
        this.usedLetters = "";
        this.wrongGuessesDone = 0;
    }
    
    /* 
     * use to set the wordList
     */
    public void setWordList(List<String> wordList) {
    	this.wordList = wordList;
    }
    
    /*
     * returns the current word (the one that will be played with)
     */
    public String getCurrentWord() {
        return this.currentWord;
    }

    public String getCurrentWordState() {
        return this.currentWordState;
    }

    public String getUsedLetters() {
        return this.usedLetters;
    }

    public int getWrongGuessesDone() {
        return this.wrongGuessesDone;
    }

    /*
     * initialises an empty currentWordState variable
     */
    protected void initEmptyCurrentWordState() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.wordLength; i ++) {
            s.append("_");
        }

        this.currentWordState = s.toString();
    }

    // primarily for testing purposes 
    public void setCurrentWord(String word) {
    	if(word != null) {
    		this.currentWord = word;
    	}else {
    		this.currentWord = this.chooseRandomWord();
    	}
    }
    
    /*
     * uses Pseudorandomness to pick and set the current word from the wordlist
     */
    private String chooseRandomWord() {
        Random random = new Random();
        int number = random.nextInt(this.wordList.size());
        return this.wordList.get(number);
    }

    /*
     * checks for the validaty of the entered character and 
     * changes the currentWordState apropriately 
     * 
     * returns an integer referring to the gamestate
     * 
     * ALREADY_USED = 0;
	 * INVALID_INPUT = 1;
	 * WRONG_GUESS = 2;
	 * CORRECT_GUESS = 3;
	 * GAME_WON = 4;
	 * GAME_LOST = 5;
	 * INPUT_ERROR = 6;
     */
    public int doUserInput(int key) {
        StringBuilder s = new StringBuilder();
        char c = (char) key;

        if(this.usedLetters.indexOf(key) == -1) {
            s.append(this.usedLetters);
            s.append(c);
            this.usedLetters = s.toString();
        }else {
        	return ALREADY_USED;
        }
    
        char[] ca = this.currentWordState.toCharArray();
        int i = 0;
        while(true) {
            if (this.currentWord.indexOf(key, i) != -1) {
                i = this.currentWord.indexOf(key, i);
                ca[i] = c;
                i++;
            }else {
                break;
            }
        }
        
        // check if input is actually a-z (only lowercase right now)
        if(key < MIN_UNICODE_INDEX || key > MAX_UNICODE_INDEX) {
        	return INVALID_INPUT;
        }
        
        // if i is still 0 that means it hasn't incremented inside the while loop
        // that means a correct match has not been found 
        if(i == 0) {
        	this.wrongGuessesDone++;
        	// when true the game is lost
            if(this.wrongGuessesDone == this.wrongGuesses) {
                return GAME_LOST;
            }else {
            	return WRONG_GUESS;
            }
        }
        
        if(i > 0) {
        	// when true the game is won
            if(this.currentWordState.equals(this.currentWord)) {
            	return GAME_WON;
            }else {
            	this.currentWordState = new String(ca);
            	return CORRECT_GUESS;
            }
        }
        
        // I don't think this is actually possible 
        return INPUT_ERROR;
    }

    public void resetValues() {
        this.usedLetters = "";
        this.initEmptyCurrentWordState();
        this.wrongGuessesDone = 0;
    }
}