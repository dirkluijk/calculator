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

        LinkedList<String> tokens = splitOnOperators(input);
        trimAll(tokens);
        handleSignedNumbers(tokens);

        return tokens.toArray(new String[tokens.size()]);
    }

    private void trimAll(LinkedList<String> tokens) {
        ListIterator<String> iterator = tokens.listIterator();

        while (iterator.hasNext()) {
            iterator.set(iterator.next().trim());
        }
    }

    private void handleSignedNumbers(LinkedList<String> tokens) {

        if (tokens.getFirst().isEmpty()) {
            tokens.removeFirst();
        }

        for (int i = 0; i < tokens.size(); i++) {
            try {
                String currentToken = tokens.get(i);

                if (isPlusOrMin(currentToken)) {
                    if (i == 0 || isNotNumber(tokens.get(i - 1))) {
                        tokens.set(i, currentToken.concat(tokens.get(i + 1)));
                        tokens.remove(i + 1);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
    }

    private boolean isNotNumber(String previousToken) {
        return Operator.isOperator(previousToken) && !Operator.isRightParenthesis(previousToken);
    }

    private LinkedList<String> splitOnOperators(String input) {
        //return new StringTokenizer(input, "+-/*()", true); // zou leesbaarder zijn

        input = input.replace(" ", "");

        String[] tokens = input.split("(?<=[\\*\\+\\-/\\(\\)])|(?=[\\*\\+\\-/\\(\\)])", -1);
        return new LinkedList<>(Arrays.asList(tokens));
    }

    private boolean isPlusOrMin(String c) {
        return String.valueOf(c).equals(Operator.SUBTRACT.getCharacter()) || String.valueOf(c).equals(Operator.SUM.getCharacter());
    }
}
