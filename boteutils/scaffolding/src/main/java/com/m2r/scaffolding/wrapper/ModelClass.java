package com.m2r.scaffolding.wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ModelClass {
	
	protected String baseDir;
	protected String basePackage;
	protected String modelName;
	protected String tableName;
	protected String modelSimpleName;
	protected String label;
	protected String icon;
	protected List<ModelField> viewedFields;
	
	private Set<String> imports;

	public ModelClass(String baseDir, String basePackage, String modelName, String tableName, String modelSimpleName, String label, String icon) {
		this.baseDir = baseDir;
		this.basePackage = basePackage;
		this.modelName = modelName;
		this.tableName = tableName;
		this.modelSimpleName = modelSimpleName;
		this.label = label;
		this.icon = icon;
		this.viewedFields = new ArrayList<>();
		this.imports = new TreeSet<>();
	}

	public String getBaseDir() {
		return baseDir;
	}
	
	public String getSourceDir() {
		return getBaseDir() + "/src/main/java";
	}
	
	public String getResourceDir() {
		return getBaseDir() + "/src/main/resources";
	}
	
	public String getViewDir() {
		return getBaseDir() + "/src/main/webapp";
	}
	
	public String getBasePackage() {
		return basePackage;
	}
	
	public String getModelName() {
		return modelName;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getName() {
		return this.modelName;
	}

	public String getSimpleName() {
		return this.modelSimpleName;
	}
	
	/*
	 * Model
	 */
	public String getModelPath() {
		return getSourceDir() + "/" + getModelPackage().replaceAll("\\.", "/") + "/" + modelSimpleName + ".java";
	}
	
	public String getModelPackage() {
		return this.basePackage + ".model";
	}
	
	public void addViewedField(ModelField field) {
		if (!field.getType().getPackage().getName().equals(getBasePackage())) {
			imports.add(field.getType().getName());
		}
		getViewedFields().add(field);
	}
	
	public List<ModelField> getViewedFields() {
		return viewedFields;
	}
	
	public String getModelInstanceName() {
		return getSimpleName().substring(0, 1).toLowerCase() + getSimpleName().substring(1);
	}
	
	public Set<String> getImports() {
		return imports;
	}

}
