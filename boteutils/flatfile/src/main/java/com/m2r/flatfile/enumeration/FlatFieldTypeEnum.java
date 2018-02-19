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
	},

	DATE_YYMMDD {
		private String DATE_PATTERN = "yyMMdd";
		@Override
		public String prepareValue(String value) {
			return value;
		}
		protected String getDatePattern() {
			return DATE_PATTERN;
		}
	},

	DATE_YYYYMMDD {
		private String DATE_PATTERN = "yyyyMMdd";
		@Override
		public String prepareValue(String value) {
			return value;
		}
		protected String getDatePattern() {
			return DATE_PATTERN;
		}
	},

	DATE_DDMMYY {
		private String DATE_PATTERN = "ddMMyy";
		@Override
		public String prepareValue(String value) {
			return value;
		}
		protected String getDatePattern() {
			return DATE_PATTERN;
		}
	},

	DATE_DDMMYYYY {
		private String DATE_PATTERN = "ddMMyyyy";
		@Override
		public String prepareValue(String value) {
			return value;
		}
		protected String getDatePattern() {
			return DATE_PATTERN;
		}
	};

	private static String ZEROS = "0000000000";
	
	private static String DEFAULT_DATE_PATTERN = "ddMMyy";

	private static SimpleDateFormat dateFormat = new SimpleDateFormat();

	protected abstract String prepareValue(String value);

	public Object convert(Class<?> clazz, String value, String pattern) {
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
			if (value.equals(getEmptyZeroDate())) {
				return null;
			}
			try {
				if ("".equals(pattern)) {
					dateFormat.applyPattern(this.getDatePattern());
				}
				else {
					dateFormat.applyPattern(pattern);					
				}
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
	
	protected String getDatePattern() {
		return DEFAULT_DATE_PATTERN;
	}
	
	private String getEmptyZeroDate() {
		return ZEROS.substring(0, getDatePattern().length());
	}

}
