package com.m2r.scaffolding;

public class MargeUtils {
	
	public String attribute(String name, Object value) {
		return value != null && !value.toString().equals("") ? (name + "=\"" + value.toString() + "\"") : "";
	}
	
}
