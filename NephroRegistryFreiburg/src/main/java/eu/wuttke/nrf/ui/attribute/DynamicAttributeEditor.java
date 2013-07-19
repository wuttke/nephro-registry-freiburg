package eu.wuttke.nrf.ui.attribute;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import eu.wuttke.nrf.domain.attribute.AttributeBase;
import eu.wuttke.nrf.domain.attribute.AttributeDataType;
import eu.wuttke.nrf.domain.attribute.AttributeType;
import eu.wuttke.nrf.domain.misc.PrecisionDateUtil;
import eu.wuttke.nrf.ui.misc.StringToPrecisionDateConverter;
import eu.wuttke.nrf.ui.misc.StringToShortDateConverter;

public class DynamicAttributeEditor 
extends FormLayout {
	
	// -> UI und Controller trennen
	// -> Switches eliminieren
	
	private Map<String, Component> singleAttributeEditorComponents = new HashMap<String, Component>(); // Attribute Type Shortcut -> Component

	public DynamicAttributeEditor() {
		setWidth("100%");
		setHeight("-1");
		setMargin(true);
		setSpacing(true);
	}

	public void configureEditor(List<AttributeType> types) {
		singleAttributeEditorComponents.clear();
		removeAllComponents();
		for (AttributeType type : types) {
			Component component = createComponentForType(type);
			if (component != null) {
				if (component instanceof Field<?>)
					configureField(type, (Field<?>)component);
				
				singleAttributeEditorComponents.put(type.getShortcut(), component);
				addComponent(component);
			}
		}
	}

	public void displayData(List<? extends AttributeBase> attributes) {
		for (AttributeBase attribute : attributes) {
			AttributeType type = attribute.getAttributeType();
			Component component = singleAttributeEditorComponents.get(type.getShortcut());
			if (component != null)
				displayData(attribute, type, component);
		}
	}

	public void retrieveData(List<? extends AttributeBase> attributes) {
		for (AttributeBase attribute : attributes) {
			AttributeType type = attribute.getAttributeType();
			Component component = singleAttributeEditorComponents.get(type.getShortcut());
			if (component != null)
				retrieveData(attribute, type, component);
		}
	}

	private Component createComponentForType(AttributeType type) {
		switch (type.getDataType()) {
		case BOOLEAN:
			CheckBox checkBox = new CheckBox(type.getLabel());
			return checkBox;
			
		case SINGLE_LINE_TEXT:
		case INTEGER:
		case DATE:
		case PRECISION_DATE:
			TextField textField = new TextField(type.getLabel());
			return textField;
	
		case MULTI_LINE_TEXT:
			TextArea textArea = new TextArea(type.getLabel());
			return textArea;
	
		case ENUM:
			ComboBox comboBox = new ComboBox(type.getLabel());
			for (String constant : type.getEnumItems().split(","))
				comboBox.addItem(constant.trim());
			return comboBox;
			
		case TRISTATE_BOOLEAN:
			ComboBox tristateBool = new ComboBox(type.getLabel());
			tristateBool.addItem("ja");
			tristateBool.addItem("nein");
			tristateBool.addItem("unbekannt");
			tristateBool.addItem("nicht gesetzt");
			return tristateBool;
			
		default:
			logger.warn("unimplemented data type: {}; omit attribute", type.getDataType());
			return null;
		}		
	}

	private void configureField(AttributeType type, Field<?> field) {
		if (field instanceof AbstractTextField) {
			AbstractTextField textField = (AbstractTextField)field;
			textField.setNullRepresentation("");
			if (type.getMaximumLength() != null)
				textField.setMaxLength(type.getMaximumLength());
			
			if (type.getDataType() == AttributeDataType.PRECISION_DATE)
				textField.setConverter(new StringToPrecisionDateConverter());
			else if (type.getDataType() == AttributeDataType.DATE)
				textField.setConverter(new StringToShortDateConverter());
		}
	}

	private void displayData(AttributeBase attribute, AttributeType type,
			Component component) {
		Object value = convertAttributeValueToFieldValue(type.getDataType(), attribute.getAttributeValue());
		if (component instanceof AbstractField<?>) {
			@SuppressWarnings("unchecked")
			AbstractField<Object> field = (AbstractField<Object>)component;
			field.setValue(value);
		} else {
			logger.warn("unknown component type: {}; omit attribute", component.getClass().getSimpleName());
		}
	}
	
	private void retrieveData(AttributeBase attribute, AttributeType type,
			Component component) {
		if (component instanceof AbstractField<?>) {
			@SuppressWarnings("unchecked")
			AbstractField<Object> field = (AbstractField<Object>)component;
			String value = convertFieldValueToAttributeValue(type.getDataType(), field.getValue());
			attribute.setAttributeValue(value);
		} else {
			logger.warn("unknown component type: {}; omit attribute", component.getClass().getSimpleName());
			attribute.setAttributeValue(null);
		}
	}

	private Object convertAttributeValueToFieldValue(AttributeDataType dataType, String attributeValue) {
		switch (dataType) {
		case BOOLEAN:
			return new Boolean(attributeValue);
			
		case SINGLE_LINE_TEXT:
		case MULTI_LINE_TEXT:
		case ENUM:
		case INTEGER:
		case PRECISION_DATE:
			return attributeValue;
			
		case DATE:
			// stored as ISO
			if (StringUtils.isBlank(attributeValue)) return "";
			Date dt = PrecisionDateUtil.parseIsoDate(attributeValue);
			return PrecisionDateUtil.formatDate(dt, null);
			
		case TRISTATE_BOOLEAN:
			if (StringUtils.isBlank(attributeValue)) return "nicht gesetzt";
			else if (attributeValue.equalsIgnoreCase("TRUE")) return "ja";
			else if (attributeValue.equalsIgnoreCase("FALSE")) return "nein";
			else if (attributeValue.equalsIgnoreCase("UNKNOWN")) return "unbekannt";
			else return "nicht gesetzt";
			
		default:
			logger.warn("unknown data type {} for value '{}'", dataType, attributeValue);
			return null;
		}
	}
	
	private String convertFieldValueToAttributeValue(AttributeDataType dataType, Object fieldValue) {
		if (fieldValue == null)
			return null;
		
		if (fieldValue instanceof String && StringUtils.isBlank((String)fieldValue))
			return (String)fieldValue;
		
		if (dataType == AttributeDataType.DATE) {
			// store as ISO
			Date dt = PrecisionDateUtil.parseDate((String)fieldValue);
			if (dt == null)
				return null;
			return PrecisionDateUtil.formatIsoDate(dt);
		} else if (dataType == AttributeDataType.TRISTATE_BOOLEAN) {
			if (((String)fieldValue).equals("ja"))
				return "TRUE";
			else if (((String)fieldValue).equals("nein"))
				return "FALSE";
			else if (((String)fieldValue).equals("unbekannt"))
				return "UNKNOWN";
			else
				return "";
		}
		
		return fieldValue.toString();
	}

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(DynamicAttributeEditor.class);
	
}

