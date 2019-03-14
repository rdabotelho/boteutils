package br.com.m2r.mvn.releaser;

public enum OSEnum {

	WIN("win%d.txt", "bat"),
	UNIX("unix%d.txt", "sh");
	
	private String script;
	private String ext;
	
	private OSEnum(String script, String ext) {
		this.script = script;
		this.ext = ext;
	}
	
	public String getScript(int id) {
		return String.format(script, id);
	}
	
	public String getExt() {
		return ext;
	}
	
	public static OSEnum getOS() {
		String os = System.getProperty("os.name");
		if (os.startsWith("Windows")) {
			return OSEnum.WIN;
		}
		else {
			return OSEnum.UNIX;
		}
	}
		
	
}
