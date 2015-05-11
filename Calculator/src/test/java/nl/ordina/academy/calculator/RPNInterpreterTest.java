package nl.ordina.academy.calculator;

import nl.ordina.academy.calculator.exception.DivisionByZeroException;
import nl.ordina.academy.calculator.exception.IllegalTokenException;
import nl.ordina.academy.calculator.exception.MissingTokenException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class RPNInterpreterTest {

    private static final double ACCURACY = 0.00001;
    private RPNInterpreter interpreter;

    @Before
    public void setUp() throws Exception {
        interpreter = new RPNInterpreter();
    }

    @Test
    public void testEmpty() throws Exception {
        double result = interpreter.interpretRPN(new String[]{});
        assertEquals(0, result, ACCURACY);
    }

    @Test
    public void testSingleToken() throws Exception {
        double result = interpreter.interpretRPN(new String[]{"99"});
        assertEquals(99, result, ACCURACY);
    }

    @Test
    public void testWithOperations() throws Exception {
        double result = interpreter.interpretRPN(new String[]{
                "99",
                "2",
                "+"
        });

        assertEquals(101, result, ACCURACY);
    }

    @Test
    public void testAdvancedExamples() throws Exception {
        double result1 = interpreter.interpretRPN(new String[]{
                "4", "2", "5", "*", "+", "1", "3", "2", "*", "+", "/"
        });

        double result2 = interpreter.interpretRPN(new String[]{
                "2", "5", "*", "6", "+", "3", "2", "*", "1", "+", "/"
        });

        double result3 = interpreter.interpretRPN(new String[]{
                "2", "5", "*", "6", "+", "3", "2", "*", "1", "-", "/"
        });

        assertEquals(2, result1, ACCURACY);
        assertEquals(16d / 7d, result2, ACCURACY);
        assertEquals(16d / 5d, result3, ACCURACY);
    }

    @Test(expected = MissingTokenException.class)
    public void testMissingNumber() throws Exception {
        interpreter.interpretRPN(new String[]{
                "2", "3", "*", "/"
        });
    }

    @Test(expected = MissingTokenException.class)
    public void testMissingOperator() throws Exception {
        interpreter.interpretRPN(new String[]{
                "2", "3"
        });

    }

    @Test(expected = IllegalTokenException.class)
    public void testEmptyToken() throws Exception {
        interpreter.interpretRPN(new String[]{
                "2", "3", ""
        });
    }

    @Test(expected = IllegalTokenException.class)
    public void testIllegalToken() throws Exception {
        interpreter.interpretRPN(new String[]{
                "2", "a", "+"
        });
    }

    @Test
    public void testDifficultNumbers() throws Exception {
        double result1 = interpreter.interpretRPN(new String[]{
                "3.2", "2.4", "+"
        });

        double result2 = interpreter.interpretRPN(new String[]{
                "-3.2", "2.4", "-"
        });

        assertEquals(3.2d + 2.4d, result1, ACCURACY);
        assertEquals(-3.2d - 2.4d, result2, ACCURACY);
    }

    @Test
    public void testDifficultOperations() throws Exception {
        double result1 = interpreter.interpretRPN(new String[]{
                "+5", "-6", "*"
        });

        double result2 = interpreter.interpretRPN(new String[]{
                "-3.2", "-2.4", "*"
        });

        assertEquals(5.0d * -6.0d, result1, ACCURACY);
        assertEquals(-3.2d * -2.4d, result2, ACCURACY);
    }

    @Test(expected = DivisionByZeroException.class)
    public void testDivisionByZero() throws Exception {
        interpreter.interpretRPN(new String[]{
                "2", "1", "1", "-", "/"
        });
    }
}