package com.m2r.bitcoinutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ConsoleAsking {
	
	String ask;
	String backLabel;
	List<String> options = new ArrayList<String>();
	Integer optionDefault = null;
	int timeout = 3;
	
	private ConsoleAsking(String ask) {
		this.ask = ask;
	}

	public static OptionAsking createOptionAsking(String asking) {
		return createOptionAsking(asking, "Exit");
	}
	
	public static OptionAsking createOptionAsking(String asking, String backLabel) {
		ConsoleAsking consoleAsking = new ConsoleAsking(asking);
		consoleAsking.backLabel = backLabel;
		return new OptionAsking(consoleAsking);
	}
	
	public static FreeAsking createFreeAsking(String asking) {
		ConsoleAsking consoleAsking = new ConsoleAsking(asking);
		return new FreeAsking(consoleAsking);
	}
	
	public static class FreeAsking {
		
		ConsoleAsking consoleAsking;

		public FreeAsking(ConsoleAsking consoleAsking) {
			this.consoleAsking = consoleAsking;
		}
		
		public FreeAsking timeout(int timeout) {
			this.timeout(timeout);
			return this;
		}
		
		public void execute(Function<String, Boolean> function) {
			String response = null;
			int time = consoleAsking.timeout;
			while (true) {
				writeInConsole("---------------------------------------\n");
				response = readFromConsole("%s: ", consoleAsking.ask);
				if (function.apply(response)) {
					break;
				}
				time--;
				if (time == 0) {
					break;
				}
			}
		}
		
	}
	
	public static class OptionAsking {
		
		ConsoleAsking consoleAsking;
		
		OptionAsking(ConsoleAsking consoleAsking) {
			this.consoleAsking = consoleAsking;
		}

		public OptionAsking option(String option) {
			consoleAsking.options.add(option);
			return this;
		}
		
		public OptionAsking optionDefault(Integer def) {
			consoleAsking.optionDefault = def;
			return this;
		}
		
		public void executeAndExit(Consumer<Integer> consumer) {
			 execute(consumer, true);			
		}
		
		public void execute(Consumer<Integer> consumer) {
			 execute(consumer, false);
		}
		
		public void execute(Consumer<Integer> consumer, boolean exit) {
			String response = null;
			while (true) {
				writeInConsole("---------------------------------------\n");
				writeInConsole("%s\n", consoleAsking.ask);
				writeInConsole("0 - %s.\n", consoleAsking.backLabel);
				for (int i=0; i<consoleAsking.options.size(); i++) {
					writeInConsole("%d - %s.\n", (i+1), consoleAsking.options.get(i));					
				}
				if (consoleAsking.optionDefault != null) {
					response = readFromConsole("Answer [%d]: ", consoleAsking.optionDefault);
				}
				else {
					response = readFromConsole("Answer: ");					
				}
				if (response.equals("")) {
					if (consoleAsking.optionDefault != null) {
						consumer.accept(consoleAsking.optionDefault);
						break;
					}
				}
				else if (response.equals("0")) {
					break;
				}
				else if (response.matches("^[\\d]+$")) {
					Integer restInt = Integer.parseInt(response);
					if (restInt > 0 && restInt <= consoleAsking.options.size()) {
						consumer.accept(restInt);
					}
					if (exit) {
						break;
					}
				}
			}
		}
		
	}
	
	private static String readFromConsole(String msg, Object ... params) {
		try {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        writeInConsole(msg, params);
	        return br.readLine();
		}
		catch (IOException e) {
			return "";
		}
	}
	
	private static void writeInConsole(String msg, Object ... params) {
        System.out.format(msg, params);
	}

}
