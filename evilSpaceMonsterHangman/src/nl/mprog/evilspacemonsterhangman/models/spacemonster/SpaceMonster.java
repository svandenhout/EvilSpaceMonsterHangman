package nl.mprog.evilspacemonsterhangman.models.spacemonster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.List;
import java.util.Set;

/*
 * Class makes a char[] for the spacemonster to use as his eyes
 * (the spacemonster has letters for eyes....)
 */
public class SpaceMonster {
	private final static int monsterSize = 6;
	
	private Random generator = new Random();
	private char[] _chosenLetters;
    private Set<Character> _availableLetters;
	
	// constructor 
	public SpaceMonster() {
		initSet();
		_chosenLetters = new char[monsterSize];
	}
	
	// adds a char to a random spot of the _chosenletters[char]
	public void addLetterAtRandom(char c) {
		_chosenLetters[generator.nextInt(5)] = c;
	}
	
	// removeUsedLetter() is used so the spacemonster won't 
	// be getting any letters that are already used by the player.
	public void removeUsedLetter(char c) {
		_availableLetters.remove(c);
	}
	
	// builds a set with the alphabet in it
	private void initSet() {
		_availableLetters = new HashSet<Character>();
		// build list with the alphabet
		for(int i = 97; i < 123; i++) {
			_availableLetters.add((char) i);
		}
	}
	
	// random the _chosenletters for the spacemonster eyes
	public char[] randomChosenLetters(String usedLetters) {
		
		// build a list called workset (I can has shuffle Lists)
		List<Character> workSet = new ArrayList<Character>(_availableLetters);
		
		for(char c : _chosenLetters) {
			Collections.shuffle(workSet);
			
			// get & remove 0 because the set is shuffled afterwards
			// I use shuffle because i think it's a hilarious language feature
			c = workSet.get(0);
			workSet.remove(0);
		}
		
		return _chosenLetters;
	}
}
