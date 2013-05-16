package eu.wuttke.nrf.ui.page;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

import eu.wuttke.nrf.ui.subject.SubjectListPresenter;

public class SubjectListPage
extends VerticalLayout
implements View {
	
	public static String PAGE_ID = "";
	
	private SubjectListPresenter presenter = new SubjectListPresenter();
	
	public SubjectListPage() {
		setMargin(true);
		setSizeFull();
		addComponent(presenter.getListView());
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		presenter.refreshContent();
	}

	private static final long serialVersionUID = 1L;
	
}
