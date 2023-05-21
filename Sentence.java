import java.util.*;

/*
 * This class represents a single sentence in the input file.
 * You may add methods to this class but please do not modify 
 * or delete the instance variables, constructor, or getter methods.
 */

public class Sentence {
	
	private int score;
	private String text;
	
	public Sentence(int score, String text) {
		this.score = score;
		this.text = text;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getText() {
		return text;
	}
	
}
