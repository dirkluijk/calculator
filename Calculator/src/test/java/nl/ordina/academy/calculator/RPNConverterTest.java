package nl.ordina.academy.calculator;

import nl.ordina.academy.calculator.exception.IllegalTokenException;
import nl.ordina.academy.calculator.exception.MissingParenthesisException;
import nl.ordina.academy.calculator.exception.MissingTokenException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class RPNConverterTest {

    private RPNConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new RPNConverter();
    }

    @Test
    public void testEmpty() throws Exception {
        String[] result = converter.convertToRPN(new String[] {""});
        assertEquals(0, result.length);
    }



    @Test
    public void testNumber() throws Exception {
        String[] result = converter.convertToRPN(new String[] {"4"});
        assertEquals("4", result[0]);
    }

    @Test
    public void testWithSpaces() throws Exception {
        String[] result1 = converter.convertToRPN(new String[] {" 5 "});
        String[] result2 = converter.convertToRPN(new String[] {" ", "5", " "});

        assertEquals("5", result1[0]);
        assertEquals("5", result2[0]);
    }

    @Test
    public void testSimpleOperations() throws Exception {
        String[] result = converter.convertToRPN(new String[] {"5", "+", "2"});
        assertEquals("5", result[0]);
        assertEquals("2", result[1]);
        assertEquals("+", result[2]);
    }

    @Test
    public void testMultipleOperations() throws Exception {
        String[] result1 = converter.convertToRPN(new String[] {"5", "+", "2", "-", "3"});
        String[] result2 = converter.convertToRPN(new String[] {"5", "*", "2", "/", "3"});

        assertEquals("5", result1[0]);
        assertEquals("2", result1[1]);
        assertEquals("+", result1[2]);
        assertEquals("3", result1[3]);
        assertEquals("-", result1[4]);

        assertEquals("5", result2[0]);
        assertEquals("2", result2[1]);
        assertEquals("*", result2[2]);
        assertEquals("3", result2[3]);
        assertEquals("/", result2[4]);
    }

    @Test
    public void testCombinationOfOperators() throws Exception {
        String[] result = converter.convertToRPN(new String[] {"5", "+", "2", "*", "3"});
        assertEquals("5", result[0]);
        assertEquals("2", result[1]);
        assertEquals("3", result[2]);
        assertEquals("*", result[3]);
        assertEquals("+", result[4]);
    }

    @Test
    public void testWithMultipleParentheses() throws Exception {
        String[] result = converter.convertToRPN(new String[] {"(", "2", "+", "1", ")", "(", "4", "+", "1", ")"});
        assertEquals("2", result[0]);
        assertEquals("1", result[1]);
        assertEquals("+", result[2]);
        assertEquals("4", result[3]);
        assertEquals("1", result[4]);
        assertEquals("+", result[5]);
        assertEquals("*", result[6]);
    }

    @Test(expected = MissingParenthesisException.class)
    public void testMissingClosingParenthesis() throws Exception {
        converter.convertToRPN(new String[]{"(", "2", "+", "1", ")", "(", "4", "+", "1"});
    }

    @Test(expected = MissingParenthesisException.class)
    public void testMissingOpeningParenthesis() throws Exception {
        converter.convertToRPN(new String[]{"(", "2", "+", "1", ")", "4", "+", "1", ")"});
    }

    @Test(expected = MissingTokenException.class)
    public void testSpaceBetweenNumbers() throws Exception {
        converter.convertToRPN(new String[] {"9", "8"});
    }

    @Test(expected = IllegalTokenException.class)
    public void testSpaceBetweenNumbersInToken() throws Exception {
        converter.convertToRPN(new String[] {"9 8", "+", "8"});
    }

    @Test
    public void testMissingMultiplicationSigns() throws Exception {
        String[] result1 = converter.convertToRPN(new String[] {"9", "(", "8", ")"});
        String[] result2 = converter.convertToRPN(new String[] {"(", "9", ")", "8"});

        assertEquals("9", result1[0]);
        assertEquals("8", result1[1]);
        assertEquals("*", result1[2]);

        assertEquals("9", result2[0]);
        assertEquals("8", result2[1]);
        assertEquals("*", result2[2]);
    }

    @Test(expected = IllegalTokenException.class)
    public void testIllegalChars() throws Exception {
        converter.convertToRPN(new String[] {"9", "*", "a"});
    }

    @Test
    public void testWikiExample() throws Exception {
        String[] strings = converter.convertToRPN(new String[]{"3", "+", "4", "*", "2", "/", "(", "1", "-", "5", ")"});
        // 3 4 2 * 1 5 - / +
    }
}