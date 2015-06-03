package nl.ordina.academy.calculator;

import java.util.*;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class MathTokenizer {

    /**
     * Converts a mathematical expression into separated tokens. Spaces are ignored.
     * <p/>
     * Examples:
     * - "1 + 1" => {"1", "+", "1"}
     * - "4+2" => {"4", "+", "2"}
     * - "7.8*-2.2" => {"7.8", "*", "-2.2"}
     * - "2*(1+30)" => {"2", "*", "(", "1", "+", "30", ")"}
     *
     * @param input A mathematical expression.
     * @return An array of String tokens.
     */
    public String[] tokenize(String input) {
        StringTokenizer t = new StringTokenizer(input, "+-*/()", true);
        Stack<String> tokens = new Stack<>();

        while (t.hasMoreTokens()) {
            String token = t.nextToken().trim();
            if (token.isEmpty()) continue;
            if (isPlusOrMin(token) && (tokens.isEmpty() || isNotNumber(tokens.peek()))) {
                token += t.nextToken();
            }
            tokens.add(token);
        }

        return tokens.toArray(new String[tokens.size()]);
    }

    private boolean isNotNumber(String previousToken) {
        return Operator.isOperator(previousToken) && !Operator.isRightParenthesis(previousToken);
    }

    private boolean isPlusOrMin(String token) {
        return token.equals(Operator.SUBTRACT.getCharacter()) || token.equals(Operator.SUM.getCharacter());
    }
}
