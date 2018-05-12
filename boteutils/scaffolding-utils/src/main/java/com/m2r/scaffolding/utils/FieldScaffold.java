package com.m2r.scaffolding.utils;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface FieldScaffold {

	String label() default "";
	boolean isfilter() default false;
	boolean isViewed() default true;
	boolean isRequired() default false;
	boolean isDisabled() default false;
	boolean isText() default false;
	boolean isViewedOnTable() default false;
	boolean selectWithFilter() default false;
	int maxlength() default 0;
	String decimalPlaces() default "";
	String decimalSeparator() default "";
	String symbol() default "";
	String pattern() default "";
	String columnWidth() default "";
	boolean isPropertyTransient() default false;
	
}
