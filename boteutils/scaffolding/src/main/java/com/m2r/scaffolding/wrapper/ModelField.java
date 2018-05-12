package com.m2r.scaffolding.wrapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import com.m2r.scaffolding.utils.ScaffoldEnum;
import com.m2r.scaffolding.utils.ScaffoldModel;

public class ModelField {
	
	protected String name;
	protected Class<?> type;
	protected String label;
	protected String columnName;
	protected boolean isFilter;
	protected boolean isViewed;
	protected boolean isText;
	protected String enumName;
	protected String relatedModel;
	protected Integer maxLength;
	protected boolean isDisabled;
	protected String decimalPlaces;
	protected String decimalSeparator;
	protected String symbol;
	protected String pattern;
	protected boolean isRequired;
	protected boolean isViewedOnTable;
	protected String columnWidth;
	protected Integer precision;
	protected Integer scale;
	protected String itemLabel;
	protected boolean selectWithFilter;
	protected boolean propertyTransient;

	public ModelField(String name, Class<?> type) {
		this.name = name;
		this.type = type;
	}
	
	public ModelField(String name, Class<?> type, String label, String columnName, boolean isFilter, boolean isViewed, boolean isText,
			String enumName, String relatedModel,
			Integer maxLength, boolean isDisabled, String decimalPlaces, String decimalSeparator, String symbol,
			String pattern, boolean isRequired, boolean isViewedOnTable, String columnWidth, Integer precision, 
			Integer scale, String itemLabel, boolean selectWithFilter, boolean propertyTransient) {
		this(name, type);
		this.label = label;
		this.columnName = columnName;
		this.isFilter = isFilter;
		this.isViewed = isViewed;
		this.isText = isText;
		this.enumName = enumName;
		this.relatedModel = relatedModel;
		this.maxLength = maxLength;
		this.isDisabled = isDisabled;
		this.decimalPlaces = decimalPlaces;
		this.decimalSeparator = decimalSeparator;
		this.symbol = symbol;
		this.pattern = pattern;
		this.isRequired = isRequired;
		this.isViewedOnTable = isViewedOnTable;
		this.columnWidth = columnWidth;
		this.precision = precision;
		this.scale = scale;
		this.itemLabel = itemLabel;
		this.selectWithFilter = selectWithFilter;
		this.propertyTransient = propertyTransient;
	}

	public String getName() {
		return name;
	}
	
	public Class<?> getType() {
		return type;
	}
	
	public String getTypeSimpleName() {
		if (isEnumType()) {
			return getEnumName();
		}
		else if (isModelType()) {
			return getRelatedModel();
		}	
		else if (isCollectionType()) {
			return getType().getSimpleName() + "<" + getRelatedModel() + ">";
		}	
		else {
			return getType().getSimpleName();			
		}
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getColumnName() {
		return columnName;
	}

	public boolean isFilter() {
		return isFilter;
	}
	
	public boolean isViewed() {
		return isViewed;
	}
	
	public Integer getMaxLength() {
		return maxLength;
	}
	
	public String getEnumName() {
		return enumName;
	}
	
	public String getRelatedModel() {
		return relatedModel;
	}
	
	public boolean isDisabled() {
		return isDisabled;
	}

	public String getDecimalPlaces() {
		return decimalPlaces;
	}

	public String getDecimalSeparator() {
		return decimalSeparator;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getPattern() {
		return pattern;
	}
	
	public boolean isMask() {
		return pattern != null;
	}

	public boolean isRequired() {
		return isRequired;
	}
	
	public boolean isNullable() {
		return !this.isRequired();
	}
	
	public boolean isViewedOnTable() {
		return isViewedOnTable;
	}
	
	public String getColumnWidth() {
		return columnWidth;
	}
	
	public Integer getPrecision() {
		return precision;
	}
	
	public Integer getScale() {
		return scale;
	}
	
	public String getItemLabel() {
		if (itemLabel == null || itemLabel.equals("")) {
			itemLabel = "item";
		}
		else if (!itemLabel.startsWith("item.")) {
			itemLabel = "item." + itemLabel;
		}
		return itemLabel;
	}
	
	public boolean isSelectWithFilter() {
		return selectWithFilter;
	}
	
	public boolean isPropertyTransient() {
		return propertyTransient;
	}
	
	public String getFilterMatchMode() {
		return isSelectWithFilter() ? "startsWith" : null;
	}

	public boolean isText() {
		return isText;
	}
	public String getGetMethodName() {
		return "get" + getName().substring(0, 1).toUpperCase() + getName().substring(1);
	}
	
	public String getSetMethodName() {
		return "set" + getName().substring(0, 1).toUpperCase() + getName().substring(1);
	}
	
	public String getNamedItems() {
		if (isEnumType()) {
			String simpleName = getType().getSimpleName().replaceAll("Enum", "");
			return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1) + "Items";
		}
		else {
			return getType().getSimpleName().substring(0, 1).toLowerCase() + getType().getSimpleName().substring(1) + "Items";
		}
	}
	
	public boolean isStringType() {
		return getType().equals(String.class) && !isText();
	}
	
	public boolean isTextType() {
		return getType().equals(String.class) && isText();
	}
	
	public boolean isIntegerType() {
		return getType().equals(Byte.class) || getType().equals(Short.class) || getType().equals(Integer.class) || getType().equals(Long.class) || getType().equals(BigInteger.class);
	}
	
	public boolean isDecimalType() {
		return getType().equals(Float.class) || getType().equals(Double.class) || getType().equals(BigDecimal.class);
	}
	
	public boolean isEnumType() {
		return getType().equals(ScaffoldEnum.class) || getType().isEnum();
	}
	
	public boolean isModelType() {
		return getType().equals(ScaffoldModel.class);
	}
	
	public boolean isCollectionType() {
		return getType().isAssignableFrom(Set.class) || getType().isAssignableFrom(List.class);
	}

	public boolean isLocalDateType() {
		return getType().equals(LocalDate.class);
	}
	
	public boolean isLocalDateTimeType() {
		return getType().equals(LocalDateTime.class);
	}
	
	public boolean isLocalTimeType() {
		return getType().equals(LocalTime.class);
	}
	
	public boolean isPeriod() {
		return getType().getSimpleName().equals("Periodo");
	}

}
