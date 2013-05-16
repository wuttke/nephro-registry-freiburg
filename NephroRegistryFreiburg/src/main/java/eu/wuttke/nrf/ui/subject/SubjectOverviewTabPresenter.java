package eu.wuttke.nrf.ui.subject;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.TwoPanelsComposite;
import eu.wuttke.nrf.ui.diagnosis.DiagnosisListPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class SubjectOverviewTabPresenter 
implements RefreshablePresenter {

	private Subject parentSubject;
	
	private DiagnosisListPresenter diagnosisListPresenter = new DiagnosisListPresenter();
	private Component view;
	
	public SubjectOverviewTabPresenter() {
		view = new TwoPanelsComposite("Visits", new Label(), 
				"Diagnosis", diagnosisListPresenter.getView());
		// Medication under Dx, Lab Values (own tab), Attributes under Visits
	}

	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}
	
	@Override
	public void refreshContent() {
		diagnosisListPresenter.setParentSubject(parentSubject);
		diagnosisListPresenter.refreshContent();
	}
	
	@Override
	public Component getView() {
		return view;
	}
	
}
