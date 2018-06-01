package com.m2r.scaffoldingtest.enums;

public enum NaturezaReferenciaEnum implements IGenericEnum {

	CONTRIBUINTE_INDIVIDUAL(""),
	;
	
    private final String description;
	
	NaturezaReferenciaEnum(String description) {
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
