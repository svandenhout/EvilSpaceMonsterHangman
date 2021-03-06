package nl.mprog.evilspacemonsterhangman.models;

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
	
	public SpaceMonster() {
		initSet();
		_chosenLetters = new char[monsterSize];
	}
	
	// this method is only for testing purposes right now
	public Set<Character> getAvailableLetters() {
		return _availableLetters;
	}
	
	// adds a random letter from a string to the _chosenletters[char]
	public void addLetterFromString(String currentWord) {
		int wordLength = currentWord.length();
		char c = currentWord.charAt(generator.nextInt(wordLength));
		addLetterAtRandom(c);
	}
	
	// adds a char to a random spot of the _chosenletters[char]
	private void addLetterAtRandom(char c) {
		_chosenLetters[generator.nextInt(monsterSize - 1)] = c;
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
	public char[] randomChosenLetters() {
		
		// build a list called workset (I can has shuffle Lists)
		List<Character> workSet = new ArrayList<Character>(_availableLetters);
		int i;
		for(i = 0; i < monsterSize; i++) {
			Collections.shuffle(workSet);
			
			// get & remove 0 because so the letter can not be drawn again
			// I use shuffle because i think it's a hilarious language feature
			_chosenLetters[i] = (char) workSet.get(0);
			workSet.remove(0);
		}
		
		return _chosenLetters;
	}
}
