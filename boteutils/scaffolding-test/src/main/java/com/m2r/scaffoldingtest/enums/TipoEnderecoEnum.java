package com.m2r.scaffoldingtest.enums;

public enum TipoEnderecoEnum implements IGenericEnum {

	RESIDENCIAL(""),
	COMERCIAL(""),
	;
	
    private final String description;
	
	TipoEnderecoEnum(String description) {
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
