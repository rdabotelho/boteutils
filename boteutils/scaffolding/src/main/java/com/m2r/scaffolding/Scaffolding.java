package com.m2r.scaffolding;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Files;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class Scaffolding {

	private String baseDir;
	private String packageDir;
	
	private ModalClassWrapper modelClass;;	
	
	public Scaffolding(String baseDir, String packageDir) {
		this.baseDir = baseDir;
		this.packageDir = packageDir;
	}

	public void generateRepository(String model) {
		modelClass = new ModalClassWrapper(loadModelClass(model), baseDir);
		if (modelClass == null) {
			return;
		}
		try {
			String fileName = getModelClass().getRepositoryPath();
			this.margeAndSaveFromTemplate("repository.vm", fileName);
			System.out.println("Created repository: " + fileName);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveTemplateInTempDir(String templateName) throws Exception {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("templates/"+templateName);
    	    byte[] buffer = new byte[in.available()];
    	    in.read(buffer);
    	    File targetFile = new File(getTempDir() + "/" + templateName);
    	    	Files.write(targetFile.toPath(), buffer);
    	}
	
	private String getTempDir() {
		return System.getProperty("java.io.tmpdir");
	}
	
	private String getModelPackage() {
		return (packageDir + "/model").replaceAll("/", ".");
	}
	
	public Class<?> loadModelClass(String model) {
		ClassLoader classLoader = this.getClass().getClassLoader();
		try {
			return classLoader.loadClass(getModelPackage() + "." + model);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ModalClassWrapper getModelClass() {
		return modelClass;
	}

	private void margeAndSaveFromTemplate(String templateName, String fileName) throws Exception {	
		saveTemplateInTempDir(templateName);
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.addProperty("file.resource.loader.path", getTempDir());
		velocityEngine.init();
		Template template = velocityEngine.getTemplate(templateName);
		VelocityContext context = new VelocityContext();
		context.put("modelClass", getModelClass());
		FileWriter writer = new FileWriter(fileName);
		template.merge(context, writer);
		writer.close();
	}
	
}
