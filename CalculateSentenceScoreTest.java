import static org.junit.Assert.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CalculateSentenceScoreTest {

    @Test
    public void testSimple() {
        // manually construct a word-score map for input
        Map<String, Double> map = new HashMap<>();
        map.put("i", (double)0);
        map.put("like", (double)2);
        map.put("apples", (double)0);
        double actual = Analyzer.calculateSentenceScore(map, "I like apples .");
        assertEquals(0.666, actual, 0.001);
    }

    @Test
    public void testIgnoreNonLetter() {
        // manually construct a word-score map for input
        Map<String, Double> map = new HashMap<>();
        map.put("dogs", (double)1);
        map.put("are", (double)0);
        map.put("smart", (double)2);
        double actual = Analyzer.calculateSentenceScore(map, "dogs are ?smart");
        assertEquals(0.5, actual, 0.001);
    }

    @Test
    public void testNotInMap() {
        // manually construct a word-score map for input
        Map<String, Double> map = new HashMap<>();
        map.put("dogs", (double)1);
        map.put("are", (double)0);
        map.put("smart", (double)2);
        double actual = Analyzer.calculateSentenceScore(map, "dogs are funny");
        assertEquals(0.333, actual, 0.001);
    }

    // test if uppercase input token is converted to lowercase before looking it up in the map
    @Test
    public void testInputUppercase() {
        // manually construct a word-score map for input
        Map<String, Double> map = new HashMap<>();
        map.put("dogs", (double)1);
        map.put("are", (double)0);
        map.put("smart", (double)2);
        double actual = Analyzer.calculateSentenceScore(map, "Dogs are funny");
        assertEquals(0.333, actual, 0.001);
    }

    // Test error handling
    @Test
    public void testNullMap() {
        double actual = Analyzer.calculateSentenceScore(null, "Dogs are funny");
        assertEquals(0.0, actual, 0.001);
    }

    @Test
    public void testEmptyMap() {
        Map<String, Double> map = new HashMap<>();
        double actual = Analyzer.calculateSentenceScore(map, "Dogs are funny");
        assertEquals(0.0, actual, 0.001);
    }

    @Test
    public void testNullSentence() {
        Map<String, Double> map = new HashMap<>();
        map.put("dogs", (double)1);
        map.put("are", (double)0);
        map.put("smart", (double)2);
        double actual = Analyzer.calculateSentenceScore(map, null);
        assertEquals(0.0, actual, 0.001);
    }
    @Test
    public void testEmptySentence() {
        Map<String, Double> map = new HashMap<>();
        map.put("dogs", (double)1);
        map.put("are", (double)0);
        map.put("smart", (double)2);
        double actual = Analyzer.calculateSentenceScore(map, "");
        assertEquals(0.0, actual, 0.001);
    }








}
