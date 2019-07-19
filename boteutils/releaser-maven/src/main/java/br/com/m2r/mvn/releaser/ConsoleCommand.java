package br.com.m2r.mvn.releaser;

import java.io.File;
import java.util.concurrent.Executors;

import org.apache.maven.plugin.logging.Log;

public class ConsoleCommand {

	private StringBuffer buffer;
	private boolean isWindows;
	private Log log;
	private File dir;
	private File mavenHome;
	private EnviromentEnum env;
	private VersionTypeEnum versionType;
	
	private ConsoleCommand() {
		isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		buffer = new StringBuffer();
	}
	
	public static ConsoleCommand create() {
		ConsoleCommand cc = new ConsoleCommand();
		return cc;
	}
	
	private String mvn() {
		return String.format("%s%s", mavenHome, (isWindows ? "\\bin\\mvn.cmd" : "/bin/mvn"));
	}
	
	public ConsoleCommand inDir(String dir) {
		this.dir = new File(dir);
		return this;
	}
	
	public ConsoleCommand withLog(Log log) {
		this.log = log;
		return this;
	}
	
	public ConsoleCommand inEnviroment(EnviromentEnum env) {
		this.env = env;
		return this;
	}
	
	public ConsoleCommand withMavenHome(String mavenHome) {
		this.mavenHome = new File(mavenHome);
		return this;
	}
	
	public ConsoleCommand withVersionType(VersionTypeEnum versionType) {
		this.versionType = versionType;
		return this;
	}
	
	public ConsoleCommand changeToReleaseVersion() throws Exception {
		switch (versionType) {
		case MAJOR: 
			addCommand(mvn() + " build-helper:parse-version versions:set -DnewVersion=${parsedVersion.nextMajorVersion}.0.0 versions:commit");
			break;
		case MINOR: 
			addCommand(mvn() + " build-helper:parse-version versions:set -DnewVersion=${parsedVersion.majorVersion}.${parsedVersion.nextMinorVersion}.0 versions:commit");
			break;
		default:
			addCommand(mvn() + " build-helper:parse-version versions:set -DnewVersion=${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.incrementalVersion} versions:commit");
		}
		return this;
	}
	
	public ConsoleCommand changeToNextSnapshotVersion() throws Exception {
		addCommand(mvn() + " build-helper:parse-version versions:set -DnewVersion=${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.nextIncrementalVersion}-SNAPSHOT versions:commit");
		return this;
	}
	
	public ConsoleCommand generateRelease(boolean isDeploy) throws Exception {
		addCommand(mvn() + " clean " + (isDeploy ? "deploy" : "install") + " -Denv=" + env.name() + " -DversionType=" + versionType.name());
		return this;
	}
	
	public ConsoleCommand scmCheckin(String user, String password, String msg) throws Exception {
		addCommand(mvn() + " scm:checkin -Dusername=" + user + " -Dpassword=" + password + " \"-Dmessage=" + msg + "\"");
		return this;
	}
	
	private void addCommand(String command) {
		buffer.append(command).append(" && ");
	}
	
	public void execute() throws Exception {
		String commands = buffer.substring(0, buffer.length() - 4);
		ProcessBuilder builder = new ProcessBuilder();
		if (isWindows) {
		    builder.command("cmd.exe", "/c", commands);
		} else {
		    builder.command("sh", "-c", commands);
		}
		builder.directory(dir);
		Process process = builder.start();
		StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), (line) -> log.info(line));
		Executors.newSingleThreadExecutor().submit(streamGobbler);
		process.waitFor();
	}
	
}
