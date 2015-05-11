package nl.ordina.academy.calculator;

import nl.ordina.academy.calculator.exception.IllegalTokenException;
import nl.ordina.academy.calculator.exception.MissingParenthesisException;
import nl.ordina.academy.calculator.exception.MissingTokenException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class RPNConverter {

    private List<String> output;
    private Stack<Operator> operatorStack;

    /**
     * Rearranges an array of mathematical tokens into RPN (Reverse Polish Notation).
     * @param tokens
     * @return
     * @throws MissingParenthesisException
     * @throws IllegalTokenException
     * @throws MissingTokenException
     */
    public String[] convertToRPN(String[] tokens) throws MissingParenthesisException, IllegalTokenException, MissingTokenException {
        output = new ArrayList<>();
        operatorStack = new Stack<>();

        tokens = handleSpaces(tokens);

        String previousToken = null;

        for (String token : tokens) {
            if (isEmptySpace(token)) {
                continue;
            } else if (isNumber(token)) {
                if (isNumber(previousToken)) {
                    throw new MissingTokenException("Missing an operator between two numbers.");
                }

                if (previousToken != null && isRightParenthesis(previousToken)) {
                    handleOperator("*");
                }

                output.add(token);
            } else if (isLeftParenthesis(token)) {
                if (previousToken != null && (isNumber(previousToken) || isRightParenthesis(previousToken))) {
                    handleOperator("*");
                }

                operatorStack.push(findOperator(token));
            } else if (isRightParenthesis(token)) {
                handleRightParenthesis();

            } else if (isOperator(token)) {
                handleOperator(token);

            } else {
                throw new IllegalTokenException("Invalid token: " + token);
            }

            previousToken = token;
        }

        while (!operatorStack.empty()) {
            if (operatorStack.peek() == Operator.LEFT_PARENTHESIS) {
                throw new MissingParenthesisException("Missing a closing parenthesis.");
            }

            output.add(operatorStack.pop().toString());
        }

        return output.toArray(new String[output.size()]);

    }

    private void handleOperator(String token) {
        Operator o1 = findOperator(token);

        while (!operatorStack.empty()) {
            Operator o2 = operatorStack.peek();

            assert o1 != null;
            if (o2 != Operator.LEFT_PARENTHESIS && o1.getPrecedence() <= o2.getPrecedence()) {
                output.add(operatorStack.pop().toString());
            } else {
                break;
            }

            //todo: o1 is right associative, and has precedence less than that of o2,
        }

        operatorStack.push(o1);
    }

    private void handleRightParenthesis() throws MissingParenthesisException {
        try {
            while (!isLeftParenthesis(operatorStack.peek().getCharacter())) {
                output.add(operatorStack.pop().toString());
            }

            operatorStack.pop();
        } catch (EmptyStackException e) {
            throw new MissingParenthesisException("Missing a opening parenthesis.", e);
        }
    }

    private boolean isEmptySpace(String token) {
        return token.trim().isEmpty();
    }

    private String[] handleSpaces(String[] tokens) throws MissingTokenException {
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = handleSpaces(tokens[i]);
        }

        return tokens;
    }

    private String handleSpaces(String input) throws MissingTokenException {
        Pattern p = Pattern.compile("\\d \\d");
        Matcher m = p.matcher(input);

        if (m.find()) {
            throw new MissingTokenException("Missing an operator between two numbers.");
        }

        return input.replace(" ", "");
    }

    private Operator findOperator(String token) {
        for (Operator o : Operator.values()) {
            if (token.equals(o.getCharacter()))
                return o;
        }

        return null;
    }

    private boolean isRightParenthesis(String token) {
        return token.equals(")");
    }

    private boolean isLeftParenthesis(String token) {
        return token.equals("(");
    }

    private boolean isOperator(String token) {
        return findOperator(token) != null;
    }

    private boolean isNumber(String token) {
        if (token == null) {
            return false;
        }

        try {
            //noinspection ResultOfMethodCallIgnored
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
