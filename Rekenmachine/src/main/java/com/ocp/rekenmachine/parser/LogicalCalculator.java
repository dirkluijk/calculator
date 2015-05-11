package com.ocp.rekenmachine.parser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogicalCalculator {

	private ArrayList<String> argumenten = new ArrayList<String>();
	private BigDecimal result;

	private static Pattern pPlus = Pattern.compile("\\+");
	private static Pattern pMin = Pattern.compile("-");
	private static Pattern pDiv = Pattern.compile("/");
	private static Pattern pMulti = Pattern.compile("\\*");
	private static Pattern pOpen = Pattern.compile("\\(");
	private static Pattern pClose = Pattern.compile("\\)");
	private static Pattern pComma = Pattern.compile(".");
	private static Pattern pFull = Pattern
			.compile("\\+||\\-||\\/||\\*||\\(||\\)");
	private static Pattern pNum = Pattern.compile("\\d|\\.|[0-9.]+$");
	private static Pattern pAlles = Pattern
			.compile("\\+ | \\- | \\/ | \\* | \\( | \\) | [0-9,]+$ | d");
	private static Pattern pAlles2 = Pattern
			.compile("(\\+)(\\-)(\\/)(\\*)(\\()(\\))([0-9,]+$)(d)");

	/**
	 * De constructor van de logical calculator.
	 * 
	 * @param input
	 */
	public LogicalCalculator() {}

	public void vulDeArray(String input) {
		Scanner scanner = null;
		try {	
			scanner = new Scanner(input);
			scanner.useDelimiter("");
			while (scanner.hasNext(pFull) || scanner.hasNextInt()) {
				if (scanner.hasNextInt()) {
					argumenten.add("" + scanner.nextInt());
				}
				if (scanner.hasNext(pComma)) {
					argumenten.add("" + scanner.next(pComma));
				}
				if (scanner.hasNext(pFull)) {
					argumenten.add("" + scanner.next(pFull));
				}
			}
		} finally {
			scanner.close();
		}
	}

	/**
	 * Accepteerd een string welke bewerkt zal worden voor de berekenignen.
	 * 
	 * @param input
	 */
	public String calculate(String input) {
		vulDeArray(input);
		argumenten = verbeterDeSom(argumenten);
		doeHetRekenen();
		return "";
	}
	
	public String doeHetRekenen(){
		Iterator<String> argumentenIterator = argumenten.iterator();
		while (argumentenIterator.hasNext()) {
			System.out.println(argumentenIterator.next());
		}
		return"";
	}

	/**
	 * Bedoelt om te kijken of losse strings in de array niet bij elkaar een
	 * groter getal kunnen vormen.
	 * 
	 * @param verbeterdit
	 * @return
	 */
	public ArrayList<String> verbeterDeSom(ArrayList<String> verbeterdit) {
		int max = verbeterdit.size();
		for (int i = 0; i < max; i++) {
			ArrayList<String> tussen = new ArrayList<String>();
			for (int j = 0; j < verbeterdit.size(); j++) {
				if (j < (verbeterdit.size() - 1)
						&& isNumberOrComma(verbeterdit.get(j))
						&& isNumberOrComma(verbeterdit.get(j + 1))) {
					tussen.add(verbeterdit.get(j)
							.concat(verbeterdit.get(j + 1)));
					++j;
				} else {
					tussen.add(verbeterdit.get(j));
				}
			}
			verbeterdit = tussen;
		}
		return verbeterdit;
	}

	/**
	 * Kijkt of de input string een Operator is van het type *,+,-,/.
	 * 
	 * @param s
	 * @return
	 */
	public boolean isOperator(String s) {
		Matcher matcher = pFull.matcher(s);
		boolean matches = matcher.matches();
		return matches;
	}

	/**
	 * Geeft true terug als de gegeven string een nummer is of een comma.
	 * 
	 * @param s
	 * @return
	 */
	public boolean isNumberOrComma(String s) {
		Matcher matcher = pNum.matcher(s);
		boolean matches = matcher.matches();
		return matches;
	}

	public static void main(String[] args) {
		String input = "4*2/(5+453.65478)2-4";
		LogicalCalculator lc = new LogicalCalculator();
		lc.calculate(input);
		ArrayList<String> ar = lc.getArgumenten();
		System.out.println(ar.size() + " De grote van de array.");
	}

	public String result() {
		return null;
	}

	public ArrayList<String> getArgumenten() {
		return argumenten;
	}
}
