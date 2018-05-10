package com.m2r.scaffolding.utils;

public interface ScaffoldEnum {
	
	public String getDescricao();
	default public String getNamedItems() {
		return getClass().getSimpleName().substring(0, 1).toLowerCase() + getClass().getSimpleName().substring(1) + "items";
	}

}
