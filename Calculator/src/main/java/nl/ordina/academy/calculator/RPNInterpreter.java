package nl.ordina.academy.calculator;

import nl.ordina.academy.calculator.exception.DivisionByZeroException;
import nl.ordina.academy.calculator.exception.IllegalTokenException;
import nl.ordina.academy.calculator.exception.MissingTokenException;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class RPNInterpreter {

    public static final double NIHIL = 0.00001;

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
    public double interpretRPN(String[] tokens) throws MissingTokenException, IllegalTokenException, DivisionByZeroException {

        if (tokens.length == 0) {
            return 0;
        }

        double returnValue;

        Stack<String> stack = new Stack<>();

        for(String t : tokens){
            if(!isOperator(t)){
                stack.push(t);
            } else {
                double a, b;

                try {
                    a = Double.valueOf(stack.pop());
                    b = Double.valueOf(stack.pop());
                } catch (NumberFormatException e) {
                    throw new IllegalTokenException("Expected a valid token.");
                } catch (EmptyStackException e) {
                    throw new MissingTokenException("Missing a numeric token.");
                }

                Operator operator = findOperator(t);

                switch(operator){
                    case SUM:
                        stack.push(String.valueOf(a+b));
                        break;
                    case SUBTRACT:
                        stack.push(String.valueOf(b-a));
                        break;
                    case MULTIPLY:
                        stack.push(String.valueOf(a*b));
                        break;
                    case DIVIDE:
                        if (a < NIHIL) {
                            throw new DivisionByZeroException("Cannot devide by zero.");
                        }
                        stack.push(String.valueOf(b/a));
                        break;
                }
            }
        }

        try {
            returnValue = Double.valueOf(stack.pop());
        } catch (NumberFormatException e) {
            throw new IllegalTokenException("Invalid token.", e);
        }

        if (!stack.isEmpty()) {
            throw new MissingTokenException("Missing an operator.");
        }

        return returnValue;

    }

    private boolean isOperator(String token) {
        return findOperator(token) != null;
    }

    private Operator findOperator(String token) {
        for (Operator o : Operator.values()) {
            if (token.equals(o.getCharacter()))
                return o;
        }

        return null;
    }
}
