package nl.ordina.academy.calculator;

import nl.ordina.academy.calculator.exception.EmptyInputException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class CalculatorTest {

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

        List<String> tokenizerOutput = Arrays.asList("bar");
        List<String> converterOutput = Arrays.asList("baz");
        BigDecimal interpreterOutput = new BigDecimal(3.2d);

        when(mathTokenizer.tokenize(anyString())).thenReturn(tokenizerOutput);
        when(converter.convertToRPN(anyListOf(String.class))).thenReturn(converterOutput);
        when(interpreter.interpretRPN(anyListOf(String.class))).thenReturn(interpreterOutput);

        // ACT
        BigDecimal result = calc.calculate(calcInput);

        // ASSERT
        verify(mathTokenizer).tokenize(calcInput);
        verify(converter).convertToRPN(tokenizerOutput);
        verify(interpreter).interpretRPN(converterOutput);
        assertEquals(interpreterOutput, result);
    }
}