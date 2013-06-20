package eu.wuttke.nrf.ui.attribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
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
	
	private Map<AttributeType, Component> singleAttributeEditorComponents = new HashMap<AttributeType, Component>();

	public DynamicAttributeEditor() {
		setSizeFull();
		setMargin(true);
		setSpacing(true);
	}

	public void configureEditor(List<AttributeType> types) {
		singleAttributeEditorComponents.clear();
		removeAllComponents();
		for (AttributeType type : types) {
			Component component = createComponentForType(type);
			if (component != null) {
				singleAttributeEditorComponents.put(type, component);
				addComponent(component);
			}
		}
	}

	private Component createComponentForType(AttributeType type) {
		switch (type.getDataType()) {
		case BOOLEAN:
			CheckBox checkBox = new CheckBox(type.getLabel());
			return checkBox;
			
		case SINGLE_LINE_TEXT:
			TextField textField = new TextField(type.getLabel());
			if (type.getMaximumLength() != null)
				textField.setMaxLength(type.getMaximumLength());
			return textField;

		case MULTI_LINE_TEXT:
			TextArea textArea = new TextArea(type.getLabel());
			if (type.getMaximumLength() != null)
				textArea.setMaxLength(type.getMaximumLength());
			return textArea;
	
		default:
			logger.warn("unimplemented data type: {}; omit attribute", type.getDataType());
			return null;
		}		
	}

	public void displayData(List<? extends AttributeBase> attributes) {
		for (AttributeBase attribute : attributes) {
			AttributeType type = attribute.getAttributeType();
			Component component = singleAttributeEditorComponents.get(type);
			if (component != null)
				displayData(attribute, type, component);
		}
	}

	private void displayData(AttributeBase attribute, AttributeType type,
			Component component) {
		Object value = convertAttributeValueToObject(type.getDataType(), attribute.getAttributeValue());
		if (component instanceof AbstractField<?>) {
			@SuppressWarnings("unchecked")
			AbstractField<Object> field = (AbstractField<Object>)component;
			field.setValue(value);
		} else {
			logger.warn("unknown component type: {}; omit attribute", component.getClass().getSimpleName());
		}
	}
	
	private Object convertAttributeValueToObject(AttributeDataType dataType, String attributeValue) {
		switch (dataType) {
		case BOOLEAN:
			return new Boolean(attributeValue);
		case SINGLE_LINE_TEXT:
		case MULTI_LINE_TEXT:
			return attributeValue;
		default:
			logger.warn("unknown data type {} for value '{}'", dataType, attributeValue);
			return null;
		}
	}

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(DynamicAttributeEditor.class);
	
}

