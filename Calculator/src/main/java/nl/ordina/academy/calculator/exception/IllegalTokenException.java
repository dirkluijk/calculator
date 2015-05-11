package nl.ordina.academy.calculator.exception;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class IllegalTokenException extends CalculatorException {
    public IllegalTokenException(String message) {
        super(message);
    }

    public IllegalTokenException(String s, Throwable e) {
        super(s, e);
    }
}
