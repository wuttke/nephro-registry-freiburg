package eu.wuttke.nrf.ui.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

import eu.wuttke.nrf.ui.subject.SubjectDetailPresenter;

public class SubjectDetailPage extends VerticalLayout implements View {

	public static String PAGE_ID = "subj";

	private SubjectDetailPresenter presenter = new SubjectDetailPresenter();
	
	public SubjectDetailPage() {
		setSizeFull();
		setMargin(true);
		addComponent(presenter.getView());
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		String id = event.getParameters();
		
		logger.info("enter detail page for subject ID " + id); //$NON-NLS-1$
		presenter.setSubjectId(Integer.parseInt(id));
		presenter.refreshContent();
	}

	private static Logger logger = LoggerFactory.getLogger(SubjectDetailPage.class);
	private static final long serialVersionUID = 1L;

}
