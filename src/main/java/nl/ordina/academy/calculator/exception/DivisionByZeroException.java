package nl.ordina.academy.calculator.exception;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class DivisionByZeroException extends CalculatorException {

    public DivisionByZeroException(String message) {
        super(message);
    }
}
