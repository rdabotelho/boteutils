package com.m2r.scaffolding.wrapper;

import java.lang.reflect.Field;
import java.util.List;

import com.m2r.scaffolding.utils.ClassScaffold;
import com.m2r.scaffolding.utils.MargeUtils;
import com.m2r.scaffolding.utils.NameUtils;

public class ModelClassWrapper extends ModelClass {

	private Class<?> modelClass;
	
	private int[] seachColumns = new int[] {0, 2};
	private int[] editColumns = new int[] {0, 2};

	public ModelClassWrapper(Class<?> modelClass, String baseDir, String basePackage) {
		super(baseDir, basePackage, modelClass.getName(), "", modelClass.getSimpleName(), "", "");
		this.modelClass = modelClass;
		this.init();
	}
	
	private void init() {
		ClassScaffold classScaffold = this.getRealModelClass().getAnnotation(ClassScaffold.class);
		if (classScaffold != null) {
			label = classScaffold.label();
			icon = classScaffold.icon();
		}
		for (Field field : MargeUtils.getAllModelFields(this.modelClass)) {
			ModelFieldAdapter modelFieldAdapter = new ModelFieldAdapter(field);
			if (modelFieldAdapter.isViewed()) {
				editColumns[0] = editColumns[0] + 1;
			}
			if (modelFieldAdapter.isFilter()) {
				seachColumns[0] = seachColumns[0] + 1;
			}
			viewedFields.add(modelFieldAdapter);
		}
		 
		seachColumns[1] = seachColumns[0] < 3 ? 2 : (seachColumns[0] == 3 ? 4 : 3); 
		editColumns[1] = editColumns[0] < 3 ? 2 : (editColumns[0] == 3 ? 4 : 3);
		if (seachColumns[0] > 4) {
			seachColumns[0] = 4;
		}
		if (editColumns[0] > 4) {
			editColumns[0] = 4;
		}
	}
	
	public Class<?> getRealModelClass() {
		return modelClass;
	}
	
	public String getBasePackage() {
		return basePackage;
	}
	
	public String getName() {
		return this.modelClass.getName();
	}

	public String getSimpleName() {
		return this.modelClass.getSimpleName();
	}
	public Integer getSeachColumnNumber() {
		return seachColumns[0];
	}
	
	public Integer getSeachColumnClassesNumber() {
		return seachColumns[1];
	}
	
	public Integer getEditColumnNumber() {
		return editColumns[0];
	}
	
	public Integer getEditColumnClassesNumber() {
		return editColumns[1];
	}
	
	/*
	 * Model
	 */
	public List<ModelField> getViewedFields() {
		return viewedFields;
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
