package nl.mprog.evilspacemonsterhangman.models.spacemonster;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class SpaceMonster {
	private final static int monsterSize = 6;
	
	private Random generator = new Random();
	private char[] _chosenLetters;
    private List<Character> availableLetters;
	
	// constructor 
	public SpaceMonster() {
		_chosenLetters = new char[monsterSize];
	}
	
	// random the _chosenletters
	// TODO: make sure there are no equal letters and used letters are not displayed
	// split into 2 char[], an allready used and a usable char list. these will
	// exchange every loop
	
	// dus ArrayList<int> availableLetters
	public void randomArray(String usedLetters) {
		char[] ca;
		ca = usedLetters.toCharArray();
		for(char c : _chosenLetters) {
			int i = generator.nextInt(availableLetters.size());
			c = availableLetters.get(i);
			availableLetters.remove(i);
		}
	}
	
	
	// adds a char to a random spot of the _chosenletters[char]
	public void addLetter(char c) {
		_chosenLetters[generator.nextInt(5)] = c;
	}
	
	// builds an available letters array, it excludes the usedLetters String
	private List<Character> buildAvailableLetters(String usedLetters) {
		char[] ca = usedLetters.toCharArray();
		
		availableLetters = new ArrayList<Character>();
		// build list with the alphabet
		for(int i = 97; i < 123; i++) {
			for(char c : ca) {
				if(c != (char) i) {
					// geen idee..??? hoe dit uit gaat komen
				}
			}
			availableLetters.add((char) i);
		}
		
		return availableLetters;
	}
}
