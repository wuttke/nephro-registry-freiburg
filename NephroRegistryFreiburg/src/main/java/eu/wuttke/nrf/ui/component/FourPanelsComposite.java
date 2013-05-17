package eu.wuttke.nrf.ui.component;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class FourPanelsComposite
extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	public FourPanelsComposite(String[] titles, Component[] components) {
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		
		for (int i = 0; i < 2; i++) {
			Panel panel1 = makePanelWithMargin(titles[i * 2], components[i * 2]);
			Panel panel2 = makePanelWithMargin(titles[i * 2 + 1], components[i * 2 + 1]);

			VerticalLayout vl = new VerticalLayout();
			vl.setSizeFull();
			vl.setSpacing(true);
			
			vl.addComponent(panel1);
			vl.addComponent(panel2);
			
			vl.setExpandRatio(panel1, 1f);
			vl.setExpandRatio(panel2, 1f);
			
			addComponent(vl);
			setExpandRatio(vl, 1f);
		}
	}

	private Panel makePanelWithMargin(String title, Component component) {
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
