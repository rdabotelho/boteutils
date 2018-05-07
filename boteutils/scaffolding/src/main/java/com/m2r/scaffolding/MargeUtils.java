package com.m2r.scaffolding;

public class MargeUtils {
	
	public String labelValue(Object value, boolean isRequired) {
		return "#{" + value.toString() + "}" + (isRequired ? " *" : "");
	}
	
	public String attribute(String name, Object value) {
		return value != null && !value.toString().equals("") ? (name + "=\"" + value.toString() + "\"") : "";
	}
	
}
