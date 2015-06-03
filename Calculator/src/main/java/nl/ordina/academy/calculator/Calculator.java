package nl.ordina.academy.calculator;

import java.math.BigDecimal;
import java.util.List;

import nl.ordina.academy.calculator.exception.CalculatorException;
import nl.ordina.academy.calculator.exception.EmptyInputException;

/**
 * Calculator used to do mathematical operations.
 */
public class Calculator {
    private MathTokenizer tokenizer;
    private RPNConverter converter;
    private RPNInterpreter interpreter;

    /**
     * Constructor creating an instance with an specific tokenizer, RPN converter and interpreter.
     * @param tokenizer {@link MathTokenizer} specific tokeneizer to format the input
     * @param converter {@link RPNConverter} used to convert input to RPN notation for enhanced calculations
     * @param interpreter {@link RPNInterpreter} used to do the RPN calculations
     */
    public Calculator(MathTokenizer tokenizer, RPNConverter converter, RPNInterpreter interpreter) {
        this.tokenizer = tokenizer;
        this.converter = converter;
        this.interpreter = interpreter;
    }

    /**
     * Default constructor which init and use default {@link MathTokenizer}, {@link RPNConverter} and {@link RPNInterpreter} classes.
     */
    public Calculator() {
        this(new MathTokenizer(), new RPNConverter(), new RPNInterpreter());
    }

    /**
     * Entry point of the calculator to do the math operations.
     * @param input {@link String}
     * @return the result of the calculation is returned as {@link BigDecimal}
     * @see BigDecimal
     * @throws CalculatorException {@link CalculatorException} when any exception occur
     */
    public BigDecimal calculate(String input) throws CalculatorException {

        if (input.trim().isEmpty()) {
            throw new EmptyInputException("Input may not be empty.");
        }

        List<String> tokens = tokenizer.tokenize(input);
        List<String> rpn = converter.convertToRPN(tokens);

        return interpreter.interpretRPN(rpn);
    }
}