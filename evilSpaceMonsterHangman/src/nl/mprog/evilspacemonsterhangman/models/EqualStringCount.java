package nl.mprog.evilspacemonsterhangman.models;

import java.util.Comparator;

public class EqualStringCount implements Comparator<EqualStringCount>, Comparable<EqualStringCount> {
	/*
	 * Counts equal instances of a string object
	 * how to use: the constructor sets the word, use checkWord() to see if a 
	 * word is equal. When false is returned a new instance can be made.
	 */
	private int counter;
	private String currentWord;
	
	// comparator won't work without this constructor
	public EqualStringCount() {
		
	}
	
	// constructor
	public EqualStringCount(String currentWord) {
		this.currentWord = currentWord;
		// counter starts at one because i think it should.....
		this.counter = 1;
	}
	
	public int count() {
		return this.counter;
	}
	
	public String getString() {
		return currentWord;
	}
	
	// check if the word is equal to the currentWord, an equal string 
	// increments the count and returns true. Not equal will return false
	public Boolean checkWord(String equivWord) {
		if(equivWord.equals(currentWord)) {
			increment();
			return true;
		}else {
			return false;
		}
	}
	
	private void increment() {
		this.counter++;
	}

	@Override
	public int compareTo(EqualStringCount another) {
		return 0;
	}

	@Override
	public int compare(EqualStringCount lhs, EqualStringCount rhs) {
		return  rhs.count() - lhs.count();
	}
}
