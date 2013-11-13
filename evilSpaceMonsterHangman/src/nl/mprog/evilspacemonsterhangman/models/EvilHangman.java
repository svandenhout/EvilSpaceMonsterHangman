package nl.mprog.evilspacemonsterhangman.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * builds a 2d array out of the currentWordList
 */
public class EvilHangman extends Hangman {
	
	
	// private ArrayList<char[]> wordList = new ArrayList<char[]>;
    private List<char[]> evilWordList;
	
	public EvilHangman(List<String> wordList, int wordLength, int wrongGuesses) {
		super(wordLength, wrongGuesses);
		super.getCurrentWordState();
		this.evilWordList = new ArrayList<char[]>();
	}
	
	// builds a char[] out of the words
	@Override
	public void setWordList(List<String> wordList) {
		for(int i = 0; i < super.wordList.size(); i++) {
			evilWordList.add(super.wordList.get(i).toCharArray()); 
		}
	}
	
	public void doEvilUserInput(int key) {
		StringBuilder s = new StringBuilder();
		
		// build a character from the ascii code
        char c = (char) key;
        
        // check if letter is has been used before
        // if not add the letter to usedLetters
        if(this.usedLetters.indexOf(key) == -1) {
            s.append(this.usedLetters);
            s.append(c);
            this.usedLetters = s.toString();
        }else {
        	//return ALREADY_USED;
        }
	}
	
	private void evilBusiness(char key) {
	    List<char[]> _list = new ArrayList<char[]>();
	    int wordListsize = this.evilWordList.size();
	    
		// make the currentWordState iterable
		char[] _ca = super.currentWordState.toCharArray();
		int i;
		int j;
		
        // for every word in the wordlist check for the letter
		// TODO: JA ZOEK HET UIT GODVERDOMME
		for(i = 0; i < super.wordLength; i++) {
	        for(j = 0; j < wordListsize; j++) {
	        	
	        	// check if the letter is found in the word
	        	// builds a list of words that don't contain the letter
	        	if(Arrays.binarySearch(evilWordList.get(j), key) == -1) {
	        		_list.add(this.evilWordList.get(j));
	        	}
	        }
		}
        
        // use the max operator to discover the largest number
        
        
        // put the currentWordState back into string form
        super.currentWordState = new String(_ca);
	}	
}