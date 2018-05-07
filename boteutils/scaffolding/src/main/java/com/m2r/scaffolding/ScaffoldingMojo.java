package com.m2r.scaffolding;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "run")
public class ScaffoldingMojo extends AbstractMojo {

	private static String baseDir = "/Users/botelho/kdi-BBTS/git_repository/elobato/elobato";
	private static String packageDir = "com/m2r/elobato";
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		Scaffolding scaffolding = new Scaffolding(baseDir, packageDir);
		scaffolding.generateRepository("Carro");		
	}

}
