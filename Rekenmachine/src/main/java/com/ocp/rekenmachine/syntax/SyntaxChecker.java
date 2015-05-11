package com.ocp.rekenmachine.syntax;

/**
 * @author NPerdijk
 * 
 */
public class SyntaxChecker {
	/**
	 * test op: - gelijk aantal ( en ); - correct openen en sluiten van haakjes;
	 * - incorrect afsluiten met operator; - incorrecte symbolen (alles behalve
	 * cijfers, "(", ")", "+", "-", "*", "/" is niet toegestaan)
	 */
	public static boolean isValidSyntax(String[] input) {
		input = StringArrayFormatter.format(input);

		if (hasWrongParentheses(input)) {
			return false;
		}
		if (hasWrongFinalArgument(input)) {
			return false;
		}
		if (hasInvalidArguments(input)) {
			return false;
		}
		return true;
	}

	private static boolean hasWrongFinalArgument(String[] input) {
		String lastChar = input[input.length - 1];
		if (lastChar.equals("*") || lastChar.equals("/")
				|| lastChar.equals("+") || lastChar.equals("-")) {
			System.out.println("Foutmelding: operator " + lastChar
					+ " aan einde gevonden!");
			return true;
		}
		return false;
	}

	private static boolean hasWrongParentheses(String[] input) {
		int numberOfParentheses = 0;
		for (String string : input) {
			if (string.equals("(")) {
				numberOfParentheses++;
			} else if (string.equals(")")) {
				numberOfParentheses--;
				if (numberOfParentheses < 0) {
					System.out.println("Foutmelding: sluithaakje zonder openingshaakje gevonden!");
					return true;
				}
			}
		}
		if (numberOfParentheses != 0) {
			System.out.println("Foutmelding: teveel openingshaakjes!");
			return true;
		}
		return false;
	}

	private static boolean hasInvalidArguments(String[] input) {
		for (String string : input) {
			if (!string.matches("[()*/+,.-]|\\d")) {
				System.out.println("Foutmelding: ongeldig argument "
						+ string + " gevonden!");
				return true;
			}
		}
		return false;
	}
}
