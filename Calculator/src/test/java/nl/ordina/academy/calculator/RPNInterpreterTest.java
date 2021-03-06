package nl.ordina.academy.calculator;

import nl.ordina.academy.calculator.exception.DivisionByZeroException;
import nl.ordina.academy.calculator.exception.IllegalTokenException;
import nl.ordina.academy.calculator.exception.MissingTokenException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class RPNInterpreterTest {

    private RPNInterpreter interpreter;

    @Before
    public void setUp() throws Exception {
        interpreter = new RPNInterpreter();
    }

    @Test
    public void testEmpty() throws Exception {
        BigDecimal result = interpreter.interpretRPN(new ArrayList<>());
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testSingleToken() throws Exception {
        BigDecimal result = interpreter.interpretRPN(Arrays.asList("99"));
        assertEquals(new BigDecimal(99), result);
    }

    @Test
    public void testWithOperations() throws Exception {
        BigDecimal result = interpreter.interpretRPN(Arrays.asList(
                "99",
                "2",
                "+"
        ));

        assertEquals(new BigDecimal(101), result);
    }

    @Test
    public void testAdvancedExamples() throws Exception {
        BigDecimal result1 = interpreter.interpretRPN(Arrays.asList(
                "4", "2", "5", "*", "+", "1", "3", "2", "*", "+", "/"
        ));

        BigDecimal result2 = interpreter.interpretRPN(Arrays.asList(
                "2", "5", "*", "6", "+", "3", "2", "*", "1", "+", "/"
        ));

        BigDecimal result3 = interpreter.interpretRPN(Arrays.asList(
                "2", "5", "*", "6", "+", "3", "2", "*", "1", "-", "/"
        ));

        assertEquals(new BigDecimal("2"), result1);
        assertEquals(new BigDecimal("2.286"), result2);
        assertEquals(new BigDecimal("3.2"), result3);
    }

    @Test(expected = MissingTokenException.class)
    public void testMissingNumber() throws Exception {
        interpreter.interpretRPN(Arrays.asList(
                "2", "3", "*", "/"
        ));
    }

    @Test(expected = MissingTokenException.class)
    public void testMissingOperator() throws Exception {
        interpreter.interpretRPN(Arrays.asList(
                "2", "3"
        ));

    }

    @Test(expected = IllegalTokenException.class)
    public void testEmptyToken() throws Exception {
        interpreter.interpretRPN(Arrays.asList(
                "2", "3", ""
        ));
    }

    @Test(expected = IllegalTokenException.class)
    public void testIllegalToken() throws Exception {
        interpreter.interpretRPN(Arrays.asList(
                "2", "a", "+"
        ));
    }

    @Test
    public void testDifficultNumbers() throws Exception {
        BigDecimal result1 = interpreter.interpretRPN(Arrays.asList(
                "3.2", "2.4", "+"
        ));

        BigDecimal result2 = interpreter.interpretRPN(Arrays.asList(
                "-3.2", "2.4", "-"
        ));

        assertEquals(new BigDecimal("5.6"), result1);
        assertEquals(new BigDecimal("-5.6"), result2);
    }

    @Test
    public void testDifficultOperations() throws Exception {
        BigDecimal result1 = interpreter.interpretRPN(Arrays.asList(
                "+5", "-6", "*"
        ));

        BigDecimal result2 = interpreter.interpretRPN(Arrays.asList(
                "-3.2", "-2.4", "*"
        ));

        assertEquals(new BigDecimal("-30"), result1);
        assertEquals(new BigDecimal("7.68"), result2);
    }

    @Test(expected = DivisionByZeroException.class)
    public void testDivisionByZero() throws Exception {
        interpreter.interpretRPN(Arrays.asList(
                "2", "1", "1", "-", "/"
        ));
    }

    @Test
    public void testDivideByNegative() throws Exception {
        BigDecimal result1 = interpreter.interpretRPN(Arrays.asList(
                "1", "-2", "/"
        ));
        BigDecimal result2 = interpreter.interpretRPN(Arrays.asList(
                "-1", "2", "/"
        ));

        assertEquals(new BigDecimal("-.5"), result1);
        assertEquals(new BigDecimal("-.5"), result2);
    }
}