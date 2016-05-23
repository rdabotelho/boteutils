package com.m2r.flatfile.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.m2r.flatfile.enumeration.FlatFieldTypeEnum;

@Target({ FIELD })
@Retention(RUNTIME)
public @interface FlatField {

	int begin();
	int end();
	FlatFieldTypeEnum converter() default FlatFieldTypeEnum.STRING;
	String id() default "";
	boolean showToString() default true;

}
