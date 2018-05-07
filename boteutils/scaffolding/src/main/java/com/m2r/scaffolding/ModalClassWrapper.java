package com.m2r.scaffolding;

public class ModalClassWrapper {

	private String baseDir;
	private Class<?> modelClass;

	public ModalClassWrapper(Class<?> modelClass, String baseDir) {
		this.modelClass = modelClass;
		this.baseDir = baseDir;
	}
	
	public Class<?> getRealModelClass() {
		return modelClass;
	}
	
	public String getBaseDir() {
		return baseDir;
	}
	
	public String getSourceDir() {
		return baseDir + "/src/main/java";
	}
	
	public String getResourceDir() {
		return baseDir + "/src/main/resources";
	}
	
	public String getName() {
		return this.modelClass.getName();
	}

	public String getSimpleName() {
		return this.modelClass.getSimpleName();
	}

	public String getRepositoryPackage() {
		return this.modelClass.getPackage().getName().replace(".model", ".repository");
	}
	
	public String getRepositorySimpleName() {
		return getSimpleName() + "Repository";
	}
	
	public String getRepositoryPath() {
		return getSourceDir() + "/" + getRepositoryPackage().replaceAll("\\.", "/") + "/" + getRepositorySimpleName() + ".java";
	}
	
}
