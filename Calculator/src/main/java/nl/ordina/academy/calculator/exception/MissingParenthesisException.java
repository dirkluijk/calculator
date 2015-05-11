package nl.ordina.academy.calculator.exception;

import java.util.EmptyStackException;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class MissingParenthesisException extends CalculatorException {
    public MissingParenthesisException(String message) {
        super(message);
    }

    public MissingParenthesisException(String s, EmptyStackException e) {
        super(s, e);
    }
}
