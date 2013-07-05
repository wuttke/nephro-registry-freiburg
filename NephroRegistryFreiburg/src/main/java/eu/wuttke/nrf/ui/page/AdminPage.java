package eu.wuttke.nrf.ui.page;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

import eu.wuttke.nrf.ui.admin.AdminOverviewPresenter;

public class AdminPage
extends VerticalLayout
implements View {
	
	public static String PAGE_ID = "admin";
	
	private AdminOverviewPresenter presenter = new AdminOverviewPresenter();
	
	public AdminPage() {
		setMargin(true);
		setSizeFull();
		addComponent(presenter.getView());
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		presenter.refreshContent();
	}

	private static final long serialVersionUID = 1L;

}
