package com.m2r.scaffoldingtest.enums;

public enum GrauInstrucaoEnum implements IGenericEnum {

	ALFABETIZADO(""),
	ANALFABETO(""),
	DOUTORADO(""),
	EDUCACAO_INFANTIL_COMPLETO(""),
	ENSINO_FUNDAMENTAL_COMPLETO(""),
	ENSINO_FUNDAMENTAL_INCOMPLETO(""),
	ENSINO_MEDIO_COMPLETO(""),
	ENSINO_MEDIO_INCOMPLETO(""),
	MBA_COMPLETO(""),
	MESTRADO(""),
	POS_DOUTORADO(""),
	POS_GRADUADO(""),
	SUPERIOR_COMPLETO(""),
	;
	
    private final String description;
	
	GrauInstrucaoEnum(String description) {
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
