package eu.wuttke.nrf.ui.subject;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import eu.wuttke.nrf.domain.subject.Gender;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.misc.StringToPrecisionDateConverter;
import eu.wuttke.nrf.ui.view.EditorView;

public class SubjectEditorView
extends CustomComponent 
implements EditorView<Subject> {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private FormLayout formLayout;
	@AutoGenerated
	@PropertyId("dateOfDeath")
	private TextField tfDateOfDeath;
	@AutoGenerated
	@PropertyId("death")
	private CheckBox cbDeath;
	@AutoGenerated
	@PropertyId("birthName")
	private TextField tfBirthName;
	@AutoGenerated
	@PropertyId("gender")
	private ComboBox cbGender;
	@AutoGenerated
	@PropertyId("birthdate")
	private PopupDateField pdfBirthdate;
	@AutoGenerated
	@PropertyId("title")
	private TextField tfTitle;
	@AutoGenerated
	@PropertyId("firstName")
	private TextField tfFirstName;
	@AutoGenerated
	@PropertyId("lastName")
	private TextField tfLastName;
	@AutoGenerated
	@PropertyId("patientId")
	private TextField tfSubjectId;
	private BeanItem<Subject> beanItem;
	private FieldGroup group;
	
	@Override
	public void displayEntity(Subject s) {
		beanItem = new BeanItem<Subject>(s);
		group = new FieldGroup(beanItem);
		group.bindMemberFields(this);
	}

	@Override
	public Subject retrieveValidatedEntity() {
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
	
	private static final long serialVersionUID = 1L;
	
	public SubjectEditorView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		cbGender.setNullSelectionAllowed(false);
		cbGender.addItem(Gender.MALE);
		cbGender.addItem(Gender.FEMALE);
		
		tfDateOfDeath.setConverter(new StringToPrecisionDateConverter());
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
		
		// tfSubjectId
		tfSubjectId = new TextField();
		tfSubjectId.setCaption("Patient Id");
		tfSubjectId.setImmediate(false);
		tfSubjectId.setWidth("100.0%");
		tfSubjectId.setHeight("-1px");
		tfSubjectId.setRequired(true);
		formLayout.addComponent(tfSubjectId);
		
		// tfLastName
		tfLastName = new TextField();
		tfLastName.setCaption("Last Name");
		tfLastName.setImmediate(false);
		tfLastName.setWidth("100.0%");
		tfLastName.setHeight("-1px");
		tfLastName.setRequired(true);
		formLayout.addComponent(tfLastName);
		
		// tfFirstName
		tfFirstName = new TextField();
		tfFirstName.setCaption("First Name");
		tfFirstName.setImmediate(false);
		tfFirstName.setWidth("100.0%");
		tfFirstName.setHeight("-1px");
		tfFirstName.setRequired(true);
		formLayout.addComponent(tfFirstName);
		
		// tfTitle
		tfTitle = new TextField();
		tfTitle.setCaption("Title");
		tfTitle.setImmediate(false);
		tfTitle.setWidth("100.0%");
		tfTitle.setHeight("-1px");
		formLayout.addComponent(tfTitle);
		
		// pdfBirthdate
		pdfBirthdate = new PopupDateField();
		pdfBirthdate.setCaption("Date of Birth");
		pdfBirthdate.setImmediate(false);
		pdfBirthdate.setWidth("-1px");
		pdfBirthdate.setHeight("-1px");
		pdfBirthdate.setRequired(true);
		formLayout.addComponent(pdfBirthdate);
		
		// cbGender
		cbGender = new ComboBox();
		cbGender.setCaption("Gender");
		cbGender.setImmediate(false);
		cbGender.setWidth("-1px");
		cbGender.setHeight("-1px");
		cbGender.setRequired(true);
		formLayout.addComponent(cbGender);
		
		// tfBirthName
		tfBirthName = new TextField();
		tfBirthName.setCaption("Birth Name");
		tfBirthName.setImmediate(false);
		tfBirthName.setWidth("100.0%");
		tfBirthName.setHeight("-1px");
		tfBirthName.setNullRepresentation("");
		formLayout.addComponent(tfBirthName);
		
		// cbDeath
		cbDeath = new CheckBox();
		cbDeath.setCaption("Verstorben");
		cbDeath.setImmediate(false);
		cbDeath.setWidth("-1px");
		cbDeath.setHeight("-1px");
		formLayout.addComponent(cbDeath);
		
		// tfDateOfDeath
		tfDateOfDeath = new TextField();
		tfDateOfDeath.setCaption("Todesdatum");
		tfDateOfDeath.setImmediate(true);
		tfDateOfDeath.setWidth("-1px");
		tfDateOfDeath.setHeight("-1px");
		tfDateOfDeath.setNullRepresentation("");
		formLayout.addComponent(tfDateOfDeath);
		
		return formLayout;
	}

}
