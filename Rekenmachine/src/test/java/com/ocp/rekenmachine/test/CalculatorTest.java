package com.ocp.rekenmachine.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ocp.rekenmachine.Calculator;
import com.ocp.rekenmachine.Main;
import com.ocp.rekenmachine.algorithm.Algorithm;
import com.ocp.rekenmachine.syntax.StringArrayFormatter;
import com.ocp.rekenmachine.syntax.SyntaxChecker;

/**
 * test class for testing the calculator component.
 * Tests performed:
 * -++ Control brackets
 * -++ Negative numbers
 * -++ Operator precedence
 * -++ Division by 0
 * -++ Rounding of numbers
 * -++ Regular calculator methods
 * -++ Invalid operators (f.e. @)
 */

public class CalculatorTest {

	private Calculator calculator;
	
	/**
	 * @param string s
	 * @return a string array
	 */
	private String[] stringToStringArray(String s) {
		return s.split(" ");
	}

	@Before
	public void setUp() throws Exception {
		this.calculator = new Calculator();
	}


	@After
	public void tearDown() throws Exception {
		this.calculator = null;
	}

	/**
	 * Test if positive numbers are correctly added
	 */
	@Test
	public void addPositiveNumbersTest() {
		assertEquals("add", "12", calculator.calculate(stringToStringArray("3 + 9")));
		//assertEquals("add large numbers", 9_900_919_795_594, testklasse.calculate("21467482313+9879452313281"));
	}

	/**
	 * Test if negative numbers are correctly added
	 */
	@Test
	public void addNegativeNumbersTest() {
		assertEquals("add", "-12", calculator.calculate(stringToStringArray("-3 + -9")));
	}
	
	/**
	 * Test if positive numbers are correctly subtracted
	 */
	@Test
	public void subtractPositiveNumbersTest() {
		assertEquals("subtract", "12", calculator.calculate(stringToStringArray("19 - 7")));
	}
	
	/**
	 * Test if negative numbers are correctly subtracted
	 */
	@Test
	public void subtractNegativeNumbersTest() {
		assertEquals("subtract", "-6", calculator.calculate(stringToStringArray("3 - 9")));
	}
	
	/**
	 * Test if numbers are correctly divided. Including division by 0
	 */
	@Test
	public void divideNumbersTest() {
		assertEquals("divide", "18", calculator.calculate(stringToStringArray("108 / 6")));
		assertEquals("divide", "0", calculator.calculate(stringToStringArray("0 / 36")));
	}	

	/**
	 * Test if numbers are correctly divided. Including division by 0
	 */
	//@Test(expected = ArithmeticException.class)
	//public void divisionBy0NumbersTest() {
	//	assertEquals("division by 0!", "Infinity", calculator.calculate(stringToStringArray("36 / 0")));
	//}	
	
	// Test is numbers are correctly multiplied
	@Test
	public void multiplyNumbersTest() {
		assertEquals("multiply", "27", calculator.calculate(stringToStringArray("3 * 9")));
	}
	
	// Test if the correct order of operator precedence is handled
	@Test
	public void mvdwoaNumbersTest() {
		assertEquals("order of operator precedence: * / + -", "27", calculator.calculate(stringToStringArray("3 * ( 6 - 4 ) / 2 + 12 * 2")));
	}

	/**
	 * 	Test if all opened brackets are also closed (and viceversa) 
	 */	
	@Test
	public void checkBracketsAndSyntaxTest() {
		assertEquals("brackets should all be closed", "not valid", calculator.calculate(stringToStringArray("( ( 3 - 9 ) * 8")));
		assertEquals("brackets should all be opened", "not valid", calculator.calculate(stringToStringArray("( 3 - 9 ) * 8 )")));
		assertEquals("brackets", "-48", calculator.calculate(stringToStringArray("( ( 3 - 9 ) * 8 )")));
		assertEquals("correct syntax", "80", calculator.calculate(stringToStringArray("( ( 3 + 7 ) * 8 )")));
	}
	
	/**
	 *  Test if syntax is correct 
	 */
	@Test 
	public void checkFalseSyntaxTest() {
		assertEquals("syntax", "not valid", calculator.calculate(stringToStringArray("( ( 3 - 9 ) * 8 ) @ $")));
		assertEquals("syntax", "not valid", calculator.calculate(stringToStringArray("( ( 3 @ 9 ) & 8 )"))); 
		assertEquals("syntax", "not valid", calculator.calculate(stringToStringArray("3 * 9 /")));
		assertEquals("syntax", "not valid", calculator.calculate(stringToStringArray("3 * 9 *")));
		assertEquals("syntax", "not valid", calculator.calculate(stringToStringArray("3 * 9 +")));
		assertEquals("syntax", "not valid", calculator.calculate(stringToStringArray("3 * 9 -")));
		//assertEquals("syntax", "not valid", calculator.calculate(stringToStringArray(null)));
		//assertEquals("syntax", "not valid", calculator.calculate(stringToStringArray(" ")));
	}
	
	/**
	 * Test if numbers are correctly rounded
	 */
	@Test
	public void numbersRoundingTest() {
		assertEquals(" ", "26.4", calculator.calculate(stringToStringArray("3 * ( 6 - 4 ) / 2.5 + 12 * 2")));
		assertEquals(" ", "0.333", calculator.calculate(stringToStringArray("1 / 3")));
		assertEquals("rounding", "1.45", calculator.calculate(stringToStringArray("2.9 / 2")));
		//assertEquals("rounding", "2.455", calculator.calculate(stringToStringArray("2.4545")));
	}
	
	/**
	 * Test LogicalCalculator class
	 */
	@Test
	public void logicalCalculatorTest() {
		
	}

	/**
	 * Test Main class
	 */
	@Test
	public void mainTest() {
		Main main = new Main();
		assertNotNull(main);
		Main.main(stringToStringArray("1 / 3"));
		Main.main(stringToStringArray(" "));
	}
	
	/**
	 * Test class constructors
	 */
	@Test
	public void constructorTest() {
		SyntaxChecker sc = new SyntaxChecker();
		assertNotNull(sc);
		StringArrayFormatter saf = new StringArrayFormatter();
		assertNotNull(saf);
		Algorithm a = new Algorithm();
		assertNotNull(a);
		//mvn archetype generator --> war file genereren
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}
