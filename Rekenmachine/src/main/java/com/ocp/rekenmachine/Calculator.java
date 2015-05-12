package com.ocp.rekenmachine;

import java.math.BigDecimal;

import com.ocp.rekenmachine.algorithm.Algorithm;
import com.ocp.rekenmachine.parser.Tokenizer;
import com.ocp.rekenmachine.syntax.SyntaxChecker;

/**
 * Awesome Calculator Class
 */
public class Calculator implements ICalculator {
		
	@Override
	public String calculate(String[] args) {
		// parse arguments
		Tokenizer tokenizer = new Tokenizer();
		String[] tokens = tokenizer.tokenize(args);
		
		// check syntax
		if (SyntaxChecker.isValidSyntax(tokens)) {
			// convert input to polish format
	        String[] output = Algorithm.convertInfixToPolishFormat(tokens);
	          
	        // calculate operations
	        BigDecimal result = Algorithm.calcTokens(output);
	        return "" + result.toPlainString();
		}
        return "not valid";
	}
}
