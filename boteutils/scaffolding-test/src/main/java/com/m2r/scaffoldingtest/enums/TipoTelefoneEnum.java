package com.m2r.scaffoldingtest.enums;

public enum TipoTelefoneEnum implements IGenericEnum {

	FIXO(""),
	CELULAR(""),
	;
	
    private final String description;
	
	TipoTelefoneEnum(String description) {
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
