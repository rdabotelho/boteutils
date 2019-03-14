package br.com.m2r.mvn.releaser;

public enum EnviromentEnum {
	
	DEVELOPMENT("dev"),
	TEST("test"),
	HOMOLOGATION("hmg"),
	PRODUCTION("prod");
	
	private String id;
	
	private EnviromentEnum(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
}
