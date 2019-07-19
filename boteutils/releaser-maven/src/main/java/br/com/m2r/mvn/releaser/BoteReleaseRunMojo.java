package br.com.m2r.mvn.releaser;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/*
 * EXECUCAO
 * br.com.m2r.mvn.releaser:boteReleaserPlugin:1.0.0-SNAPSHOT:run -Denv=TEST
 */
@Mojo(name = "run", defaultPhase=LifecyclePhase.GENERATE_RESOURCES)
public class BoteReleaseRunMojo extends AbstractMojo {

	private OSEnum osType;
	
	@Parameter(defaultValue = "${project}")
	private MavenProject project;
	
	@Parameter(property = "mavenHome", defaultValue = "${maven.home}")
	private String mavenHome;
	
	@Parameter(property = "env", required = true)
	private EnviromentEnum env;
	
	@Parameter(property = "versionType", defaultValue="INCREMENTAL", required = true)
	private VersionTypeEnum versionType;
	
	@Parameter(property = "deploy", defaultValue="true")
	private Boolean deploy;
	
	@Parameter(property = "commitSvn", defaultValue="true")
	private Boolean commitSvn;
		
	@Parameter(property = "message", defaultValue="Release: %s [boteReleaserPlugin]")
	private String svnMessage;	
	
	@Parameter(property = "svnUser", defaultValue="")
	private String svnUser;	
	
	@Parameter(property = "svnPassword", defaultValue="")
	private String svnPassword;	
	
	public BoteReleaseRunMojo() {
		osType = OSEnum.getOS();
	}
	
	public void execute() throws MojoExecutionException {
		if (getProject().getParent() != null) {
			return;
		}
		try {
			this._execute();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MojoExecutionException("Bote Release Plugin Erro", e);
		}		
	}
	
	private void _execute() throws Exception {
		ConsoleCommand script = ConsoleCommand.create()
				
			.inDir(getProjectBaseDir())
			.withLog(getLog())
			.withMavenHome(getMavenHome())
			.inEnviroment(getEnv())
			.withVersionType(getVersionType())
			
			.changeToReleaseVersion()
			.generateRelease(getDeploy())
			.changeToNextSnapshotVersion();
		
		if (getCommitSvn()) {
			if (getProject().getScm() == null) {
				getLog().error("Error performing SVN commit. Tag scm (url) not defined in pom.xml.");
			}
			else {
				script.scmCheckin(getSvnUser(), getSvnPassword(), String.format(getSvnMessage(), getProject().getVersion().replace("-SNAPSHOT", "")));
			}
		}
		
		script.execute();
	}
	
	public OSEnum getOsType() {
		return osType;
	}
	
	public MavenProject getProject() {
		return project;
	}
	
	public String getProjectBaseDir() {
		return getProject().getBasedir().getAbsolutePath();
	}
	
	public String getMavenHome() {
		return mavenHome;
	}

	public void setMavenHome(String mavenHome) {
		this.mavenHome = mavenHome;
	}

	public EnviromentEnum getEnv() {
		return env;
	}

	public void setEnv(EnviromentEnum env) {
		this.env = env;
	}

	public VersionTypeEnum getVersionType() {
		return versionType;
	}

	public void setVersionType(VersionTypeEnum versionType) {
		this.versionType = versionType;
	}

	public void setOsType(OSEnum osType) {
		this.osType = osType;
	}

	public void setProject(MavenProject project) {
		this.project = project;
	}

	public Boolean getDeploy() {
		return deploy;
	}

	public void setDeploy(Boolean deploy) {
		this.deploy = deploy;
	}

	public String getSvnMessage() {
		return svnMessage;
	}
	
	public void setSvnMessage(String svnMessage) {
		this.svnMessage = svnMessage;
	}

	public Boolean getCommitSvn() {
		return commitSvn;
	}

	public void setCommitSvn(Boolean commitSvn) {
		this.commitSvn = commitSvn;
	}

	public String getSvnPassword() {
		return svnPassword;
	}

	public void setSvnPassword(String svnPassword) {
		this.svnPassword = svnPassword;
	}

	public String getSvnUser() {
		return svnUser;
	}

	public void setSvnUser(String svnUser) {
		this.svnUser = svnUser;
	}
	
	public static void main(String[] args) {
		String userHome = System.getProperty("user.home");
		File pluginDir = new File(userHome, ".brmtmp");
		if (!pluginDir.exists()) {
			pluginDir.mkdirs();
		}
		System.out.println(pluginDir);
	}
	
}