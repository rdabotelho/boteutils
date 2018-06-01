package com.m2r.scaffoldingtest.enums;

public enum NaturezaRendaEnum implements IGenericEnum {

	RENDIMENTO_AUTONOMO(""),
	;
	
    private final String description;
	
	NaturezaRendaEnum(String description) {
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
