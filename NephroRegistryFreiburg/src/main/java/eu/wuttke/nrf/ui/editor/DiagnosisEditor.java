package eu.wuttke.nrf.ui.editor;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import eu.wuttke.nrf.domain.diagnosis.Diagnosis;

public class DiagnosisEditor extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private FormLayout formLayout;
	@AutoGenerated
	private TextField tfValidUntil;
	@AutoGenerated
	private TextField tfValidFrom;
	@AutoGenerated
	@PropertyId("description")
	private TextArea taDescription;
	@AutoGenerated
	private HorizontalLayout horizontalLayoutCode;
	@AutoGenerated
	private Button btnChooseCode;
	@AutoGenerated
	@PropertyId("code")
	private TextField tfCode;
	@AutoGenerated
	@PropertyId("label")
	private TextField tfLabel;
	private static final long serialVersionUID = 1L;
	public DiagnosisEditor(Diagnosis d) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		BeanItem<Diagnosis> bi = new BeanItem<Diagnosis>(d);
		FieldGroup group = new FieldGroup(bi);
		group.bindMemberFields(this);
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
		formLayout.addComponent(tfLabel);
		
		// horizontalLayoutCode
		horizontalLayoutCode = buildHorizontalLayoutCode();
		formLayout.addComponent(horizontalLayoutCode);
		formLayout.setComponentAlignment(horizontalLayoutCode,
				new Alignment(20));
		
		// taDescription
		taDescription = new TextArea();
		taDescription.setCaption("Description");
		taDescription.setImmediate(false);
		taDescription.setWidth("100.0%");
		taDescription.setHeight("-1px");
		formLayout.addComponent(taDescription);
		formLayout.setExpandRatio(taDescription, 1.0f);
		
		// tfValidFrom
		tfValidFrom = new TextField();
		tfValidFrom.setCaption("Valid From");
		tfValidFrom.setImmediate(false);
		tfValidFrom.setWidth("100.0%");
		tfValidFrom.setHeight("-1px");
		formLayout.addComponent(tfValidFrom);
		
		// tfValidUntil
		tfValidUntil = new TextField();
		tfValidUntil.setCaption("Valid Until");
		tfValidUntil.setImmediate(false);
		tfValidUntil.setWidth("100.0%");
		tfValidUntil.setHeight("-1px");
		formLayout.addComponent(tfValidUntil);
		
		return formLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayoutCode() {
		// common part: create layout
		horizontalLayoutCode = new HorizontalLayout();
		horizontalLayoutCode.setCaption("Code");
		horizontalLayoutCode.setImmediate(false);
		horizontalLayoutCode.setWidth("100.0%");
		horizontalLayoutCode.setHeight("-1px");
		horizontalLayoutCode.setMargin(false);
		horizontalLayoutCode.setSpacing(true);
		
		// tfCode
		tfCode = new TextField();
		tfCode.setImmediate(false);
		tfCode.setWidth("100.0%");
		tfCode.setHeight("-1px");
		horizontalLayoutCode.addComponent(tfCode);
		horizontalLayoutCode.setExpandRatio(tfCode, 1.0f);
		
		// btnChooseCode
		btnChooseCode = new Button();
		btnChooseCode.setCaption("Choose");
		btnChooseCode.setImmediate(true);
		btnChooseCode.setWidth("-1px");
		btnChooseCode.setHeight("-1px");
		horizontalLayoutCode.addComponent(btnChooseCode);
		
		return horizontalLayoutCode;
	}

}
