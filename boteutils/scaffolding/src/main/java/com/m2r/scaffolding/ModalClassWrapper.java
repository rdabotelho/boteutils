package com.m2r.scaffolding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModalClassWrapper {

	private String baseDir;
	private String basePackage;
	private Class<?> modelClass;
	private List<FieldAdapter> viewedFields;

	public ModalClassWrapper(Class<?> modelClass, String baseDir, String basePackage) {
		this.modelClass = modelClass;
		this.baseDir = baseDir;
		this.basePackage = basePackage;
		this.init();
	}
	
	private void init() {
		viewedFields = new ArrayList<FieldAdapter>();
		for (Field field : getDeclaredFields()) {
			viewedFields.add(new FieldAdapter(field));
		}
	}
	
	public Class<?> getRealModelClass() {
		return modelClass;
	}
	
	public String getBaseDir() {
		return baseDir;
	}
	
	public String getBasePackage() {
		return basePackage;
	}
	
	public String getSourceDir() {
		return baseDir + "/src/main/java";
	}
	
	public String getResourceDir() {
		return baseDir + "/src/main/resources";
	}
	
	public String getViewDir() {
		return baseDir + "/src/main/webapp";
	}
	
	public String getName() {
		return this.modelClass.getName();
	}

	public String getSimpleName() {
		return this.modelClass.getSimpleName();
	}
	
	/*
	 * Model
	 */
	public List<FieldAdapter> getViewedFields() {
		return viewedFields;
	}
	
	public Field[] getDeclaredFields() {
		return this.modelClass.getDeclaredFields();
	}

	public Field[] getFields() {
		return this.modelClass.getFields();
	}

	public String getModelInstanceName() {
		return modelClass.getSimpleName().substring(0, 1).toLowerCase() + modelClass.getSimpleName().substring(1);
	}
	
	/*
	 * Repository
	 */
	public String getRepositoryPath() {
		return getSourceDir() + "/" + getRepositoryPackage().replaceAll("\\.", "/") + "/" + getRepositorySimpleName() + ".java";
	}
	
	public String getRepositoryPackage() {
		return this.modelClass.getPackage().getName().replace(".model", ".repository");
	}
	
	public String getRepositorySimpleName() {
		return getSimpleName() + "Repository";
	}
	
	public String getRepositoryName() {
		return this.getRepositoryPackage() + "." + getRepositorySimpleName();
	}
	
	public String getRepositoryInstanceName() {
		return getRepositorySimpleName().substring(0, 1).toLowerCase() + getRepositorySimpleName().substring(1);
	}
	
	/*
	 * Service
	 */
	public String getServicePath() {
		return getSourceDir() + "/" + getServicePackage().replaceAll("\\.", "/") + "/" + getServiceSimpleName() + ".java";
	}
	
	public String getServicePackage() {
		return this.modelClass.getPackage().getName().replace(".model", ".service");
	}
	
	public String getServiceSimpleName() {
		return getSimpleName() + "Service";
	}
	
	public String getServiceName() {
		return this.getServicePackage() + "." + getServiceSimpleName();
	}
	
	public String getServiceInstanceName() {
		return getServiceSimpleName().substring(0, 1).toLowerCase() + getServiceSimpleName().substring(1);
	}
	
	/*
	 * Controller
	 */
	public String getControllerPath() {
		return getSourceDir() + "/" + getControllerPackage().replaceAll("\\.", "/") + "/" + getControllerSimpleName() + ".java";
	}
	
	public String getControllerPackage() {
		return this.modelClass.getPackage().getName().replace(".model", ".controller");
	}
	
	public String getControllerSimpleName() {
		return getSimpleName() + "Controller";
	}
	
	public String getControllerName() {
		return this.getControllerPackage() + "." + getControllerSimpleName();
	}
	
	public String getControllerInstanceName() {
		return getControllerSimpleName().substring(0, 1).toLowerCase() + getControllerSimpleName().substring(1);
	}
	
	/*
	 * View
	 */
	public String getViewPath() {
		return getViewDir() + "/pages/" + getViewSimpleName() + "/" + getViewSimpleName() + ".xhtml";
	}
	
	public String getViewSimpleName() {
		return NameUtils.modelPropertyToViewName(getModelInstanceName());
	}
	
	/*
	 * Label properties
	 */
	public String getLabelPropertiesPath() {
		return getResourceDir() + "/label_pt_BR.properties";
	}
	
	public String getLabelPropertiesPackage() {
		return this.modelClass.getPackage().getName().replace(".model", ".controller");
	}
	
	public String getLabelPropertiesSimpleName() {
		return getSimpleName() + "LabelProperties";
	}
	
	public String getLabelPropertiesName() {
		return this.getLabelPropertiesPackage() + "." + getLabelPropertiesSimpleName();
	}
	
	public String getLabelPropertiesInstanceName() {
		return getLabelPropertiesSimpleName().substring(0, 1).toLowerCase() + getLabelPropertiesSimpleName().substring(1);
	}
			
}
