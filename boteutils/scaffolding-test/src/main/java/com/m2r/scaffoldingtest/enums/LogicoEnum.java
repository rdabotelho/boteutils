package com.m2r.scaffoldingtest.enums;

public enum LogicoEnum implements IGenericEnum {

	SIM(""),
	NAO(""),
	;
	
    private final String description;
	
	LogicoEnum(String description) {
	     this.description = description;
	}

	@Override
	public String getValue() {
		return name();
	}

    @Override
    public String getDescription() {
        return description;
    }

}
