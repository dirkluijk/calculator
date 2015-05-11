package nl.ordina.academy.calculator.exception;

import nl.ordina.academy.calculator.exception.CalculatorException;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class MissingTokenException extends CalculatorException {
    public MissingTokenException(String message) {
        super(message);
    }
}
