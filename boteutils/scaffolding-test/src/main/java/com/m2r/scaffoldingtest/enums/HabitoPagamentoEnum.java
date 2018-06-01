package com.m2r.scaffoldingtest.enums;

public enum HabitoPagamentoEnum implements IGenericEnum {

	SEM_EXPERIENCIA(""),
	;
	
    private final String description;
	
	HabitoPagamentoEnum(String description) {
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
