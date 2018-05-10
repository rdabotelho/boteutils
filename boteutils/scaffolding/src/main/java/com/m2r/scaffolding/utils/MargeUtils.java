package com.m2r.scaffolding.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MargeUtils {

	public String attributeBool(String name, Boolean value, Boolean notShow) {
		return value != null && !value.toString().equals("") && !value.equals(notShow) ? (name + "=" + value.toString())
				: "";
	}

	public String attributeNumber(String name, Object value) {
		return value != null && !value.toString().equals("") ? (name + "=" + value.toString()) : "";
	}

	public String attribute(String name, Object value) {
		return value != null && !value.toString().equals("") ? (name + "=\"" + value.toString() + "\"") : "";
	}

	public static List<Field> getAllModelFields(Class<?> aClass) {
	    List<Field> fields = new ArrayList<>();
	    do {
	        Collections.addAll(fields, aClass.getDeclaredFields());
	        aClass = aClass.getSuperclass();
	    } 
	    while (aClass != null);
	    return fields;
	}
}
