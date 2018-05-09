package com.m2r.scaffolding;

public class NameUtils {

	public static String enumToModelProperty(String value) {
		StringBuilder str = new StringBuilder();
		boolean upper = false;
		for (char c : value.toLowerCase().toCharArray()) {
			if (c == '_') {
				upper = true;
			}
			else {
				if (upper) {
					str.append(Character.toUpperCase(c));
					upper = false;
				}
				else {
					str.append(c);				
				}
			}
		}
		return str.toString();
	}
	
	public static String modelPropertyToEnum(String value) {
		return modelPropertyToKeyValue(value).replaceAll("\\.", "_").toUpperCase();
	}
	
	public static String modelPropertyToViewName(String value) {
		return modelPropertyToKeyValue(value).replaceAll("\\.", "-").toLowerCase();
	}
	
	public static String modelNameToModelProperty(String value) {
		return value.substring(0, 1).toLowerCase() + value.substring(1);
	}
	
	public static String modelPropertyToKeyValue(String value) {
		StringBuilder str = new StringBuilder();
		for (char c : value.toCharArray()) {
			if (Character.isUpperCase(c)) {
				str.append(".");
			}
			str.append(c);
		}
		return str.toString().toLowerCase();
	}
	
	public static String modelPropertyToLabelValue(String value) {
		StringBuilder str = new StringBuilder();
		for (char c : value.toCharArray()) {
			if (Character.isUpperCase(c)) {
				str.append(" ");
			}
			str.append(c);
		}
		return str.toString().substring(0, 1).toUpperCase() + str.toString().substring(1);
	}
	
}
