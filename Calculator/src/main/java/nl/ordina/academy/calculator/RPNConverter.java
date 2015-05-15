package nl.ordina.academy.calculator;

import nl.ordina.academy.calculator.exception.CalculatorException;
import nl.ordina.academy.calculator.exception.IllegalTokenException;
import nl.ordina.academy.calculator.exception.MissingParenthesisException;
import nl.ordina.academy.calculator.exception.MissingTokenException;

import java.util.*;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class RPNConverter {

    private List<String> output;
    private Stack<Operator> operatorStack;

    /**
     * Rearranges an array of mathematical tokens into RPN (Reverse Polish Notation).
     *
     * @param tokens
     * @return
     * @throws CalculatorException
     */
    public String[] convertToRPN(String[] tokens) throws CalculatorException {
        output = new ArrayList<>();
        operatorStack = new Stack<>();

        trimTokens(tokens);

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            String previousToken = i == 0 ? null : tokens[i - 1];

            shuntingYard(token, previousToken);
        }

        while (!operatorStack.empty()) {
            if (operatorStack.peek() == Operator.LEFT_PARENTHESIS) {
                throw new MissingParenthesisException("Missing a closing parenthesis.");
            }

            output.add(operatorStack.pop().toString());
        }

        return output.toArray(new String[output.size()]);

    }

    private void shuntingYard(String token, String previousToken) throws CalculatorException {
        token = token.trim();

        if (token.isEmpty()) {
            return;
        }

        if (isValidNumber(token) && isValidNumber(previousToken)) {
            throw new MissingTokenException("Missing an operator between two numbers.");
        }

        if (isValidNumber(token)) {
            if (Operator.isRightParenthesis(previousToken)) {
                putOperatorOnStack("*");
            }

            output.add(token);
        } else if (Operator.isLeftParenthesis(token)) {
            if (previousToken != null && (isValidNumber(previousToken) || Operator.isRightParenthesis(previousToken))) {
                putOperatorOnStack("*");
            }

            pushLeftParenthesisOnOperatorStack(token);
        } else if (Operator.isRightParenthesis(token)) {
            popFromOperatorStackUntilLeftParenthesis();
        } else if (Operator.isOperator(token)) {
            putOperatorOnStack(token);
        } else {
            throw new IllegalTokenException("Invalid token: " + token);
        }
    }

    private void pushLeftParenthesisOnOperatorStack(String token) {
        operatorStack.push(Operator.getOperator(token));
    }

    private void putOperatorOnStack(String token) {
        Operator currentOperator = Operator.getOperator(token);

        assert currentOperator != null;
        while (!operatorStack.empty() && nextOperatorHasPrecedenceOver(currentOperator)) {
            output.add(operatorStack.pop().toString());
        }

        operatorStack.push(currentOperator);
    }

    private boolean nextOperatorHasPrecedenceOver(Operator currentOperator) {
        Operator nextOperator = operatorStack.peek();
        return currentOperator.getPrecedence() <= nextOperator.getPrecedence();
    }

    private void popFromOperatorStackUntilLeftParenthesis() throws MissingParenthesisException {
        try {
            while (operatorStack.peek() != Operator.LEFT_PARENTHESIS) {
                output.add(operatorStack.pop().toString());
            }

            operatorStack.pop();
        } catch (EmptyStackException e) {
            throw new MissingParenthesisException("Missing a opening parenthesis.", e);
        }
    }

    private void trimTokens(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = tokens[i].trim();
        }
    }

    private boolean isValidNumber(String token) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Double.parseDouble(token);
        } catch (NumberFormatException|NullPointerException e) {
            return false;
        }

        return true;
    }
}
