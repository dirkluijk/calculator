package com.ocp.rekenmachine;

import java.math.BigDecimal;

import com.ocp.rekenmachine.algorithm.Algorithm;
import com.ocp.rekenmachine.syntax.SyntaxChecker;

/**
 * @author NPerdijk en RvanBroeckhuijsen
 *
 */
public class Calculator implements ICalculator {
		
	@Override
	public String calculate(String[] args) {
		// parse arguments
		
		
		// check syntax
		if (SyntaxChecker.isValidSyntax(args)) {
			// convert input to polish format
	        String[] output = Algorithm.convertInfixToPolishFormat(args);
	          
	        // calculate operations
	        BigDecimal result = Algorithm.calcTokens(output);
	        return "" + result.toPlainString();
		}
        return "not valid";
	}
}
