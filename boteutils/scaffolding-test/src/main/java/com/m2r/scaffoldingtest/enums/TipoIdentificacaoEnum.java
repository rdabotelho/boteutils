package com.m2r.scaffoldingtest.enums;

public enum TipoIdentificacaoEnum implements IGenericEnum {

	RG(""),
	CNH(""),
	CTPS(""),
	;
	
    private final String description;
	
	TipoIdentificacaoEnum(String description) {
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
