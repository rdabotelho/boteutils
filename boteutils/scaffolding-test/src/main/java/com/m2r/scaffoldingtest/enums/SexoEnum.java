package com.m2r.scaffoldingtest.enums;

public enum SexoEnum implements IGenericEnum {

	MASCULINO(""),
	FEMININO(""),
	;
	
    private final String description;
	
	SexoEnum(String description) {
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
