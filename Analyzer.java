import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
 * Implement the four methods as described in the specification.
 * 
 * Do not change the method signatures!
 * Contact the instructor if you feel that a change is necessary.
 */

public class Analyzer {

	public Analyzer() {
		
	}
	
	public static Set<Sentence> readFile(String filename) {
		// implement this method in Part 1
		if (filename == null) {
			throw new IllegalArgumentException("Filename cannot be null.");
		}
		Set<Sentence> ret = new HashSet<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println("original line: " + line);
				line = line.trim();
				System.out.println("trimmed line: " + line);
				String[] parts = line.split("\\s+", 2);

				for (int i = 0; i < parts.length; i++) {
					System.out.println("parts[" + i + "]: " + parts[i]);
				}

				if (parts.length == 2) {
					try {

						int score = Integer.parseInt(parts[0]);
						// if score is outside the range, skip the line
						if (score < -2 || score > 2) {
							continue;
						}
						String text = parts[1];
						System.out.println("score: " + score);
						System.out.println("text: " + text);
						Sentence sentence = new Sentence(score, text);
						ret.add(sentence);
					} catch (NumberFormatException e) {
						// if score isn't an int or isn't a digit
						// Skip to the next line
						continue;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("File cannot be opened for reading: " + filename, e);
		}

		return ret;
	}



	public static Map<String, Double> calculateWordScores(Set<Sentence> sentences) {
		// implement this method in Part 2

		// corner cases
		Map<String, Double> ret = new HashMap<>();
		if (sentences == null) {
			return ret;
		}
		if (sentences.size() == 0) {
			return ret;
		}

		// wordOccurrences maps each word to the total number of its occurrences in all sentences
		Map<String, Integer> wordOccurrences = new HashMap<>();
		// wordCumulativeScore maps each word to its cumulative score out of all sentences
		Map<String, Integer> wordCumulativeScore = new HashMap<>();

		for (Sentence sentence : sentences) {
			int score = sentence.getScore();
			System.out.println("score: " + score);
			System.out.println("sentence.getText(): " + sentence.getText());
			// trim first before split to prevent IndexOutOfBoundException
			String[] tokens = sentence.getText().trim().split("\\s+");
			for (String token : tokens) {
				if (token.length() == 0 || !(Character.isLetter(token.charAt(0)))) {
					continue;
				}
				token = token.toLowerCase();
				if (wordOccurrences.containsKey(token)) {
					wordOccurrences.put(token, wordOccurrences.get(token) + 1);
				} else {
					wordOccurrences.put(token, 1);
				}
				if (wordCumulativeScore.containsKey(token)) {
					wordCumulativeScore.put(token, wordCumulativeScore.get(token) + score);
				} else {
					wordCumulativeScore.put(token, score);
				}
			}
		}

		for (Map.Entry<String, Integer> entry : wordOccurrences.entrySet()) {
			String word = entry.getKey();
			Integer cumulativeOccurrences = entry.getValue();
			Integer cumulativeScore = wordCumulativeScore.get(word);
			ret.put(word, (double) (cumulativeScore) / (double) (cumulativeOccurrences));
		}

		return ret;

		// If the input Set of Sentences is non-null and non-empty, then the method should ignore (and not consider for calculation) any Sentence objects in the Set for which necessary data is missing or invalid.
		// It is up to you to determine what “missing or invalid” means! Refer back to the issues from discussions and assignments on defensive programming and the situations that you needed to handle.

	}
	

	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
				
		// implement this method in Part 3

		// Error handling
		if (wordScores == null) {
			return 0;
		}
		if (wordScores.size() == 0) {
			return 0;
		}
		if (sentence == null) {
			return 0;
		}
		if (sentence.equals("")) {
			return 0;
		}

		// split the sentence
		String[] tokens = sentence.split("\\s+");

		// counter
		int numWords = 0;
		int sentenceScore = 0;

		for (String token : tokens) {
			token = token.toLowerCase();

			// if a token doesn't start with a letter, it's not a word, skip it
			if (!Character.isLetter(token.charAt(0))) {
				continue;
			}

			// reaching here means the token is a valid word
			numWords++;

			if (!wordScores.containsKey(token)) {
				// if the token is a word not included in the map
				// give it a score of 0
				sentenceScore += 0;
			} else {
				// if the token is a word in the map
				// update sentence score by the weight of the word
				sentenceScore += wordScores.get(token);
			}

		}

		return (double) sentenceScore / numWords;
		
	}
	
	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Please provide a filename as a command-line argument.");
			return;
		}

		String filePath = args[0];

		Scanner scanner = new Scanner(System.in);
		// implement this method in Part 4


//		// Prompt the user to enter their name
//		System.out.print("Enter input file path: ");
//
//		// Read the input provided by the user
//		String filePath = scanner.nextLine();

		try {

			// pass the name of the input file to get the Set of Sentence objects
			Set<Sentence> setOfSentences = readFile(filePath);
			Map<String, Double> mapOfWordsAndScores  = calculateWordScores(setOfSentences);

			String input;  // Variable to store user input

			while (true) {
				// Prompt the user to enter input
				System.out.print("Enter a sentence: ");

				// Read the input provided by the user
				input = scanner.nextLine();

				input = input.trim();

				if (input.equals("quit")) {
					break;
				}

				double sentenceScore = calculateSentenceScore(mapOfWordsAndScores, input);
				// Process the input
				System.out.println("The sentiment score of your input is: " + sentenceScore);
			}

		} catch (IllegalArgumentException e) {
			System.out.println("bad input file");
		}


		// Close the scanner
		scanner.close();
		
	}
	
	// don't forget to write your JUnit tests for Part 5!

}
