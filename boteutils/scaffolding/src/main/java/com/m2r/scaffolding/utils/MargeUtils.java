package com.m2r.scaffolding.utils;

public class MargeUtils {
	
	public String attributeBool(String name, Boolean value, Boolean notShow) {
		return value != null && !value.toString().equals("") && !value.equals(notShow) ? (name + "=" + value.toString()) : "";
	}
	
	public String attributeNumber(String name, Object value) {
		return value != null && !value.toString().equals("") ? (name + "=" + value.toString()) : "";
	}
	
	public String attribute(String name, Object value) {
		return value != null && !value.toString().equals("") ? (name + "=\"" + value.toString() + "\"") : "";
	}
	
}
