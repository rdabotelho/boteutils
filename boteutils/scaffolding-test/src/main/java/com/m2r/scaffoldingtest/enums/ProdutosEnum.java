package com.m2r.scaffoldingtest.enums;

public enum ProdutosEnum implements IGenericEnum {

	AMAZONIA_FLORESCER(""),
	CONTA_DEPOSITO(""),
	DOMICILIO_BANCARIO(""),
	;
	
    private final String description;
	
	ProdutosEnum(String description) {
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
