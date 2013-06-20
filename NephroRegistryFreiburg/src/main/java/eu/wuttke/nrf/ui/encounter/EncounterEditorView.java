package eu.wuttke.nrf.ui.encounter;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import eu.wuttke.nrf.domain.encounter.Encounter;
import eu.wuttke.nrf.domain.encounter.EncounterType;
import eu.wuttke.nrf.ui.misc.StringToShortDateConverter;
import eu.wuttke.nrf.ui.view.EditorView;

public class EncounterEditorView 
extends CustomComponent
implements EditorView<Encounter> {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private FormLayout formLayout;
	@AutoGenerated
	@PropertyId("notes")
	private TextArea textAreaNotes;
	@AutoGenerated
	@PropertyId("type")
	private ComboBox comboBoxType;
	@AutoGenerated
	@PropertyId("encounterDateTime")
	private TextField tfEncounterDate;
	@AutoGenerated
	@PropertyId("label")
	private TextField tfLabel;
	private static final long serialVersionUID = 1L;
	
	private BeanItem<Encounter> beanItem;
	private FieldGroup group;

	public EncounterEditorView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		tfEncounterDate.setConverter(new StringToShortDateConverter());
		
		for (EncounterType type : EncounterType.values())
			comboBoxType.addItem(type);
	}
	
	@Override
	public void displayEntity(Encounter d) {
		beanItem = new BeanItem<Encounter>(d);
		group = new FieldGroup(beanItem);
		group.bindMemberFields(this);
	}

	@Override
	public Encounter retrieveValidatedEntity() {
		try {
			group.commit();
			return beanItem.getBean();
		} catch (CommitException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean isValid() {
		return group.isValid();
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// formLayout
		formLayout = buildFormLayout();
		mainLayout.addComponent(formLayout);
		
		return mainLayout;
	}

	@AutoGenerated
	private FormLayout buildFormLayout() {
		// common part: create layout
		formLayout = new FormLayout();
		formLayout.setImmediate(false);
		formLayout.setWidth("100.0%");
		formLayout.setHeight("100.0%");
		formLayout.setMargin(false);
		formLayout.setSpacing(true);
		
		// tfLabel
		tfLabel = new TextField();
		tfLabel.setCaption("Label");
		tfLabel.setImmediate(false);
		tfLabel.setWidth("100.0%");
		tfLabel.setHeight("-1px");
		tfLabel.setRequired(true);
		tfLabel.setNullRepresentation("");
		formLayout.addComponent(tfLabel);
		
		// tfEncounterDate
		tfEncounterDate = new TextField();
		tfEncounterDate.setCaption("Date Time");
		tfEncounterDate.setImmediate(true);
		tfEncounterDate.setWidth("100.0%");
		tfEncounterDate.setHeight("-1px");
		tfEncounterDate.setRequired(true);
		tfEncounterDate.setNullRepresentation("");
		formLayout.addComponent(tfEncounterDate);
		
		// comboBoxType
		comboBoxType = new ComboBox();
		comboBoxType.setCaption("Type");
		comboBoxType.setImmediate(false);
		comboBoxType.setWidth("-1px");
		comboBoxType.setHeight("-1px");
		formLayout.addComponent(comboBoxType);
		
		// textAreaNotes
		textAreaNotes = new TextArea();
		textAreaNotes.setCaption("Notes");
		textAreaNotes.setImmediate(false);
		textAreaNotes.setWidth("100.0%");
		textAreaNotes.setHeight("100px");
		textAreaNotes.setNullRepresentation("");
		formLayout.addComponent(textAreaNotes);
		
		return formLayout;
	}

}
