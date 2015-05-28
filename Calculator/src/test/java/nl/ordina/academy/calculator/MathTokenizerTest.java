package nl.ordina.academy.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class MathTokenizerTest {

    private MathTokenizer mathTokenizer;

    @Before
    public void setUp() throws Exception {
        mathTokenizer = new MathTokenizer();
    }

    @Test
    public void testEmpty() throws Exception {
        String[] tokens = mathTokenizer.tokenize("");
        assertEquals(0, tokens.length);
    }

    @Test
    public void testNumber() throws Exception {
        String[] tokens = mathTokenizer.tokenize("1");
        assertEquals("1", tokens[0]);
    }

    @Test
    public void testIgnoreSpaces() throws Exception {
        String[] tokens = mathTokenizer.tokenize("1 + 2");
        assertEquals("1", tokens[0]);
        assertEquals("+", tokens[1]);
        assertEquals("2", tokens[2]);
    }

    @Test
    public void testNumbers() throws Exception {
        String[] tokens1 = mathTokenizer.tokenize("-1.5*1");
        String[] tokens2 = mathTokenizer.tokenize("-.5/1");
        String[] tokens3 = mathTokenizer.tokenize("+.5+1");
        String[] tokens4 = mathTokenizer.tokenize("+5-1");

        assertEquals("-1.5", tokens1[0]);
        assertEquals("*", tokens1[1]);
        assertEquals("1", tokens1[2]);

        assertEquals("-.5", tokens2[0]);
        assertEquals("/", tokens2[1]);
        assertEquals("1", tokens2[2]);

        assertEquals("+.5", tokens3[0]);
        assertEquals("+", tokens3[1]);
        assertEquals("1", tokens3[2]);

        assertEquals("+5", tokens4[0]);
        assertEquals("-", tokens4[1]);
        assertEquals("1", tokens4[2]);
    }

    @Test
    public void testAdvanced() throws Exception {
        String[] tokens = mathTokenizer.tokenize("-.5/4.2*-1");

        assertEquals("-.5", tokens[0]);
        assertEquals("/", tokens[1]);
        assertEquals("4.2", tokens[2]);
        assertEquals("*", tokens[3]);
        assertEquals("-1", tokens[4]);
    }

    @Test
    public void testIssue1() throws Exception {
        String[] tokens = mathTokenizer.tokenize("(1+2*3)-4/5");

        assertEquals("(", tokens[0]);
        assertEquals("1", tokens[1]);
        assertEquals("+", tokens[2]);
        assertEquals("2", tokens[3]);
        assertEquals("*", tokens[4]);
        assertEquals("3", tokens[5]);
        assertEquals(")", tokens[6]);
        assertEquals("-", tokens[7]);
        assertEquals("4", tokens[8]);
        assertEquals("/", tokens[9]);
        assertEquals("5", tokens[10]);
    }

    @Test
    public void testFromMonkey() throws Exception {
        String[] tokens = mathTokenizer.tokenize("11.455 + -35.804");

        assertEquals("11.455", tokens[0]);
        assertEquals("+", tokens[1]);
        assertEquals("-35.804", tokens[2]);
    }

    @Test
    public void testAnotherFromMonkey() throws Exception {
        String[] tokens = mathTokenizer.tokenize("(-47.56)");

        assertEquals("(", tokens[0]);
        assertEquals("-47.56", tokens[1]);
        assertEquals(")", tokens[2]);
    }
}