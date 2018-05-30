package com.m2r.scaffoldingtest.enums;

public enum CapacidadeCivelEnum implements IGenericEnum {

	ABSOLUTAMENTE_INCAPAZ(""),
	CAPAZ(""),
	MENOR_EMANCIPADO(""),
	RELATIVAMENTE_INCAPAZ(""),
	;
	
    private final String description;
	
	CapacidadeCivelEnum(String description) {
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
