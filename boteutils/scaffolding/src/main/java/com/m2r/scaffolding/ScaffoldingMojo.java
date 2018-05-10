package com.m2r.scaffolding;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.m2r.scaffolding.utils.ConsoleReader;
import com.m2r.scaffolding.utils.ModelProperties;

@Mojo(name = "run")
public class ScaffoldingMojo extends AbstractMojo {

	@Parameter(property = "baseDir")
	private String baseDir;
	
	@Parameter(property = "basePackage")
	private String basePackage;
	
	@Parameter(property = "model")
	private String model;
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		if (baseDir == null) {
			throw new MojoExecutionException("Parameter baseDir doesn't defined!");
		}
		if (basePackage == null) {
			throw new MojoExecutionException("Parameter basePackage doesn't defined!");
		}
		
		Scaffolding scaffolding = new Scaffolding(this, baseDir, basePackage);
		
		StringBuilder str = new StringBuilder();
		str.append("[INFO] --- Which kind of generation do you want?\n")
			.append("[INFO] --- 0) All (repository, service, controller, label properties, view)\n")
			.append("[INFO] --- 1) Model\n")
			.append("[INFO] --- 2) View\n")
			.append("[INFO] --- 3) Nothing\n")
			.append("[INFO] --- (0): ");
		String line = ConsoleReader.readFromConsole(str.toString(), "0");
		
		switch (line) {
		case "0": 
			if (model == null) {
				model = ConsoleReader.readFromConsole("[INFO] --- Enter with the Model: ");	
				if (model == null || model.equals("")) {
					throw new MojoExecutionException("Parameter model doesn't defined!");
				}
			}		
			scaffolding.configureEnviroment(model);
			generateAll(scaffolding);
			break;
		case "2": 
			if (model == null) {
				model = ConsoleReader.readFromConsole("[INFO] --- Enter with the Model: ");	
				if (model == null || model.equals("")) {
					throw new MojoExecutionException("Parameter model doesn't defined!");
				}
			}		
			scaffolding.configureEnviroment(model);
			generateView(scaffolding);;
			break;
		case "1": 
			ModelProperties modelProperties = new ModelProperties();
			if (modelProperties.promptProperties()) {
				scaffolding.configureEnviroment(modelProperties);
				generateModel(scaffolding);
			}
			break;
		}
	}
	
	private void generateModel(Scaffolding scaffolding) throws MojoExecutionException, MojoFailureException {
		scaffolding.generateModel();
	}
	
	private void generateAll(Scaffolding scaffolding) throws MojoExecutionException, MojoFailureException {
		scaffolding.generateRepository();		
		scaffolding.generateService();
		scaffolding.generateController();
		scaffolding.generateLabelProperties();
		scaffolding.generateView();		
	}
	
	private void generateView(Scaffolding scaffolding) throws MojoExecutionException, MojoFailureException {
		scaffolding.generateView();				
	}
	
}
