package com.m2r.flatfile.enums;


public enum FlatFileMessageEnum {

	ERRO_NEXT_RECORD("com.m2r.flatfile.erro.nextRecord");

	private String key;

	private FlatFileMessageEnum(String key) {
		this.key = key;
	}

	public String getKey() {

		return this.key;
	}

}
