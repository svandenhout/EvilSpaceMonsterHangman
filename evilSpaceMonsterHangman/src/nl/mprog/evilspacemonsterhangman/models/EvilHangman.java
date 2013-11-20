package nl.mprog.evilspacemonsterhangman.models;

import java.util.ArrayList;
import java.util.List;

/*
 * builds a 2d array out of the currentWordList
 */
public class EvilHangman extends Hangman {
	
	
    public EvilHangman(int wordLength, int wrongGuesses) {
		super(wordLength, wrongGuesses);
		super.getCurrentWordState();
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
	
	/*
	 * TODO: build this method
	 * build arrayList of possible answers
	 * ____
	 * _E__
	 * _EE_
	 * E_E_
	 * 
	 * build an equivilance class array and count the doubles
	 */
	public void evilBusiness(int key) {
		List<EqualStringCount> equivList = new ArrayList<EqualStringCount>();
		String equivString;
		
		// to make sure the equalstringcount has a value (java...)
		equivString = this.buildEquivalenceString(wordList.get(0), key);
		EqualStringCount topEsc = new EqualStringCount(equivString);
        // for every word in the wordlist check for the letter
	    for(String word : wordList) {
	    	equivString = this.buildEquivalenceString(word, key);
	    	if(equivList.size() > 0) {
	    		// loop through the equivList to check for equalStrings
		    	// esc is short for EqualStringCount
		    	for(EqualStringCount esc: equivList) {
		    		// checkWord increments when an equal word is found
		    		if(esc.checkWord(equivString)) 
		    			break;
		    		// when no equal word is found a new esc will be added to the arrayList
		    	}
		    	equivList.add(new EqualStringCount(equivString));
	    	} else {
	    		// when the equivList size is zero it has to start off by putting
	    		// the first word into an EqualStringCount() object;
	    		equivList.add(new EqualStringCount(equivString));
	    	}
    	}
	    
	    // return the best solution 
	    // the highest equivList.count() is probably the best solution
	    for(EqualStringCount esc : equivList) {
	    	if(esc.count() > topEsc.count()) {
	    		topEsc = esc;
	    	}
	    } 
	    super.currentWordState = topEsc.getString();
	}
	
	/*
	 * returns an equivalence string, a representation of a
	 * possible outcome if the user would get this word
	 * example:
	 * 
	 * "____"
	 * "_E__"
	 */
	public String buildEquivalenceString(String word, int key) {
		// use the currentWordState to generate several
		// equivalence Strings
		char[] _ca = super.currentWordState.toCharArray();
		char c = (char) key;
		int i = 0;
		while(true) {
            if (word.indexOf(key, i) != -1) {
                i = word.indexOf(key, i);
                _ca[i] = c;
                i++;
            }else {
                break;
            }
        }
		String equivString = new String(_ca);
		return equivString;
	}
}