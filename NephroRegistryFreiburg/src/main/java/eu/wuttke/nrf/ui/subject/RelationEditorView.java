package eu.wuttke.nrf.ui.subject;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import eu.wuttke.nrf.domain.misc.PrecisionDateUtil;
import eu.wuttke.nrf.domain.subject.Relation;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.view.EditorView;

public class RelationEditorView
extends CustomComponent 
implements EditorView<Relation> {

	private VerticalLayout mainLayout;
	private FormLayout formLayout;
	private Label lblMotherId;
	private Label lblFatherId;
	private Label lblSubjectId;
	private Button btnChooseMother;
	private Button btnChooseFather;
	private Relation relation;
	
	@Override
	public void displayEntity(Relation s) {
		this.relation = s;
		displaySubject(lblMotherId, s.getMother());
		displaySubject(lblFatherId, s.getFather());
		displaySubject(lblSubjectId, s.getSubject());		
	}
	
	public void addChooseClickListeners(ClickListener chooseMother, ClickListener chooseFather) {
		btnChooseFather.addClickListener(chooseFather);
		btnChooseMother.addClickListener(chooseMother);
	}

	private void displaySubject(Label label, Subject subject) {
		if (subject == null)
			label.setValue("---");
		else
			label.setValue(String.format("%s, %s (ID %s, Pseudonym %s, DOB %s)", subject.getLastName(), subject.getFirstName(), 
					subject.getPatientId(), subject.getPseudonym(), PrecisionDateUtil.formatDate(subject.getBirthdate(), null)));
	}

	@Override
	public Relation retrieveValidatedEntity() {
		return relation;
	}
	
	@Override
	public boolean isValid() {
		return true;
	}
	
	private static final long serialVersionUID = 1L;
	
	public RelationEditorView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

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

	private FormLayout buildFormLayout() {
		// common part: create layout
		formLayout = new FormLayout();
		formLayout.setImmediate(false);
		formLayout.setWidth("100.0%");
		formLayout.setHeight("100.0%");
		formLayout.setMargin(false);
		formLayout.setSpacing(true);
		
		// tfSubjectId
		lblSubjectId = new Label();
		lblSubjectId.setCaption("Subject");
		lblSubjectId.setImmediate(false);
		lblSubjectId.setWidth("100.0%");
		lblSubjectId.setHeight("-1px");
		formLayout.addComponent(lblSubjectId);
		
		// btnChooseFather
		btnChooseFather = new Button();
		btnChooseFather.setCaption("Choose");
		btnChooseFather.setImmediate(true);
		btnChooseFather.setWidth("-1px");
		btnChooseFather.setHeight("-1px");

		// btnChooseMother
		btnChooseMother = new Button();
		btnChooseMother.setCaption("Choose");
		btnChooseMother.setImmediate(true);
		btnChooseMother.setWidth("-1px");
		btnChooseMother.setHeight("-1px");

		// tfFatherId
		lblFatherId = new Label();
		lblFatherId.setImmediate(false);
		lblFatherId.setWidth("100.0%");
		lblFatherId.setHeight("-1px");
		formLayout.addComponent(buildHorizontalLayout("Father", lblFatherId, btnChooseFather));
		
		// tfMotherId
		lblMotherId = new Label();
		lblMotherId.setImmediate(false);
		lblMotherId.setWidth("100.0%");
		lblMotherId.setHeight("-1px");
		formLayout.addComponent(buildHorizontalLayout("Mother", lblMotherId, btnChooseMother));
		
		return formLayout;
	}
	

	private HorizontalLayout buildHorizontalLayout(String caption, Label displayLabel, Button chooseButton) {
		// common part: create layout
		HorizontalLayout horizontalLayoutCode = new HorizontalLayout();
		horizontalLayoutCode.setCaption(caption);
		horizontalLayoutCode.setImmediate(false);
		horizontalLayoutCode.setWidth("100.0%");
		horizontalLayoutCode.setHeight("-1px");
		horizontalLayoutCode.setMargin(false);
		horizontalLayoutCode.setSpacing(true);
		
		horizontalLayoutCode.addComponent(displayLabel);
		horizontalLayoutCode.setComponentAlignment(displayLabel, Alignment.MIDDLE_LEFT);
		horizontalLayoutCode.addComponent(chooseButton);
		horizontalLayoutCode.setComponentAlignment(chooseButton, Alignment.MIDDLE_RIGHT);
		
		return horizontalLayoutCode;
	}

}
