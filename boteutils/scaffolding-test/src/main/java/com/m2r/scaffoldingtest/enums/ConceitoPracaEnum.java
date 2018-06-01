package com.m2r.scaffoldingtest.enums;

public enum ConceitoPracaEnum implements IGenericEnum {

	BOM(""),
	RAZOAVEL(""),
	RUIM(""),
	;
	
    private final String description;
	
	ConceitoPracaEnum(String description) {
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
