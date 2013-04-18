package eu.wuttke.nrf.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import eu.wuttke.nrf.domain.diagnosis.Diagnosis;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.editor.DiagnosisEditor;

public class RegistryVaadinUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		logger.info("RegistryVaadinUI init");
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
				Subject.findAllSubjects();
				logger.info("button clicked");
				buttonClicked();
			}
		});
		layout.addComponent(button);
	}
	
	private void buttonClicked() {
		Diagnosis d = new Diagnosis();
		d.setCode("I12");
		d.setLabel("Hypertonie");
		d.setDescription("Test 123\n3455");
		DiagnosisEditor de = new DiagnosisEditor(d);
		OkCancelWindow w = new OkCancelWindow(de);
		w.show(this, "Diagnosis Editor", "50%", "50%");		
	}

	private static Logger logger = LoggerFactory.getLogger(RegistryVaadinUI.class);
	private static final long serialVersionUID = 1L;

}
