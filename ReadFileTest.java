import static org.junit.Assert.*;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReadFileTest {

    String filePathOutsideRange = "test_outside_range.txt";
    String filePathNonInteger = "test_non_integer.txt";
    String filePathMissingText= "test_missing_text.txt";
    String filePathMissingNumber= "test_missing_number.txt";
    @Test
    public void testOutsideRange() {
        Set<Sentence> result = Analyzer.readFile(filePathOutsideRange);
        assertEquals(5, result.size());
    }

    @Test
    public void testNonInteger() {
        Set<Sentence> result = Analyzer.readFile(filePathNonInteger);
        assertEquals(5, result.size());
    }

    @Test
    public void testMissingNumber() {
        Set<Sentence> result = Analyzer.readFile(filePathMissingNumber);
        assertEquals(5, result.size());
    }

    @Test
    public void testMissingText() {
        Set<Sentence> result = Analyzer.readFile(filePathMissingText);
        assertEquals(5, result.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFile() {
        Set<Sentence> result = Analyzer.readFile(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFile() {
        Set<Sentence> result = Analyzer.readFile("InvalidFile.txt");
    }


}
