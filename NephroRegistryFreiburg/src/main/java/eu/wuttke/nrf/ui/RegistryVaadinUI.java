package eu.wuttke.nrf.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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
				logger.info("button clicked");
			}
		});
		layout.addComponent(button);
	}

	private static Logger logger = LoggerFactory.getLogger(RegistryVaadinUI.class);
	private static final long serialVersionUID = 1L;

}
