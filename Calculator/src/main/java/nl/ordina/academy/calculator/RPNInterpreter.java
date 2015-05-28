package nl.ordina.academy.calculator;

import nl.ordina.academy.calculator.exception.DivisionByZeroException;
import nl.ordina.academy.calculator.exception.IllegalTokenException;
import nl.ordina.academy.calculator.exception.MissingTokenException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class RPNInterpreter {

    public static final BigDecimal NIHIL = new BigDecimal(0.00001);
    public static final MathContext MATH_CONTEXT = new MathContext(4, RoundingMode.HALF_UP);

    /**
     * Interpret a methematical expression in RPN (Reverse Polish Notation).
     *
     * Examples:
     * - {"3", "2", "+") => 5
     * - {"3", "2", "4", "*", "+") => 11
     *
     * @param tokens An array of mathematical tokens.
     * @return The calculated result.
     *
     * @throws MissingTokenException If an operator or a number is missing.
     * @throws IllegalTokenException If an illegal token was passed.
     */
    public BigDecimal interpretRPN(String[] tokens) throws MissingTokenException, IllegalTokenException, DivisionByZeroException {

        if (tokens.length == 0) {
            return BigDecimal.ZERO;
        }

        Stack<BigDecimal> stack = new Stack<>();

        for(String t : tokens) {
            if(!Operator.isOperator(t)){
                try {
                    stack.push(new BigDecimal(t, MATH_CONTEXT));
                } catch (NumberFormatException e) {
                    throw new IllegalTokenException("Expected a valid token.");
                }
            } else {
                BigDecimal a, b;

                try {
                    a = stack.pop();
                    b = stack.pop();
                } catch (EmptyStackException e) {
                    throw new MissingTokenException("Missing a numeric token.");
                }

                Operator operator = Operator.getOperator(t);

                switch(operator){
                    case SUM:
                        stack.push(b.add(a));
                        break;
                    case SUBTRACT:
                        stack.push(b.subtract(a));
                        break;
                    case MULTIPLY:
                        stack.push(b.multiply(a));
                        break;
                    case DIVIDE:
                    default:
                        if (a.abs().compareTo(NIHIL) == -1) {
                            throw new DivisionByZeroException("Cannot divide by zero.");
                        }
                        stack.push(b.divide(a, MATH_CONTEXT));
                        break;
                }
            }
        }

        BigDecimal returnValue;

        try {
            returnValue = stack.pop();
        } catch (NumberFormatException e) {
            throw new IllegalTokenException("Invalid token.", e);
        }

        if (!stack.isEmpty()) {
            throw new MissingTokenException("Missing an operator.");
        }

        return returnValue;

    }
}
