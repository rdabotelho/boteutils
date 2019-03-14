package br.com.m2r.mvn.releaser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.logging.Log;

public class ScriptUtil {

	private String tmpDir;
	private Map<String, String> properties;

	public ScriptUtil() {
		this.properties = new HashMap<>();
		tmpDir = System.getProperty("java.io.tmpdir");
	}

	public void execute(String fileName, OSEnum osType, Log log, int id) throws Exception {
		File file = createTempScript(fileName, osType, id);
		Process process = Runtime.getRuntime().exec(file.toPath().toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null;
		while ((line = in.readLine()) != null) {
			log.info(line);
		}
		in.close();
	}
	
	private File createTempScript(String fileName, OSEnum osType, int id) throws Exception {
		InputStream in = this.getClass().getResourceAsStream(fileName);
		String content = IOUtils.toString(in);
		for (String key : properties.keySet()) {
			content = content.replace("${"+key+"}", properties.get(key));
		}
		File file = new File(getTmpDir(), "_boteReleasePlugin"+id+"." + osType.getExt());
		FileOutputStream out = new FileOutputStream(file);
		IOUtils.write(content, out);
		out.close();
		return file;
	}
	
	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	public String getTmpDir() {
		return tmpDir;
	}
	
}
