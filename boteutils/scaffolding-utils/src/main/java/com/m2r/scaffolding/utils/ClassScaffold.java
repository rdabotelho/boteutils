package com.m2r.scaffolding.utils;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ClassScaffold {

	String label() default "";
	String table() default "";
	String icon() default "fa-file-o";
	
}
