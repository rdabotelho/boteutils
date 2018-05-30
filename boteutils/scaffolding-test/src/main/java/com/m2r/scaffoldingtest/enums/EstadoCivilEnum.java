package com.m2r.scaffoldingtest.enums;

public enum EstadoCivilEnum implements IGenericEnum {

	CASADO(""),
	CONCUBINATO(""),
	DESQUITADO(""),
	DIVORCIADO(""),
	SEPARADO(""),
	SOLTEIRO(""),
	UNIAO_ESTAVEL(""),
	VIUVO(""),
	;
	
    private final String description;
	
	EstadoCivilEnum(String description) {
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
