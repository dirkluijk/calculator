package nl.ordina.academy.calculator;

import nl.ordina.academy.calculator.exception.EmptyInputException;
import org.junit.Before;
import org.junit.Test;

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
    private Tokenizer tokenizer;

    @Before
    public void setUp() throws Exception {
        tokenizer = mock(Tokenizer.class);
        converter = mock(RPNConverter.class);
        interpreter = mock(RPNInterpreter.class);

        calc = new Calculator(tokenizer, converter, interpreter);
    }

    @Test(expected = EmptyInputException.class)
    public void testEmptyInput() throws Exception {
        calc.calculate("");
    }

    @Test
    public void testWithLegalInput() throws Exception {

        // ARRANGE
        String calcInput = "foo";

        String[] tokenizerOutput = {"bar"};
        String[] converterOutput = {"baz"};
        double interpreterOutput = 3.2d;

        when(tokenizer.tokenize(anyString())).thenReturn(tokenizerOutput);
        when(converter.convertToRPN(any(String[].class))).thenReturn(converterOutput);
        when(interpreter.interpretRPN(any(String[].class))).thenReturn(interpreterOutput);

        // ACT
        double result = calc.calculate(calcInput);

        // ASSERT
        verify(tokenizer).tokenize(calcInput);
        verify(converter).convertToRPN(tokenizerOutput);
        verify(interpreter).interpretRPN(converterOutput);
        assertEquals(interpreterOutput, result, ACCURACY);
    }
}