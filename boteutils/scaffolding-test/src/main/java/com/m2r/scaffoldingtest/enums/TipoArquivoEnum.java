package com.m2r.scaffoldingtest.enums;

public enum TipoArquivoEnum implements IGenericEnum {

	PNG("Portable Network Graphics", "image/png"),
	JPEG("JPEG images", "image/jpeg"),
	MPEG("MPEG Video", "video/mpeg"),
	PDF("Adob PDF", "application/pdf"),
	;
	
    private final String description;
    private final String contentType;
	
	TipoArquivoEnum(String description, String contentType) {
	     this.description = description;
	     this.contentType = contentType;
	}

	@Override
	public String getValue() {
		return name();
	}

    @Override
    public String getDescription() {
        return description;
    }
    
    public String getContentType() {
		return contentType;
	}

}
