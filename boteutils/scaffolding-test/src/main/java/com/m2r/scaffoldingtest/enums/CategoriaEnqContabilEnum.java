package com.m2r.scaffoldingtest.enums;

public enum CategoriaEnqContabilEnum implements IGenericEnum {

	PESSOA_FISICA(""),
	PESSOA_JURIDICA(""),
	;
	
    private final String description;
	
	CategoriaEnqContabilEnum(String description) {
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
