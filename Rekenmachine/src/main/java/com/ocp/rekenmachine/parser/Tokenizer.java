package com.ocp.rekenmachine.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public class Tokenizer {

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
        List<String> tokens = new ArrayList<>();

        boolean minAndPlusAsPartOfNumber = true;
        StringBuilder number = new StringBuilder();

        for (char character : input.toCharArray()) {
            if (character == ' ') {
                continue;
            }

            if (!Operator.isOperator(character) || (isPlusOrMin(character) && minAndPlusAsPartOfNumber)) {
                number.append(character);
                minAndPlusAsPartOfNumber = false;
            } else {
                // add number token if needed
                if (number.length() > 0) {
                    tokens.add(number.toString());
                    number = new StringBuilder();
                }

                tokens.add(String.valueOf(character));

                if (character != '(' && character != ')') {
                    minAndPlusAsPartOfNumber = true;
                }
            }
        }

        // add last token if needed
        if (number.length() > 0) {
            tokens.add(number.toString());
        }

        return tokens.toArray(new String[tokens.size()]);
    }
    
    public String[] tokenize(String[] input) {
        StringBuilder builder = new StringBuilder();
        for (String arg : input) {
            builder.append(arg);
        }
        return tokenize(builder.toString());
    }

    private boolean isPlusOrMin(char c) {
        return String.valueOf(c).equals(Operator.SUBTRACT.getCharacter()) || String.valueOf(c).equals(Operator.SUM.getCharacter());
    }
}
