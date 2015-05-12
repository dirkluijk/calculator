package nl.ordina.academy.calculator.exception;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public abstract class CalculatorException extends Exception {
    public CalculatorException(String message) {
        super(message);
    }

    public CalculatorException(String s, Throwable e) {
        super(s, e);
    }
}
