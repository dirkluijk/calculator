package com.ocp.rekenmachine.algorithm;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * customized 3th-party algorithm (Shunting Yard Algorithm)
 */
public class Algorithm {
	private static final int LEFT_ASSOC = 0;
	private static final int RIGHT_ASSOC = 1;

	// set operators
	private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();
	static {
		// Map<"token", []{precendence, associativity}>
		OPERATORS.put("+", new int[] { 0, LEFT_ASSOC });
		OPERATORS.put("-", new int[] { 0, LEFT_ASSOC });
		OPERATORS.put("*", new int[] { 5, LEFT_ASSOC });
		OPERATORS.put("/", new int[] { 5, LEFT_ASSOC });
	}

	/**
	 * Check if token is an operator
	 * @param token of type (@link String)
	 * @return (@link Boolean) if token is true, false otherwise
	 */
	private static boolean isOperator(String token) {
		return OPERATORS.containsKey(token);
	}

	/**
	 * Check if token is an valid operator token
	 * @param token of type (@link String)
	 * @param type token of type (@link Integer)
	 * @return (@link Boolean) if token is a valid operator, return true, false otherwise
	 */
	private static boolean isAssociative(String token, int type) {
		//if (!isOperator(token)) {
		//	throw new IllegalArgumentException("Invalid token: " + token);
		//}

		if (OPERATORS.get(token)[1] == type) {
			return true;
		}
		return false;
	}

	// Compare precedence of operators.
	private static final int cmpPrecedence(String token1, String token2) {
		//if (!isOperator(token1) || !isOperator(token2)) {
		//	throw new IllegalArgumentException("Invalid tokens: " + token1
		//			+ " " + token2);
		//}
		return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
	}

	// Convert infix expression format into reverse Polish notation
	public static String[] convertInfixToPolishFormat(String[] inputTokens) {
		ArrayList<String> out = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();

		for (String token : inputTokens) {
			// token is an operator
			if (isOperator(token)) {
				// While stack not empty AND stack top element
				// is an operator
				while (!stack.empty() && isOperator(stack.peek())) {
					if ((isAssociative(token, LEFT_ASSOC) && cmpPrecedence(token, stack.peek()) <= 0)
							|| (isAssociative(token, RIGHT_ASSOC) && cmpPrecedence(token, stack.peek()) < 0)) {
						out.add(stack.pop());
						continue;
					}
					break;
				}
				// Push the new operator on the stack
				stack.push(token);
			}
			// If token is a left bracket '('
			else if (token.equals("(")) {
				stack.push(token); //
			}
			// If token is a right bracket ')'
			else if (token.equals(")")) {
				while (!stack.empty() && !stack.peek().equals("(")) {   
					out.add(stack.pop());
				}
				stack.pop();
			}
			// If token is a number
			else {
				out.add(token);
			}
		}
		while (!stack.empty()) {
			out.add(stack.pop());
		}
		String[] output = new String[out.size()];
		return out.toArray(output);
	}

	/**
	 * Calculate the given tokens
	 * @param array of tokens to calculate
	 * @return result of calculation of type (@link BigDecimal)
	 */
	public static BigDecimal calcTokens(String[] tokens) {
		// set roundings
		MathContext mathContext = new MathContext(3, RoundingMode.HALF_UP);
		
		Stack<String> stack = new Stack<String>();

		for (String token : tokens) {
			// check if token is a value, yes, push on top of the stack
			if (!isOperator(token)) {
				stack.push(token);
			} else {
				// wut.. token is an operator, so pop top entries
				BigDecimal d2 = new BigDecimal(stack.pop());
				BigDecimal d1 = new BigDecimal(stack.pop());
				

				// do the awesome calculation
				BigDecimal result = token.compareTo("+") == 0 ? d1.add(d2, mathContext) : token
						.compareTo("-") == 0 ? d1.subtract(d2, mathContext)
						: token.compareTo("*") == 0 ? d1.multiply(d2, mathContext) : d1.divide(d2, mathContext);

				// push the shizzle on the stack
				stack.push(String.valueOf(result));
			}
		}

		return new BigDecimal(stack.pop(), mathContext);
	}
}
