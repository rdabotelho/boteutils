package com.m2r.scaffoldingtest.enums;

public enum EnquadramentoEnum implements IGenericEnum {

	PESSOA_FISICA_OUTROS(""),
	;
	
    private final String description;
	
	EnquadramentoEnum(String description) {
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
