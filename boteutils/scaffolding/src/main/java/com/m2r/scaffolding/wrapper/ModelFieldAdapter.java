package com.m2r.scaffolding.wrapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.m2r.scaffolding.utils.FieldScaffold;

public class ModelFieldAdapter extends ModelField {

	private Field field;

	public ModelFieldAdapter(ModelClass modelClass, Field field) {
		super(modelClass, field.getName(), field.getType());
		this.field = field;
		this.init();
	}
	
	private void init() {
		FieldScaffold scaffoldingAnnotation = field.getAnnotation(FieldScaffold.class);
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
	
	public Field getField() {
		return field;
	}

}
