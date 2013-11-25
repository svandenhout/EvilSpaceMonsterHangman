package nl.mprog.evilspacemonsterhangman.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * the class initiates an instance of the hangman game
 * the purpose of evilHangman is to make the system as evil as possible
 * the doEvilUserInput(int key) method will change the currentWordState
 * to the least usable value. There is no word, the system just cheats by
 * giving the currentWordState that has the most amount of possible words.
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
	 * evilBusiness makes sure that this.currentWordState becomes the
	 * least usefull answer possible.
	 */
	public void evilBusiness(int key) {
		List<EqualStringCount> equivList = new ArrayList<EqualStringCount>();
		String equivString;
		
		// to make sure the equalstringcount has a value (java...)
		equivString = this.buildEquivalenceString(super.wordList.get(0), key);
		EqualStringCount topEsc = new EqualStringCount(equivString);
        // for every word in the wordlist check for the letter
	    for(String word : super.wordList) {
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
	    
	    // sort the arrayList from most common to least common
	    // array
	    Collections.sort(equivList, new EqualStringCount());
	    
	    // the first element in the equivList is always one of
	    // the most common Strings
	    topEsc = equivList.get(0);
	    
	    // in case of a tie it will find the least actual letters
	    // by finding the word with the most '_' in it
	    int hiCount = 0;
	    for(EqualStringCount esc : equivList) {
	    	// only if the esc is equal to the highest one
	    	if(esc.count() == topEsc.count()) {
	    		char[] ca = esc.getString().toCharArray();
	    		
	    		int count = 0;
	    		
	    		// loop through the char array to count the '_'
	    		for(char c : ca) {
	    			if(c == '_')
	    				count++;
	    		}
	    			    		
	    		// change the currentwordstate to the "equivilanceString" with 
	    		// the least letters
	    		if(count > hiCount) 
	    			super.currentWordState = esc.getString();
	    		
    			hiCount = count;
	    	}
	    }
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