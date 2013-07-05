package eu.wuttke.nrf.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import eu.wuttke.nrf.ui.page.AdminPage;
import eu.wuttke.nrf.ui.page.SubjectDetailPage;
import eu.wuttke.nrf.ui.page.SubjectListPage;

public class RegistryVaadinUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		logger.info("RegistryVaadinUI init");
		
		Navigator n = new Navigator(this, this);
		n.addView(SubjectListPage.PAGE_ID, new SubjectListPage());
		n.addView(SubjectDetailPage.PAGE_ID, new SubjectDetailPage());
		n.addView(AdminPage.PAGE_ID, new AdminPage());
	}
	
	private static Logger logger = LoggerFactory.getLogger(RegistryVaadinUI.class);
	private static final long serialVersionUID = 1L;

}
