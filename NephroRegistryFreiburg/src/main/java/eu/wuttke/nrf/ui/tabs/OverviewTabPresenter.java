package eu.wuttke.nrf.ui.tabs;

import com.vaadin.ui.Component;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.FourPanelsComposite;
import eu.wuttke.nrf.ui.diagnosis.DiagnosisListPresenter;
import eu.wuttke.nrf.ui.encounter.EncounterListPresenter;
import eu.wuttke.nrf.ui.medication.MedicationListPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;
import eu.wuttke.nrf.ui.subject.RelationListPresenter;

public class OverviewTabPresenter 
implements RefreshablePresenter {

	private Subject parentSubject;
	
	private DiagnosisListPresenter diagnosisListPresenter = new DiagnosisListPresenter();
	private EncounterListPresenter encounterListPresenter = new EncounterListPresenter();
	private RelationListPresenter relationListPresenter = new RelationListPresenter();
	private MedicationListPresenter medicationListPresenter = new MedicationListPresenter();
	
	private Component view;
	
	public OverviewTabPresenter() {
		view = new FourPanelsComposite(
				new String[] {"Encounters", "Family", "Diagnosis", "Medication"},
				new Component[] {encounterListPresenter.getView(), relationListPresenter.getView(), 
						diagnosisListPresenter.getView(), medicationListPresenter.getView()}
				);
	}

	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}
	
	@Override
	public void refreshContent() {
		encounterListPresenter.setParentSubject(parentSubject);
		relationListPresenter.setParentSubject(parentSubject);
		diagnosisListPresenter.setParentSubject(parentSubject);
		medicationListPresenter.setParentSubject(parentSubject);

		encounterListPresenter.refreshContent();
		relationListPresenter.refreshContent();
		diagnosisListPresenter.refreshContent();
		medicationListPresenter.refreshContent();
	}
	
	@Override
	public Component getView() {
		return view;
	}
	
}
