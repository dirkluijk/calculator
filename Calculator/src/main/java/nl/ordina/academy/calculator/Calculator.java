package nl.ordina.academy.calculator;

import nl.ordina.academy.calculator.exception.*;

import java.math.BigDecimal;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class Calculator {

    private MathTokenizer mathTokenizer;
    private RPNConverter converter;
    private RPNInterpreter interpreter;

    public Calculator(MathTokenizer mathTokenizer, RPNConverter converter, RPNInterpreter interpreter) {
        this.mathTokenizer = mathTokenizer;
        this.converter = converter;
        this.interpreter = interpreter;
    }

    public Calculator() {
        this(new MathTokenizer(), new RPNConverter(), new RPNInterpreter());
    }

    public BigDecimal calculate(String input) throws CalculatorException {

        if (input.trim().isEmpty()) {
            throw new EmptyInputException("Input may not be empty.");
        }

        String[] tokens = mathTokenizer.tokenize(input);
        String[] tokensInRPN = converter.convertToRPN(tokens);

        return interpreter.interpretRPN(tokensInRPN);
    }
}
