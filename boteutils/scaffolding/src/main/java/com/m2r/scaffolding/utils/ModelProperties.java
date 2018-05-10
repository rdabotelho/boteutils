package com.m2r.scaffolding.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.m2r.scaffolding.wrapper.ModelClass;
import com.m2r.scaffolding.wrapper.ModelField;

public class ModelProperties {

	private Map<String, String> map;

	public ModelProperties() {
		map = new LinkedHashMap<>();
	}
	
	public ModelClass convertToModelBase(String baseDir, String basePackage) {
		String modelSimpleName = NameUtils.modelPropertyToClassName(map.get(ModelPropertiesEnum.MODEL_NAME.name()));
		String modelName = basePackage + ".model." +  modelSimpleName;
		String tableName = map.get(ModelPropertiesEnum.MODEL_TABLE.name());
		String tableLabel = map.get(ModelPropertiesEnum.MODEL_LABEL.name());
		String icon = map.get(ModelPropertiesEnum.MODEL_ICON.name()); 
		ModelClass modelClass = new ModelClass(baseDir, basePackage, modelName, tableName, modelSimpleName, tableLabel, icon);
		
		int id = 0;
		String name = map.get(ModelPropertiesEnum.MODEL_PROPERTY_NAME.name() + "_" + id);
		while (name != null) {
			Class<?> type = strToType(map.get(ModelPropertiesEnum.MODEL_PROPERTY_TYPE.name() + "_" + id));
			String label = map.get(ModelPropertiesEnum.MODEL_PROPERTY_LABEL.name() + "_" + id);
			String columnName = map.get(ModelPropertiesEnum.MODEL_PROPERTY_COLUMN_NAME.name() + "_" + id);
			boolean isFilter = strToBoolean(map.get(ModelPropertiesEnum.MODEL_PROPERTY_FILTER.name() + "_" + id));
			boolean isViewed = strToBoolean(map.get(ModelPropertiesEnum.MODEL_PROPERTY_VIEWED.name() + "_" + id));
			boolean isText = strToBoolean(map.get(ModelPropertiesEnum.MODEL_PROPERTY_TYPE.name() + "_" + id));
			String enumName = map.get(ModelPropertiesEnum.MODEL_PROPERTY_ENUM_NAME.name() + "_" + id);
			String relatedModel = map.get(ModelPropertiesEnum.MODEL_PROPERTY_RELATED_MODEL.name() + "_" + id);
			Integer maxLength = strToInteger(map.get(ModelPropertiesEnum.MODEL_PROPERTY_LENGTH.name() + "_" + id));
			boolean isDisabled = strToBoolean(map.get(ModelPropertiesEnum.MODEL_PROPERTY_DISABLED.name() + "_" + id));
			String decimalPlaces = map.get(ModelPropertiesEnum.MODEL_PROPERTY_DECIMAL_PLACES.name() + "_" + id);
			String decimalSeparator = map.get(ModelPropertiesEnum.MODEL_PROPERTY_DECIMAL_SEPARATOR.name() + "_" + id);
			String symbol = map.get(ModelPropertiesEnum.MODEL_PROPERTY_DECIMAL_SYMBOL.name() + "_" + id);
			String pattern = map.get(ModelPropertiesEnum.MODEL_PROPERTY_DECIMAL_PATTERN.name() + "_" + id);
			boolean isRequired = strToBoolean(map.get(ModelPropertiesEnum.MODEL_PROPERTY_REQUIRED.name() + "_" + id));
			boolean isViewedOnTable = strToBoolean(map.get(ModelPropertiesEnum.MODEL_PROPERTY_VIEWED_ON_TABLE.name() + "_" + id));
			String columnWidth = map.get(ModelPropertiesEnum.MODEL_PROPERTY_COLUMN_WIDTH.name() + "_" + id);
			Integer percision = strToInteger(map.get(ModelPropertiesEnum.MODEL_PROPERTY_DECIMAL_PRECISION.name() + "_" + id));
			Integer scale = strToInteger(map.get(ModelPropertiesEnum.MODEL_PROPERTY_DECIMAL_SCALE.name() + "_" + id));
			String itemLabel = map.get(ModelPropertiesEnum.MODEL_PROPERTY_SELECT_ITEM_LABEL.name() + "_" + id);
			
			ModelField modelField = new ModelField(name, type, label, columnName, isFilter, isViewed, isText, enumName, relatedModel,maxLength, isDisabled, decimalPlaces, decimalSeparator, symbol, pattern, isRequired, isViewedOnTable, columnWidth, percision, scale, itemLabel);
			modelClass.addViewedField(modelField);
			
			id++;
			name = map.get(ModelPropertiesEnum.MODEL_PROPERTY_NAME.name() + "_" + id);
		}
		modelClass.extractImports();
		return modelClass;
	}
	
	private Class<?> strToType(String str) {
		switch (str) {
			case "0": return String.class;
			case "1": return String.class;
			case "2": return Integer.class;
			case "3": return Long.class;
			case "4": return BigInteger.class;
			case "5": return Float.class;
			case "6": return Double.class;
			case "7": return BigDecimal.class;
			case "8": return LocalDate.class;
			case "9": return LocalTime.class;
			case "10": return LocalDateTime.class;
			case "11": return ScaffoldEnum.class;
			case "12": return ScaffoldModel.class;
			case "13": return Set.class;
			case "14": return List.class;
		}
		return null;
	}
	
	private boolean strToBoolean(String str) {
		return "1".equals(str);
	}
	
	private Integer strToInteger(String str) {
		return str != null && !str.equals("") ? Integer.parseInt(str) : null;
	}
	
	public Map<String, String> getMap() {
		return map;
	}
	
	public boolean promptProperties() {
		int id = 0;
		int i = 0;
		boolean uncomplete = true;
		while (i < ModelPropertiesEnum.values().length) {
			ModelPropertiesEnum prop = ModelPropertiesEnum.values()[i]; 
			
			if (ModelPropertiesEnum.ABORTED.equals(prop)) {
				break;
			}

			if (ModelPropertiesEnum.ENDED.equals(prop)) {
				break;
			}
			
			if (prop.isShow(map)) {
				String option = prop.getPrompt(map);
				String optionResult = ConsoleReader.readFromConsole(option);
				try {
					ModelPropertiesEnum nextProp = prop.treatPromptAndGoTo(optionResult, map);
					
					if (ModelPropertiesEnum.MODEL_PROPERTY_VIEWED_ON_TABLE.equals(prop)) {
						for (int e=ModelPropertiesEnum.MODEL_PROPERTY_NAME.ordinal(); e<ModelPropertiesEnum.ENDED.ordinal(); e++) {
							ModelPropertiesEnum prop2 = ModelPropertiesEnum.values()[e]; 
							map.put(prop2.name() + "_" + id, map.get(prop2.name()));
							map.put(prop2.name(), null);
						}
						id++;
					}
					
					if (nextProp == null) {
						break;
					}
					
					i = nextProp.ordinal();
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
			}
			else {
				i++;
			}
			
			if (ModelPropertiesEnum.MODEL_ICON.equals(prop)) {
				i = ModelPropertiesEnum.REPEAT_PROPERTY_BEGIN.ordinal();
				uncomplete = false;
			}
		}
		return !uncomplete;
	}
	
	enum ModelPropertiesEnum {
		
		// Model Data
		MODEL_NAME("", true),
		MODEL_TABLE("", true){
			@Override
			public String getDefaultValue(Map<String, String> map) {
				return NameUtils.modelPropertyToEnum(NameUtils.modelNameToModelProperty(map.get(MODEL_NAME.name())));
			}
		},
		MODEL_LABEL("", true){
			@Override
			public String getDefaultValue(Map<String, String> map) {
				return NameUtils.modelPropertyToLabelValue(NameUtils.modelNameToModelProperty(map.get(MODEL_NAME.name())));
			}
		},
		MODEL_ICON("fa-file-o", true),
		
		// Properties Data
		MODEL_PROPERTY_NAME("", true),
		MODEL_PROPERTY_LABEL("", true){
			@Override
			public String getDefaultValue(Map<String, String> map) {
				return NameUtils.modelPropertyToLabelValue(NameUtils.modelNameToModelProperty(map.get(MODEL_PROPERTY_NAME.name())));
			}
		},
		MODEL_PROPERTY_COLUMN_NAME("", true){
			@Override
			public String getDefaultValue(Map<String, String> map) {
				return NameUtils.modelPropertyToEnum(NameUtils.modelNameToModelProperty(map.get(MODEL_PROPERTY_NAME.name())));
			}
		},
		MODEL_PROPERTY_TYPE("0", true){
			@Override
			public String getPrompt(Map<String, String> map) {
				StringBuilder str = new StringBuilder();
				str.append(ConsoleReader.INFO).append("Which TYPE OF PROPERTY do you want to create?\n")
					.append(ConsoleReader.INFO).append("0) String\n")
					.append(ConsoleReader.INFO).append("1) String (Long Text)\n")
					.append(ConsoleReader.INFO).append("2) Integer\n")
					.append(ConsoleReader.INFO).append("3) Integer (Long)\n")
					.append(ConsoleReader.INFO).append("4) Integer (BigInteger)\n")
					.append(ConsoleReader.INFO).append("5) Decimal (Float)\n")
					.append(ConsoleReader.INFO).append("6) Decimal (Double)\n")
					.append(ConsoleReader.INFO).append("7) Decimal (BigDecimal)\n")
					.append(ConsoleReader.INFO).append("8) Date (LocalDate)\n")
					.append(ConsoleReader.INFO).append("9) Date (LocalTime)\n")
					.append(ConsoleReader.INFO).append("10) Date (LocalDateTime)\n")
					.append(ConsoleReader.INFO).append("11) Enumeration\n")
					.append(ConsoleReader.INFO).append("12) Model\n")
					.append(ConsoleReader.INFO).append("13) Collection (Set)\n")
					.append(ConsoleReader.INFO).append("14) Collection (List)\n")
					.append(ConsoleReader.INFO).append("15) ABORT\n")
					.append(ConsoleReader.INFO).append("(0): ");
				return str.toString();
			}	
			@Override
			public ModelPropertiesEnum treatPromptAndGoTo(String optionResult, Map<String, String> map) {
				return savePromptResult(optionResult, map, 15, true, MODEL_PROPERTY_ENUM_NAME);
			}
		},
		MODEL_PROPERTY_ENUM_NAME("", true){
			@Override
			public boolean isShow(Map<String, String> map) {
				return isType(map.get(MODEL_PROPERTY_TYPE.name()), "11");
			}						
		},
		MODEL_PROPERTY_RELATED_MODEL("", true){
			@Override
			public boolean isShow(Map<String, String> map) {
				return isType(map.get(MODEL_PROPERTY_TYPE.name()), "12", "13", "14");
			}						
		},
		MODEL_PROPERTY_SELECT_ITEM_LABEL("item", true){
			@Override
			public boolean isShow(Map<String, String> map) {
				return isType(map.get(MODEL_PROPERTY_TYPE.name()), "13", "14");
			}						
		},
		MODEL_PROPERTY_LENGTH("", false){
			@Override
			public boolean isShow(Map<String, String> map) {
				return isType(map.get(MODEL_PROPERTY_TYPE.name()), "0", "1");
			}
		},
		MODEL_PROPERTY_DECIMAL_PRECISION("19", false){
			@Override
			public boolean isShow(Map<String, String> map) {
				return isType(map.get(MODEL_PROPERTY_TYPE.name()), "5", "6", "7");
			}			
		},
		MODEL_PROPERTY_DECIMAL_SCALE("8", false){
			@Override
			public boolean isShow(Map<String, String> map) {
				return isType(map.get(MODEL_PROPERTY_TYPE.name()), "5", "6", "7");
			}			
		},
		MODEL_PROPERTY_DECIMAL_PLACES("", false){
			@Override
			public boolean isShow(Map<String, String> map) {
				return isType(map.get(MODEL_PROPERTY_TYPE.name()), "5", "6", "7");
			}			
		},
		MODEL_PROPERTY_DECIMAL_SEPARATOR("", false){
			@Override
			public boolean isShow(Map<String, String> map) {
				return isType(map.get(MODEL_PROPERTY_TYPE.name()), "5", "6", "7");
			}						
		},
		MODEL_PROPERTY_DECIMAL_SYMBOL("", false){
			@Override
			public boolean isShow(Map<String, String> map) {
				return isType(map.get(MODEL_PROPERTY_TYPE.name()), "5", "6", "7");
			}						
		},
		MODEL_PROPERTY_DECIMAL_PATTERN("", false){
			@Override
			public boolean isShow(Map<String, String> map) {
				return isType(map.get(MODEL_PROPERTY_TYPE.name()), "5", "6", "7", "8", "9", "10");
			}						
		},
		MODEL_PROPERTY_COLUMN_WIDTH("", false),
		MODEL_PROPERTY_REQUIRED("1", false){
			@Override
			public String getPrompt(Map<String, String> map) {
				StringBuilder str = new StringBuilder();
				str.append(ConsoleReader.INFO).append("Is the property REQUIRED?\n")
					.append(ConsoleReader.INFO).append("0) No\n")
					.append(ConsoleReader.INFO).append("1) Yes\n")
					.append(ConsoleReader.INFO).append("3) ABORT\n")
					.append(ConsoleReader.INFO).append("(1): ");
				return str.toString();
			}	
			@Override
			public ModelPropertiesEnum treatPromptAndGoTo(String optionResult, Map<String, String> map) {
				return savePromptResult(optionResult, map, 3, true, MODEL_PROPERTY_DISABLED);
			}
		},
		MODEL_PROPERTY_DISABLED("0", false){
			@Override
			public String getPrompt(Map<String, String> map) {
				StringBuilder str = new StringBuilder();
				str.append(ConsoleReader.INFO).append("Is the property DISABLED?\n")
					.append(ConsoleReader.INFO).append("0) No\n")
					.append(ConsoleReader.INFO).append("1) Yes\n")
					.append(ConsoleReader.INFO).append("3) ABORT\n")
					.append(ConsoleReader.INFO).append("(0): ");
				return str.toString();
			}	
			@Override
			public ModelPropertiesEnum treatPromptAndGoTo(String optionResult, Map<String, String> map) {
				return savePromptResult(optionResult, map, 3, true, MODEL_PROPERTY_FILTER);
			}
		},
		MODEL_PROPERTY_FILTER("0", false){
			@Override
			public String getPrompt(Map<String, String> map) {
				StringBuilder str = new StringBuilder();
				str.append(ConsoleReader.INFO).append("Is the property FILTER?\n")
					.append(ConsoleReader.INFO).append("0) No\n")
					.append(ConsoleReader.INFO).append("1) Yes\n")
					.append(ConsoleReader.INFO).append("3) ABORT\n")
					.append(ConsoleReader.INFO).append("(0): ");
				return str.toString();
			}	
			@Override
			public ModelPropertiesEnum treatPromptAndGoTo(String optionResult, Map<String, String> map) {
				return savePromptResult(optionResult, map, 3, true, MODEL_PROPERTY_VIEWED);
			}
		},
		MODEL_PROPERTY_VIEWED("1", false){
			@Override
			public String getPrompt(Map<String, String> map) {
				StringBuilder str = new StringBuilder();
				str.append(ConsoleReader.INFO).append("Is the property VIEWED?\n")
					.append(ConsoleReader.INFO).append("0) No\n")
					.append(ConsoleReader.INFO).append("1) Yes\n")
					.append(ConsoleReader.INFO).append("3) ABORT\n")
					.append(ConsoleReader.INFO).append("(1): ");
				return str.toString();
			}	
			@Override
			public ModelPropertiesEnum treatPromptAndGoTo(String optionResult, Map<String, String> map) {
				return savePromptResult(optionResult, map, 3, true, MODEL_PROPERTY_VIEWED_ON_TABLE);
			}
		},
		MODEL_PROPERTY_VIEWED_ON_TABLE("1", false){
			@Override
			public String getPrompt(Map<String, String> map) {
				StringBuilder str = new StringBuilder();
				str.append(ConsoleReader.INFO).append("Is the property VIEWED ON TABLE?\n")
					.append(ConsoleReader.INFO).append("0) No\n")
					.append(ConsoleReader.INFO).append("1) Yes\n")
					.append(ConsoleReader.INFO).append("3) ABORT\n")
					.append(ConsoleReader.INFO).append("(1): ");
				return str.toString();
			}	
			@Override
			public ModelPropertiesEnum treatPromptAndGoTo(String optionResult, Map<String, String> map) {
				return savePromptResult(optionResult, map, 3, true, REPEAT_PROPERTY_BEGIN);
			}
		},
		REPEAT_PROPERTY_BEGIN("1", false){
			@Override
			public String getPrompt(Map<String, String> map) {
				StringBuilder str = new StringBuilder();
				str.append(ConsoleReader.INFO).append("Do you want to create a new PROPERTY?\n")
					.append(ConsoleReader.INFO).append("0) No\n")
					.append(ConsoleReader.INFO).append("1) Yes\n")
					.append(ConsoleReader.INFO).append("(1): ");
				return str.toString();
			}
			@Override
			public ModelPropertiesEnum treatPromptAndGoTo(String optionResult, Map<String, String> map) {
				ModelPropertiesEnum result = savePromptResult(optionResult, map, 2, false, MODEL_PROPERTY_NAME);
				if (MODEL_PROPERTY_NAME.equals(result) && optionResult.equals("0")) {
					return ModelPropertiesEnum.ENDED;
				}
				return result;
			}			
		},
		ENDED("", false),
		ABORTED("", false);
		
		private String defaultValue;
		private boolean required;
		
		ModelPropertiesEnum(String defaultValue, boolean required) {
			this.defaultValue = defaultValue;
			this.required = required;
		}

		public String getPropertyName() {
			return NameUtils.modelPropertyToLabelValue(NameUtils.enumToModelProperty(name()));
		}
		
		public String getDefaultValue(Map<String, String> map) {
			return this.defaultValue;
		}
		
		public String getPrompt(Map<String, String> map) {
			String option = getDefaultValue(map);
			return ConsoleReader.INFO + "What is the " + getPropertyName().toUpperCase() + "?\n" + (option != null && !option.equals("") ? "(" + option + "): " : "" );
		}
		
		public ModelPropertiesEnum treatPromptAndGoTo(String optionResult, Map<String, String> map) {
			if (optionResult == null || optionResult.equals("")) {
				String dv = getDefaultValue(map);
				if (dv.equals("") && required) {
					throw new RuntimeException("Value do not permited!");
				}
				optionResult = dv;
			}
			map.put(this.name(), optionResult);
			return values()[ordinal() + 1];
		}
		
		public boolean isShow(Map<String, String> map) {
			return true;
		}
		
		protected ModelPropertiesEnum savePromptResult(String optionResult, Map<String, String> map, int maxOption, boolean hasAbortOption, ModelPropertiesEnum success) {
			if (optionResult == null || optionResult.equals("")) {
				optionResult = getDefaultValue(map);
			}
			try {
				map.put(this.name(), optionResult);
				int code = Integer.parseInt(optionResult);
				if (hasAbortOption && code == maxOption) {
					return ABORTED; // exit
				}
				if (code >=0 && code <= maxOption) {
					return success;
				}
			}
			catch (Exception e) {
			}
			throw new RuntimeException("Value do not permited!");			
		}
		
		protected boolean isType(String value, String ... codes) {
			for (String code : codes) {
				if (code.equals(value)) {
					return true;
				}
			}
			return false;
		}
		
	}
	
}
