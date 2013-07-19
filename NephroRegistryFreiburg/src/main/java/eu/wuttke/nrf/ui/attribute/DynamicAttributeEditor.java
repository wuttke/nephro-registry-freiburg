package eu.wuttke.nrf.ui.attribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			return attributeValue;
			
		default:
			logger.warn("unknown data type {} for value '{}'", dataType, attributeValue);
			return null;
		}
	}
	
	private String convertFieldValueToAttributeValue(AttributeDataType dataType, Object fieldValue) {
		if (fieldValue == null)
			return null;
		return fieldValue.toString();
	}

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(DynamicAttributeEditor.class);
	
}

