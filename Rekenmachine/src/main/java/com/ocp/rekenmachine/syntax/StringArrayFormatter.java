package com.ocp.rekenmachine.syntax;

import java.util.ArrayList;

public class StringArrayFormatter {
	
	public static String[] format(String[] args){
		String string = "";
		for (String argument : args){
			string += argument.replace(" ", "");
		}
		
		String[] zonderSpaties = new String[string.length()];
		
		for(int i = 0; i<string.length(); i++){
			zonderSpaties[i] = string.substring(i, i+1);
		}
		
		return zonderSpaties;
	}
}
