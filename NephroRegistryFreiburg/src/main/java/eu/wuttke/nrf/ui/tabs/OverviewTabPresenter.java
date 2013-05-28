package eu.wuttke.nrf.ui.tabs;

import com.vaadin.ui.Component;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.FourPanelsComposite;
import eu.wuttke.nrf.ui.diagnosis.DiagnosisListPresenter;
import eu.wuttke.nrf.ui.medication.MedicationListPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;
import eu.wuttke.nrf.ui.subject.RelationListPresenter;
import eu.wuttke.nrf.ui.visit.VisitListPresenter;

public class OverviewTabPresenter 
implements RefreshablePresenter {

	private Subject parentSubject;
	
	private DiagnosisListPresenter diagnosisListPresenter = new DiagnosisListPresenter();
	private VisitListPresenter visitListPresenter = new VisitListPresenter();
	private RelationListPresenter relationListPresenter = new RelationListPresenter();
	private MedicationListPresenter medicationListPresenter = new MedicationListPresenter();
	
	private Component view;
	
	public OverviewTabPresenter() {
		view = new FourPanelsComposite(
				new String[] {"Visits", "Family", "Diagnosis", "Medication"},
				new Component[] {visitListPresenter.getView(), relationListPresenter.getView(), 
						diagnosisListPresenter.getView(), medicationListPresenter.getView()}
				);
	}

	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}
	
	@Override
	public void refreshContent() {
		visitListPresenter.setParentSubject(parentSubject);
		relationListPresenter.setParentSubject(parentSubject);
		diagnosisListPresenter.setParentSubject(parentSubject);
		medicationListPresenter.setParentSubject(parentSubject);

		visitListPresenter.refreshContent();
		relationListPresenter.refreshContent();
		diagnosisListPresenter.refreshContent();
		medicationListPresenter.refreshContent();
	}
	
	@Override
	public Component getView() {
		return view;
	}
	
}
