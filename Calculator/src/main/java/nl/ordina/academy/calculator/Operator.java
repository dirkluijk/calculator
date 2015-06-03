package nl.ordina.academy.calculator;

/**
 * @author Dirk Luijk <dirk.luijk@ordina.nl>
 */
public enum Operator {
    LEFT_PARENTHESIS("(", -1), RIGHT_PARENTHESIS(")", -1),
    MULTIPLY("*", 1), DIVIDE("/", 1),
    SUM("+", 0), SUBTRACT("-", 0);

    private final String character;
    private final int precedence;

    Operator(String character, int precedence) {
        this.character = character;
        this.precedence = precedence;
    }

    public String getCharacter() {
        return character;
    }

    public int getPrecedence() {
        return precedence;
    }

    @Override
    public String toString() {
        return String.valueOf(getCharacter());
    }

    public static Operator getOperator(String token) {
        for (Operator o : Operator.values()) {
            if (token.equals(o.getCharacter()))
                return o;
        }

        return null;
    }

    public static boolean isOperator(String token) {
        return getOperator(token) != null;
    }

    public static boolean isRightParenthesis(String token) {
        return RIGHT_PARENTHESIS.getCharacter().equals(token);
    }

    public static boolean isLeftParenthesis(String token) {
        return LEFT_PARENTHESIS.getCharacter().equals(token);
    }
}
