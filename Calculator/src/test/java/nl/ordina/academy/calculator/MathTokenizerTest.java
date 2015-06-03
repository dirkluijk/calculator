package nl.ordina.academy.calculator;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        List<String> tokens = mathTokenizer.tokenize("");
        assertEquals(0, tokens.size());
    }

    @Test
    public void testNumber() throws Exception {
        List<String> tokens = mathTokenizer.tokenize("1");
        assertEquals("1", tokens.get(0));
    }

    @Test
    public void testIgnoreSpaces() throws Exception {
        List<String> tokens = mathTokenizer.tokenize("1 + 2");
        assertEquals("1", tokens.get(0));
        assertEquals("+", tokens.get(1));
        assertEquals("2", tokens.get(2));
    }

    @Test
    public void testNumbers() throws Exception {
        List<String> tokens1 = mathTokenizer.tokenize("-1.5*1");
        List<String> tokens2 = mathTokenizer.tokenize("-.5/1");
        List<String> tokens3 = mathTokenizer.tokenize("+.5+1");
        List<String> tokens4 = mathTokenizer.tokenize("+5-1");

        assertEquals("-1.5", tokens1.get(0));
        assertEquals("*", tokens1.get(1));
        assertEquals("1", tokens1.get(2));

        assertEquals("-.5", tokens2.get(0));
        assertEquals("/", tokens2.get(1));
        assertEquals("1", tokens2.get(2));

        assertEquals("+.5", tokens3.get(0));
        assertEquals("+", tokens3.get(1));
        assertEquals("1", tokens3.get(2));

        assertEquals("+5", tokens4.get(0));
        assertEquals("-", tokens4.get(1));
        assertEquals("1", tokens4.get(2));
    }

    @Test
    public void testAdvanced() throws Exception {
        List<String> tokens = mathTokenizer.tokenize("-.5/4.2*-1");

        assertEquals("-.5", tokens.get(0));
        assertEquals("/", tokens.get(1));
        assertEquals("4.2", tokens.get(2));
        assertEquals("*", tokens.get(3));
        assertEquals("-1", tokens.get(4));
    }

    @Test
    public void testIssue1() throws Exception {
        List<String> tokens = mathTokenizer.tokenize("(1+2*3)-4/5");

        assertEquals("(", tokens.get(0));
        assertEquals("1", tokens.get(1));
        assertEquals("+", tokens.get(2));
        assertEquals("2", tokens.get(3));
        assertEquals("*", tokens.get(4));
        assertEquals("3", tokens.get(5));
        assertEquals(")", tokens.get(6));
        assertEquals("-", tokens.get(7));
        assertEquals("4", tokens.get(8));
        assertEquals("/", tokens.get(9));
        assertEquals("5", tokens.get(10));
    }

    @Test
    public void testFromMonkey() throws Exception {
        List<String> tokens = mathTokenizer.tokenize("11.455 + -35.804");

        assertEquals("11.455", tokens.get(0));
        assertEquals("+", tokens.get(1));
        assertEquals("-35.804", tokens.get(2));
    }

    @Test
    public void testAnotherFromMonkey() throws Exception {
        List<String> tokens = mathTokenizer.tokenize("(-47.56)");

        assertEquals("(", tokens.get(0));
        assertEquals("-47.56", tokens.get(1));
        assertEquals(")", tokens.get(2));
    }
}