package com.m2r.scaffolding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.m2r.scaffolding.utils.MargeUtils;
import com.m2r.scaffolding.utils.ModelProperties;
import com.m2r.scaffolding.wrapper.ModelClass;
import com.m2r.scaffolding.wrapper.ModelClassWrapper;

public class Scaffolding {

	private ScaffoldingMojo mojo;
	private String baseDir;
	private String basePackage;
	
	private ModelClass modelClass;
	
	private MargeUtils margeUtils = new MargeUtils();
	
	public Scaffolding(ScaffoldingMojo mojo, String baseDir, String basePackage) {
		this.mojo = mojo;
		this.baseDir = baseDir;
		this.basePackage = basePackage;
	}
	
	public void configureEnviroment(ModelProperties modelProperties) throws MojoExecutionException {
		modelClass = modelProperties.convertToModelBase(baseDir, basePackage);
	}
	
	public void configureEnviroment(String model) throws MojoExecutionException {
		modelClass = new ModelClassWrapper(loadModelClass(model), baseDir, basePackage);
	}

	public void generateModel() {
		try {
			File file = new File(getModelClass().getModelPath());
			if (file.exists()) {
				file = new File(file.getPath()+".tmp");
			}
			this.margeAndSaveFromTemplate(file, "model.vm");
			mojo.getLog().info("Created model: " + file.getPath());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void generateRepository() {
		try {
			File file = new File(getModelClassWrapper().getRepositoryPath());
			if (file.exists()) {
				file = new File(file.getPath()+".tmp");
			}
			this.margeAndSaveFromTemplate(file, "repository.vm");
			mojo.getLog().info("Created repository: " + file.getPath());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateService() {
		try {
			File file = new File(getModelClassWrapper().getServicePath());
			if (file.exists()) {
				file = new File(file.getPath()+".tmp");
			}
			this.margeAndSaveFromTemplate(file, "service.vm");
			mojo.getLog().info("Created service: " + file.getPath());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateController() {
		try {
			File file = new File(getModelClassWrapper().getControllerPath());
			if (file.exists()) {
				file = new File(file.getPath()+".tmp");
			}
			this.margeAndSaveFromTemplate(file, "controller.vm");
			mojo.getLog().info("Created controller: " + file.getPath());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateView() {
		try {
			File file = new File(getModelClassWrapper().getViewPath());
			if (file.exists()) {
				file = new File(file.getPath()+".tmp");
			}
			this.margeAndSaveFromTemplate(file, "view.vm", "inputs/input-string.vm", "inputs/input-date.vm", "inputs/input-decimal.vm", "inputs/input-integer.vm", "inputs/text-area.vm", "inputs/select-enum.vm", "inputs/select-model.vm");
			mojo.getLog().info("Created view: " + file.getPath());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateLabelProperties() {
		try {
			String fileName = getModelClassWrapper().getLabelPropertiesPath();
			FileInputStream in = new FileInputStream(fileName);
			Properties properties = new Properties();
			properties.load(in);
			in.close();
			fileName = fileName + ".tmp";
			Map<Object, Object> map = new TreeMap<>();
			for (Object key : properties.keySet()) {
				map.put(key, properties.get(key));
			}
			map.put(getModelClassWrapper().getModelInstanceName(), getModelClassWrapper().getLabel());
			for (Field field : getModelClassWrapper().getDeclaredFields()) {
				if (!map.containsKey(field.getName()) && !field.getName().equals("id")) {
					com.m2r.scaffolding.utils.FieldScaffold annotation = field.getAnnotation(com.m2r.scaffolding.utils.FieldScaffold.class);
					if (annotation != null && !annotation.label().equals("")) {
						map.put(field.getName(), annotation.label());
					}
					else {
						map.put(field.getName(), field.getName());						
					}
				}
			}
			String letter = "";
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.ISO_8859_1);
			for (Object key : map.keySet()) {
				if (!key.toString().substring(0,1).toUpperCase().equals(letter)) {
					if (!letter.equals("")) {
						writer.write("\n");
					}
					letter = key.toString().substring(0,1).toUpperCase();
					writer.write("# " + letter + " #\n");
				}
				writer.write(key.toString() + "=" + map.get(key).toString() + "\n");
			}
			writer.close();
			
			mojo.getLog().info("Created label properties: " + fileName);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveTemplatesInTempDir(String ... templatesNames) throws Exception {
		for (String templateName : templatesNames) {
			saveTemplateInTempDir(templateName);
		}
	}
	private void saveTemplateInTempDir(String templateName) throws Exception {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("templates/"+templateName);
	    byte[] buffer = new byte[in.available()];
	    in.read(buffer);
	    File targetFile = new File(getTempDir() + "/" + templateName);
	    if (!targetFile.getParentFile().exists()) {
	    	targetFile.getParentFile().mkdirs();
	    }
	    Files.write(targetFile.toPath(), buffer);
   	}
	
	private String getTempDir() {
		return System.getProperty("java.io.tmpdir");
	}
	
	private String getModelPackage() {
		return basePackage + ".model";
	}
	
	public Class<?> loadModelClass(String model) throws MojoExecutionException {
		String completeName = getModelPackage() + "." + model;
		ClassLoader classLoader = this.getClass().getClassLoader();
		try {
			return classLoader.loadClass(completeName);
		} 
		catch (ClassNotFoundException e) {
			throw new MojoExecutionException("Model not found: " + completeName);
		}
	}
	
	public ModelClass getModelClass() {
		return modelClass;
	}

	public ModelClassWrapper getModelClassWrapper() {
		return (ModelClassWrapper) getModelClass();
	}
	
	private void margeAndSaveFromTemplate(File file, String ... templatesNames) throws Exception {	
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		saveTemplatesInTempDir(templatesNames);
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.addProperty("file.resource.loader.path", getTempDir());
		velocityEngine.init();
		Template template = velocityEngine.getTemplate(templatesNames[0]);
		VelocityContext context = new VelocityContext();
		context.put("modelClass", getModelClass());
		context.put("utils", margeUtils);
		FileWriter writer = new FileWriter(file);
		template.merge(context, writer);
		writer.close();
	}
		
}
