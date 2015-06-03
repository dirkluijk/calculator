package nl.ordina.academy.calculator;

import nl.ordina.academy.calculator.exception.IllegalTokenException;
import nl.ordina.academy.calculator.exception.MissingParenthesisException;
import nl.ordina.academy.calculator.exception.MissingTokenException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
        List<String> result = converter.convertToRPN(Arrays.asList(""));
        assertEquals(0, result.size());
    }

    @Test
    public void testNumber() throws Exception {
        List<String> result = converter.convertToRPN(Arrays.asList("4"));
        assertEquals("4", result.get(0));
    }

    @Test
    public void testWithSpaces() throws Exception {
        List<String> result1 = converter.convertToRPN(Arrays.asList(" 5 "));
        List<String> result2 = converter.convertToRPN(Arrays.asList(" ", "5", " "));

        assertEquals("5", result1.get(0));
        assertEquals("5", result2.get(0));
    }

    @Test
    public void testSimpleOperations() throws Exception {
        List<String> result = converter.convertToRPN(Arrays.asList("5", "+", "2"));
        assertEquals("5", result.get(0));
        assertEquals("2", result.get(1));
        assertEquals("+", result.get(2));
    }

    @Test
    public void testMultipleOperations() throws Exception {
        List<String> result1 = converter.convertToRPN(Arrays.asList("5", "+", "2", "-", "3"));
        List<String> result2 = converter.convertToRPN(Arrays.asList("5", "*", "2", "/", "3"));

        assertEquals("5", result1.get(0));
        assertEquals("2", result1.get(1));
        assertEquals("+", result1.get(2));
        assertEquals("3", result1.get(3));
        assertEquals("-", result1.get(4));

        assertEquals("5", result2.get(0));
        assertEquals("2", result2.get(1));
        assertEquals("*", result2.get(2));
        assertEquals("3", result2.get(3));
        assertEquals("/", result2.get(4));
    }

    @Test
    public void testCombinationOfOperators() throws Exception {
        List<String> result = converter.convertToRPN(Arrays.asList("5", "+", "2", "*", "3"));
        assertEquals("5", result.get(0));
        assertEquals("2", result.get(1));
        assertEquals("3", result.get(2));
        assertEquals("*", result.get(3));
        assertEquals("+", result.get(4));
    }

    @Test
    public void testWithMultipleParentheses() throws Exception {
        List<String> result = converter.convertToRPN(Arrays.asList("(", "2", "+", "1", ")", "(", "4", "+", "1", ")"));
        assertEquals("2", result.get(0));
        assertEquals("1", result.get(1));
        assertEquals("+", result.get(2));
        assertEquals("4", result.get(3));
        assertEquals("1", result.get(4));
        assertEquals("+", result.get(5));
        assertEquals("*", result.get(6));
    }

    @Test(expected = MissingParenthesisException.class)
    public void testMissingClosingParenthesis() throws Exception {
        converter.convertToRPN(Arrays.asList("(", "2", "+", "1", ")", "(", "4", "+", "1"));
    }

    @Test(expected = MissingParenthesisException.class)
    public void testMissingOpeningParenthesis() throws Exception {
        converter.convertToRPN(Arrays.asList("(", "2", "+", "1", ")", "4", "+", "1", ")"));
    }

    @Test(expected = MissingTokenException.class)
    public void testSpaceBetweenNumbers() throws Exception {
        converter.convertToRPN(Arrays.asList("9", "8"));
    }

    @Test(expected = IllegalTokenException.class)
    public void testSpaceBetweenNumbersInToken() throws Exception {
        converter.convertToRPN(Arrays.asList("9 8", "+", "8"));
    }

    @Test
    public void testMissingMultiplicationSigns() throws Exception {
        List<String> result1 = converter.convertToRPN(Arrays.asList("9", "(", "8", ")"));
        List<String> result2 = converter.convertToRPN(Arrays.asList("(", "9", ")", "8"));

        assertEquals("9", result1.get(0));
        assertEquals("8", result1.get(1));
        assertEquals("*", result1.get(2));

        assertEquals("9", result2.get(0));
        assertEquals("8", result2.get(1));
        assertEquals("*", result2.get(2));
    }

    @Test(expected = IllegalTokenException.class)
    public void testIllegalChars() throws Exception {
        converter.convertToRPN(Arrays.asList("9", "*", "a"));
    }

    @Test
    public void testWikiExample() throws Exception {
        List<String> strings = converter.convertToRPN(Arrays.asList("3", "+", "4", "*", "2", "/", "(", "1", "-", "5", ")"));
        // 3 4 2 * 1 5 - / +
    }
}