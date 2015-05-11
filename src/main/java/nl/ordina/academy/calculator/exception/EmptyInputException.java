package nl.ordina.academy.calculator.exception;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class EmptyInputException extends CalculatorException {

    public EmptyInputException(String message) {
        super(message);
    }
}
