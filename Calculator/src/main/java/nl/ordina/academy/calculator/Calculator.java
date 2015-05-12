package nl.ordina.academy.calculator;

import nl.ordina.academy.calculator.exception.*;

import java.math.BigDecimal;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class Calculator {

    private Tokenizer tokenizer;
    private RPNConverter converter;
    private RPNInterpreter interpreter;

    public Calculator(Tokenizer tokenizer, RPNConverter converter, RPNInterpreter interpreter) {
        this.tokenizer = tokenizer;
        this.converter = converter;
        this.interpreter = interpreter;
    }

    public Calculator() {
        this(new Tokenizer(), new RPNConverter(), new RPNInterpreter());
    }

    public BigDecimal calculate(String input) throws CalculatorException {

        if (input.isEmpty()) {
            throw new EmptyInputException("Input may not be empty.");
        }

        String[] tokens = tokenizer.tokenize(input);
        String[] rpn = converter.convertToRPN(tokens);

        return interpreter.interpretRPN(rpn);
    }
}
