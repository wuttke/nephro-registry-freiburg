package eu.wuttke.nrf.ui.component;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class TwoPanelsComposite
extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	public TwoPanelsComposite(String title1, Component c1, String title2, Component c2) {
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		
		Panel panel1 = makePanelWithLayout(title1, c1);
		Panel panel2 = makePanelWithLayout(title2, c2);
		
		addComponent(panel1);
		addComponent(panel2);
		setExpandRatio(panel1, 1f);
		setExpandRatio(panel2, 1f);
	}

	private Panel makePanelWithLayout(String title, Component component) {
		VerticalLayout marginLayout = new VerticalLayout();
		marginLayout.setMargin(true);
		marginLayout.addComponent(component);
		marginLayout.setSizeFull();
		
		Panel panel = new Panel();
		panel.setCaption(title);
		panel.setSizeFull();
		panel.setContent(marginLayout);
		return panel;
	}
	
}
