package com.talend.se.platform.demos.datavalidator;

public abstract class DataValidatorFunctions {

	// These methods are registered in the eval context and therefore accessible through function calls
	// in validation expressions

	public static String isEven(int i) {
		if ((i % 2) == 0) {
			return "y";
		}
		return "n";
	}

	public static int[] reverseInt(int i, int j, int k) {
		return new int[] { k, j, i };
	}

	public static String reverseString(String input) {
		StringBuilder backwards = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			backwards.append(input.charAt(input.length() - 1 - i));
		}
		return backwards.toString();
	}

	public static String varargsFunctionReverseStringsAndMerge(String... strings) {
		StringBuilder sb = new StringBuilder();
		if (strings != null) {
			for (int i = strings.length - 1; i >= 0; i--) {
				sb.append(strings[i]);
			}
		}
		return sb.toString();
	}

	public static String varargsFunctionReverseStringsAndMerge2(int j, String... strings) {
		StringBuilder sb = new StringBuilder();
		sb.append(j);
		if (strings != null) {
			for (int i = strings.length - 1; i >= 0; i--) {
				sb.append(strings[i]);
			}
		}
		return sb.toString();
	}
	
}
