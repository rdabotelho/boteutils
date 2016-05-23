package com.m2r.flatfile.utils;

import java.lang.reflect.Field;

import com.m2r.flatfile.annotation.FlatField;

public class ToStringBuilder {

	public static String toString(Object object) {
		Class<?> clazz = object.getClass();
		FlatField ff = null;
		Object value = null;
		StringBuilder builder = new StringBuilder("[");
		for (Field field : clazz.getDeclaredFields()) {
			ff = field.getAnnotation(FlatField.class);
			if ((ff != null) && ff.showToString()) {
				builder.append(" ").append(field.getName()).append(": ");
				field.setAccessible(true);
				try {
					value = field.get(object);
					builder.append(value != null ? value : "");
				} catch (Exception e) {
					builder.append("");
				}
				field.setAccessible(false);
				builder.append(",");
			}
		}

		return builder. append("]").toString().replace(",]", "]");
	}

}
