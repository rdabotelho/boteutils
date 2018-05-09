package com.m2r.scaffolding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

import com.m2r.scaffolding.utils.Scaffolding;
import com.m2r.scaffolding.utils.ScaffoldingModel;

public class FieldAdapter {

	private Field field;
	private String label;
	private boolean isFilter;
	private boolean isViewed;
	private boolean isText;
	private Integer maxLength;
	private boolean isDisabled;
	private String decimalPlaces;
	private String decimalSeparator;
	private String symbol;
	private String pattern;
	private boolean isRequired;
	private boolean isViewedOnTable;
	private String columnWidth;

	public FieldAdapter(Field field) {
		this.field = field;
		this.init();
	}
	
	private void init() {
		Scaffolding scaffoldingAnnotation = field.getAnnotation(Scaffolding.class);
		label = scaffoldingAnnotation != null && !scaffoldingAnnotation.label().equals("") ? scaffoldingAnnotation.label() : null;
		isFilter = scaffoldingAnnotation != null && scaffoldingAnnotation.isfilter();
		isViewed = !(getName().equals("id") || getName().equals("serialVersionUID")) && !(scaffoldingAnnotation != null && !scaffoldingAnnotation.isViewed());
		isText = (scaffoldingAnnotation != null && scaffoldingAnnotation.isText()) || (isAnnotationPresent("javax.persistence.Lob")); 
		maxLength = scaffoldingAnnotation != null && scaffoldingAnnotation.maxlength() > 0 ? scaffoldingAnnotation.maxlength() : null;
		isDisabled = scaffoldingAnnotation != null && scaffoldingAnnotation.isDisabled();
		decimalPlaces = scaffoldingAnnotation != null && !scaffoldingAnnotation.decimalPlaces().equals("") ? scaffoldingAnnotation.decimalPlaces() : null;
		decimalSeparator = scaffoldingAnnotation != null && !scaffoldingAnnotation.decimalSeparator().equals("") ? scaffoldingAnnotation.decimalSeparator() : null;
		symbol = scaffoldingAnnotation != null && !scaffoldingAnnotation.symbol().equals("") ? scaffoldingAnnotation.symbol() : null;
		pattern = scaffoldingAnnotation != null && !scaffoldingAnnotation.pattern().equals("") ? scaffoldingAnnotation.pattern() : null;
		isRequired = (scaffoldingAnnotation != null && scaffoldingAnnotation.isRequired()) || (isAnnotationPresent("nullable=false"));
		isViewedOnTable = scaffoldingAnnotation != null && scaffoldingAnnotation.isViewedOnTable();
		columnWidth = scaffoldingAnnotation != null && !scaffoldingAnnotation.columnWidth().equals("") ? scaffoldingAnnotation.columnWidth() : null;
	}
	
	public boolean isAnnotationPresent(final String content) {
		for (Annotation a : field.getAnnotations()) {
			if (a.annotationType().getName().equals(content) || a.toString().contains(content)) {
				return true;
			}
		}
		return false;
	}
	
	public String getName() {
		return field.getName();
	}
	
	public String getGetMethodName() {
		return "get" + getName().substring(0, 1).toUpperCase() + getName().substring(1);
	}
	
	public String getSetMethodName() {
		return "set" + getName().substring(0, 1).toUpperCase() + getName().substring(1);
	}
	
	public Class<?> getType() {
		return field.getType();
	}
	
	public String getLabel() {
		return label;
	}

	public boolean isFilter() {
		return isFilter;
	}
	
	public boolean isViewed() {
		return isViewed;
	}
	
	public Integer getMaxLength() {
		return maxLength;
	}
	
	public boolean isDisabled() {
		return isDisabled;
	}

	public String getDecimalPlaces() {
		return decimalPlaces;
	}

	public String getDecimalSeparator() {
		return decimalSeparator;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getPattern() {
		return pattern;
	}
	
	public boolean isMask() {
		return pattern != null;
	}

	public boolean isRequired() {
		return isRequired;
	}
	
	public boolean isViewedOnTable() {
		return isViewedOnTable;
	}
	
	public String getColumnWidth() {
		return columnWidth;
	}

	public Field getField() {
		return field;
	}
	
	public String getEnumItemsNamed() {
		return "tipos" + field.getType().getSimpleName().replaceAll("Enum", "s");
	}
	
	public boolean isStringType() {
		return getType().equals(String.class) && !isText;
	}
	
	public boolean isTextType() {
		return getType().equals(String.class) && isText;
	}
	
	public boolean isIntegerType() {
		return getType().equals(Byte.class) || getType().equals(Short.class) || getType().equals(Integer.class) || getType().equals(Long.class) || getType().equals(BigInteger.class);
	}
	
	public boolean isDecimalType() {
		return getType().equals(Float.class) || getType().equals(Double.class) || getType().equals(BigDecimal.class);
	}
	
	public boolean isEnumType() {
		return getType().isEnum();
	}
	
	public boolean isCollectionType() {
		return getType().isAssignableFrom(Collections.class);
	}
	
	public boolean isModelType() {
		return getType().isAssignableFrom(ScaffoldingModel.class);
	}
	
	public boolean isLocalDateType() {
		return getType().equals(LocalDate.class);
	}
	
	public boolean isLocalDateTimeType() {
		return getType().equals(LocalDateTime.class);
	}
	
	public boolean isLocalTimeType() {
		return getType().equals(LocalTime.class);
	}
		
}
