package com.m2r.scaffolding;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleReader {

    public static String readFromConsole(String prompt, String defaultValue) {
    	String line = readFromConsole(prompt);
    	if (line == null || !line.equals("")) {
    		return defaultValue != null ? defaultValue : line;
    	}
    	return line;
    }
    
    public static String readFromConsole(String prompt) {
		try {
	    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	    		System.out.print(prompt);
	    		return br.readLine();
		}
		catch (Exception e) {
			return null;
		}
    }	    

}
