package nl.ordina.academy.calculator.application;

import nl.ordina.academy.calculator.Calculator;
import nl.ordina.academy.calculator.exception.CalculatorException;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class CalculatorAppTest {

    private CalculatorApp app;
    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = mock(Calculator.class);
        app = new CalculatorApp(calculator);
    }

    @Test
    public void testRunWithRounding() throws Exception {

        // ARRANGE
        double output1 = -0.005;
        double output2 = -0.0005;
        double output3 = -5.000;

        when(calculator.calculate("1")).thenReturn(output1);
        when(calculator.calculate("2")).thenReturn(output2);
        when(calculator.calculate("3")).thenReturn(output3);

        // ACT
        String result1 = app.run("1");
        String result2 = app.run("2");
        String result3 = app.run("3");

        // ASSERT
        verify(calculator, times(3)).calculate(anyString());

        assertEquals("Het resultaat van 1 is: -0,005", result1);
        assertEquals("Het resultaat van 2 is: -0,001", result2);
        assertEquals("Het resultaat van 3 is: -5", result3);
    }

    @Test
    public void testAnError() throws Exception {
        when(calculator.calculate(anyString())).thenThrow(mock(CalculatorException.class));
        assertTrue(app.run("foo").contains("Het ging mis: "));
    }
}