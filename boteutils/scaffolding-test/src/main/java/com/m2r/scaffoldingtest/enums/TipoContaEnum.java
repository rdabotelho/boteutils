package com.m2r.scaffoldingtest.enums;

public enum TipoContaEnum implements IGenericEnum {

	SIMPLIFICADO(""),
	;
	
    private final String description;
	
	TipoContaEnum(String description) {
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
