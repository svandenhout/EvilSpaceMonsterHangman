package nl.mprog.evilspacemonsterhangman.models;

public class EqualStringCount {
	/*
	 * simple class that counts equal strings
	 * when another string is inserted 
	 * the checkWord() method returns false so a new
	 * object can be made
	 */
	private int counter;
	private String currentWord;
	
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
}