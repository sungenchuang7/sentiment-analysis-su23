import static org.junit.Assert.*;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CalculateWordScoresTest {

    @Test
    public void testNullSet() {
        Map<String, Double> actual = Analyzer.calculateWordScores(null);
        assertEquals(0, actual.size());
    }

    @Test
    public void testEmptySet() {
        Set<Sentence> sentences = new HashSet<>();
        Map<String, Double> actual = Analyzer.calculateWordScores(sentences);
        assertEquals(0, actual.size());
    }

    @Test
    public void testSingleOccurrence() {
        // manually construct a set for input
        Set<Sentence> sentences = new HashSet<>();
        Sentence s1 = new Sentence(2, "I like cake and could eat cake all day .");
        Sentence s2 = new Sentence(1, "I hope the dog does not eat my cake .");
        sentences.add(s1);
        sentences.add(s2);

        Map<String, Double> actual = Analyzer.calculateWordScores(sentences);
        assertEquals((double) 1.0, actual.get("dog"), (double) 0.001);
    }

    @Test
    public void testMultipleOccurrences() {
        // manually construct a set for input
        Set<Sentence> sentences = new HashSet<>();
        Sentence s1 = new Sentence(2, "I like cake and could eat cake all day .");
        Sentence s2 = new Sentence(1, "I hope the dog does not eat my cake .");
        sentences.add(s1);
        sentences.add(s2);

        Map<String, Double> actual = Analyzer.calculateWordScores(sentences);
        assertEquals((double) 1.5, actual.get("eat"), (double) 0.001);
    }

    @Test
    public void testComplex() {
        // manually construct a set for input
        Set<Sentence> sentences = new HashSet<>();
        Sentence s1 = new Sentence(2, "I like cake and could eat cake all day .");
        Sentence s2 = new Sentence(1, "I hope the dog does not eat my cake .");
        sentences.add(s1);
        sentences.add(s2);

        Map<String, Double> actual = Analyzer.calculateWordScores(sentences);
        assertEquals((double) 1.667, actual.get("cake"), (double) 0.001);
    }

    @Test
    public void testCaseInsensitivity() {
        // manually construct a set for input
        Set<Sentence> sentences = new HashSet<>();
        Sentence s1 = new Sentence(2, "I like cake and could eat cake all day .");
        Sentence s2 = new Sentence(1, "I hope the dog does not eat my cake .");
        Sentence s3 = new Sentence(2, "It 's a lot of fun to learn about data structures .");
        sentences.add(s1);
        sentences.add(s2);
        sentences.add(s3);

        Map<String, Double> actual = Analyzer.calculateWordScores(sentences);
        assertFalse(actual.containsKey("It"));
        assertTrue(actual.containsKey("it"));
    }

    @Test
    public void testIgnoreNonLetter() {
        // manually construct a set for input
        Set<Sentence> sentences = new HashSet<>();
        Sentence s1 = new Sentence(2, "I like cake and could eat cake all day .");
        Sentence s2 = new Sentence(1, "I hope the dog does not eat my cake .");
        Sentence s3 = new Sentence(2, "It 's a lot of fun to learn about data structures .");
        sentences.add(s1);
        sentences.add(s2);
        sentences.add(s3);

        Map<String, Double> actual = Analyzer.calculateWordScores(sentences);
        assertFalse(actual.containsKey("'s"));
    }





}
