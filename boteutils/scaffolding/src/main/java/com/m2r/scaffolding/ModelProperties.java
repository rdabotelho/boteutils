package com.m2r.scaffolding;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModelProperties {

	private Map<String, String> map;
	
	public ModelProperties() {
		map = new LinkedHashMap<>();
	}
	
	public void promptProperties() {
		int id = 0;
		int i = 0;
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
			}
		}
	}
	
	public static void main(String[] args) {
		ModelProperties m = new ModelProperties();
		m.promptProperties();
		System.out.println(m.map);
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
		MODEL_PROPERTY_TYPE("0", true){
			@Override
			public String getPrompt(Map<String, String> map) {
				StringBuilder str = new StringBuilder();
				str.append("Which TYPE OF PROPERTY do you want to create?\n")
					.append("0) String\n")
					.append("1) String (Long Text)\n")
					.append("2) Integer\n")
					.append("3) Integer (Long)\n")
					.append("4) Integer (BigInteger)\n")
					.append("5) Decimal (Float)\n")
					.append("6) Decimal (Double)\n")
					.append("7) Decimal (BigDecimal)\n")
					.append("8) Date (LocalDate)\n")
					.append("9) Date (LocalTime)\n")
					.append("10) Date (LocalDateTime)\n")
					.append("11) Enumeration\n")
					.append("12) ABORT\n")
					.append("(0): ");
				return str.toString();
			}	
			@Override
			public ModelPropertiesEnum treatPromptAndGoTo(String optionResult, Map<String, String> map) {
				return savePromptResult(optionResult, map, 12, true, MODEL_PROPERTY_LENGTH);
			}
		},
		MODEL_PROPERTY_LENGTH("", false){
			@Override
			public boolean isShow(Map<String, String> map) {
				return isType(map.get(MODEL_PROPERTY_TYPE.name()), "0", "1");
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
				return isType(map.get(MODEL_PROPERTY_TYPE.name()), "5", "6", "7", "9", "10");
			}						
		},
		MODEL_PROPERTY_COLUMN_WIDTH("", false),
		MODEL_PROPERTY_REQUIRED("1", false){
			@Override
			public String getPrompt(Map<String, String> map) {
				StringBuilder str = new StringBuilder();
				str.append("Is the property REQUIRED?\n")
					.append("0) No\n")
					.append("1) Yes\n")
					.append("3) ABORT\n")
					.append("(1): ");
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
				str.append("Is the property DISABLED?\n")
					.append("0) No\n")
					.append("1) Yes\n")
					.append("3) ABORT\n")
					.append("(0): ");
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
				str.append("Is the property FILTER?\n")
					.append("0) No\n")
					.append("1) Yes\n")
					.append("3) ABORT\n")
					.append("(0): ");
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
				str.append("Is the property VIEWED?\n")
					.append("0) No\n")
					.append("1) Yes\n")
					.append("3) ABORT\n")
					.append("(1): ");
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
				str.append("Is the property VIEWED ON TABLE?\n")
					.append("0) No\n")
					.append("1) Yes\n")
					.append("3) ABORT\n")
					.append("(1): ");
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
				str.append("Do you want to create a new PROPERTY?\n")
					.append("0) No\n")
					.append("1) Yes\n")
					.append("(1): ");
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
			return "What is the " + getPropertyName().toUpperCase() + "?\n" + (option != null && !option.equals("") ? "(" + option + "): " : "" );
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
