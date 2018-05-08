package com.m2r.scaffolding.utils;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Scaffolding {

	String label() default "";
	boolean isfilter() default false;
	boolean isViewed() default true;
	boolean isText() default false;
	int maxlength() default 0;
	boolean isDisabled() default false;
	String decimalPlaces() default "";
	String decimalSeparator() default "";
	String symbol() default "";
	String pattern() default "";
	boolean isRequired() default false;
	boolean isViewedOnTable() default false;
	String columnWidth() default "";
	
}
