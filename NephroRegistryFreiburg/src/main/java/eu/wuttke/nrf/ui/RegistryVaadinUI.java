package eu.wuttke.nrf.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import eu.wuttke.nrf.controller.subject.SubjectListController;

public class RegistryVaadinUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		logger.info("RegistryVaadinUI init");
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSizeFull();
		setContent(layout);

		SubjectListController mainController = new SubjectListController();
		layout.addComponent(mainController.getView());
		mainController.refreshContent();
	}
	
	/*
	private void buttonClicked() {
		Diagnosis d = new Diagnosis();
		d.setCode("I12");
		d.setLabel("Hypertonie");
		d.setDescription("Test 123\n3455");
		d.setValidFrom(PrecisionDateUtil.parsePrecisionDate("10/2008"));
		DiagnosisEditor de = new DiagnosisEditor(d);
		OkCancelWindow w = new OkCancelWindow(de);
		w.show(this, "Diagnosis Editor", "50%", "50%");		
	}
	*/

	private static Logger logger = LoggerFactory.getLogger(RegistryVaadinUI.class);
	private static final long serialVersionUID = 1L;

}
