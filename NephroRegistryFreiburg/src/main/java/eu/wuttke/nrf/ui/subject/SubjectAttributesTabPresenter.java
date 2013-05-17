package eu.wuttke.nrf.ui.subject;

import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class SubjectAttributesTabPresenter 
implements RefreshablePresenter {

	private Component view;
	private Subject parentSubject;
	
	public SubjectAttributesTabPresenter() {
		view = new Panel("Attributes");
	}
	
	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}
	
	@Override
	public void refreshContent() {
		
	}

	@Override
	public Component getView() {
		return view;
	}

}
