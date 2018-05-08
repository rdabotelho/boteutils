package com.m2r.scaffolding;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "run")
public class ScaffoldingMojo extends AbstractMojo {

	@Parameter(property = "baseDir")
	private String baseDir;
	
	@Parameter(property = "basePackage")
	private String basePackage;
	
	@Parameter(property = "model")
	private String model;
	
	private void validateParams() throws MojoExecutionException, MojoFailureException {
		if (baseDir == null) {
			throw new MojoExecutionException("Parameter baseDir doesn't defined!");
		}
		if (basePackage == null) {
			throw new MojoExecutionException("Parameter basePackage doesn't defined!");
		}
		if (model == null) {
			model = readFromConsole("[INFO] --- Enter with the Model: ");	
			if (model == null || model.equals("")) {
				throw new MojoExecutionException("Parameter model doesn't defined!");
			}
		}		
	}
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		validateParams();
		Scaffolding scaffolding = new Scaffolding(this, baseDir, basePackage);
		scaffolding.configureEnviroment(model);
		scaffolding.generateRepository();		
		scaffolding.generateService();
		scaffolding.generateController();
		scaffolding.generateLabelProperties();
		scaffolding.generateView();
	}
	
    private static String readFromConsole(String prompt) {
		try {
	    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	    		System.out.print(prompt);
	    		return br.readLine();
		}
		catch (Exception e) {
			return null;
		}
    }	

}
