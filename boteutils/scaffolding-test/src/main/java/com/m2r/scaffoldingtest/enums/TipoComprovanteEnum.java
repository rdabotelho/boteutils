package com.m2r.scaffoldingtest.enums;

public enum TipoComprovanteEnum implements IGenericEnum {

	CONTA_GAS(""),
	CONTA_INTERNET(""),
	CONTA_LUZ(""),
	CONTA_TELEFONE_FIXO(""),
	CONTA_TELEFONE_MOVEL(""),
	CONTA_TV_POR_ASSINATURA(""),
	CONTRATO_LOCACAO(""),
	ESCRITURA_DO_IMOVEL(""),
	EXTRATO_BANCARIO(""),
	FATURA_CARTAO_CREDITO(""),
	REGISTRO_IMOVEL(""),
	OUTROS(""),
	;
	
    private final String description;
	
	TipoComprovanteEnum(String description) {
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
