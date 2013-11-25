package nl.mprog.evilspacemonsterhangman.models;

import java.util.Comparator;

public class EqualStringCount implements Comparator<EqualStringCount>, Comparable<EqualStringCount> {
	/*
	 * simple class that counts equal strings
	 * when another string is inserted 
	 * the checkWord() method returns false so a new
	 * object can be made
	 */
	private int counter;
	private String currentWord;
	
	// i have no idea why i would need this for a comparator...
	// it actually works....
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
	
	// check if the word is equal to the currentWord
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compare(EqualStringCount lhs, EqualStringCount rhs) {
		// TODO Auto-generated method stub
		return  rhs.count() - lhs.count();
	}
}
