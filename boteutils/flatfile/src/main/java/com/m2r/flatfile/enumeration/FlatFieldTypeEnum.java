package com.m2r.flatfile.enumeration;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum FlatFieldTypeEnum {


	INTEGER {
		@Override
		public String prepareValue(String value) {
			if (value.startsWith("0")) {
				for (int i=0; i<value.length(); i++) {
					if (value.charAt(i) != '0') {
						return value.substring(i);
					}
				}
			}
			return value;
		}
	},

	DECIMAL {
		@Override
		public String prepareValue(String value) {
			value = value.substring(0, value.length() - 2) + "." + value.substring(value.length() - 2);
			if (value.startsWith("0")) {
				for (int i=0; i<value.length(); i++) {
					if (value.charAt(i) != '0') {
						return value.substring(i);
					}
				}
			}
			return value;
		}
	},

	STRING {
		@Override
		public String prepareValue(String value) {
			return value.trim();
		}
	},

	DATE {
		@Override
		public String prepareValue(String value) {
			return value;
		}
	};

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");

	protected abstract String prepareValue(String value);

	public Object convert(Class<?> clazz, String value) {
		if (clazz.equals(BigDecimal.class)) {
			return new BigDecimal(this.prepareValue(value));
		}
		else if (clazz.equals(Integer.class)) {
			return Integer.parseInt(this.prepareValue(value));
		}
		else if (clazz.equals(Float.class)) {
			return Float.parseFloat(this.prepareValue(value));
		}
		else if (clazz.equals(Date.class)) {
			if (value.equals("000000")) {
				return null;
			}
			try {
				return dateFormat.parse(this.prepareValue(value));
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		else {
			return this.prepareValue(value);
		}
	}

}
