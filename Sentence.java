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

	@Override
	public int hashCode() {
		// to determine which bucket
		// score + hashcode of text
		int result = 31 + score;
		result = 31 * result + (text != null ? text.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// for comparing each element in the linked list in the bucket
		// score and text
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof Sentence)) {
			return false;
		}
		Sentence other = (Sentence) obj;
		return score == other.score && Objects.equals(text, other.text);
	}
}
