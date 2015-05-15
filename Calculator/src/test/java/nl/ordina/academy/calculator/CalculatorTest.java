package nl.ordina.academy.calculator;

import nl.ordina.academy.calculator.exception.EmptyInputException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class CalculatorTest {
    public static final double ACCURACY = 0.00001;

    private Calculator calc;

    private RPNInterpreter interpreter;
    private RPNConverter converter;
    private MathTokenizer mathTokenizer;

    @Before
    public void setUp() throws Exception {
        mathTokenizer = mock(MathTokenizer.class);
        converter = mock(RPNConverter.class);
        interpreter = mock(RPNInterpreter.class);

        calc = new Calculator(mathTokenizer, converter, interpreter);
    }

    @Test(expected = EmptyInputException.class)
    public void testEmptyInput() throws Exception {
        calc.calculate("");
    }

    @Test(expected = EmptyInputException.class)
    public void testSpaceOnlyInput() throws Exception {
        calc.calculate("  ");
    }

    @Test
    public void testWithLegalInput() throws Exception {

        // ARRANGE
        String calcInput = "foo";

        String[] tokenizerOutput = {"bar"};
        String[] converterOutput = {"baz"};
        BigDecimal interpreterOutput = new BigDecimal(3.2d);

        when(mathTokenizer.tokenize(anyString())).thenReturn(tokenizerOutput);
        when(converter.convertToRPN(any(String[].class))).thenReturn(converterOutput);
        when(interpreter.interpretRPN(any(String[].class))).thenReturn(interpreterOutput);

        // ACT
        BigDecimal result = calc.calculate(calcInput);

        // ASSERT
        verify(mathTokenizer).tokenize(calcInput);
        verify(converter).convertToRPN(tokenizerOutput);
        verify(interpreter).interpretRPN(converterOutput);
        assertEquals(interpreterOutput, result);
    }
}