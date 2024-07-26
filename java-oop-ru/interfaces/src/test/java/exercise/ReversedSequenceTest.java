package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversedSequenceTest {
    private static ReversedSequence sequence;
    @BeforeAll
    public static void beforeAll() {
        sequence = new ReversedSequence("ytrewq");
    }

    @Test
    public void testLength() {
        var actual = sequence.length();
        var expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    public void testCharAT() {
        var actual = sequence.charAt(1);
        var expected = 'w';
        assertEquals(expected, actual);
    }

    @Test
    public void testSubSequence() {
        var actual = sequence.subSequence(0, 2);
        var expected = "qw";
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        var actual = sequence.toString();
        var expected = "qwerty";
        assertEquals(expected, actual);
    }


}
