package nl.ordina.academy.calculator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import nl.ordina.academy.calculator.exception.*;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
@RequestScoped
public class Calculator {

    @Inject
    Tokenizer tokenizer;

    @Inject
    RPNConverter converter;

    @Inject
    RPNInterpreter interpreter;

    public double calculate(String input) throws CalculatorException {

        if (input.isEmpty()) {
            throw new EmptyInputException("Input may not be empty.");
        }

        String[] tokens = tokenizer.tokenize(input);
        String[] rpn = converter.convertToRPN(tokens);

        return interpreter.interpretRPN(rpn);
    }
}
