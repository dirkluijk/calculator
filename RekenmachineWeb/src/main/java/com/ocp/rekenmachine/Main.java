package com.ocp.rekenmachine;

public class Main {
	
    public static void main(String[] args) {
    	if (!(args.length > 0)) {
    		args = "( 1 + 2 ) * ( 3 / 4 ) - ( 5 + 6 )".split(" "); 
    	}
    	ICalculator calculator = new Calculator();
    	String result = calculator.calculate(args);
        
    	System.out.println(result);
    }
}