package eu.wuttke.nrf.ui.tabs;

import com.vaadin.ui.Component;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.TwoPanelsComposite;
import eu.wuttke.nrf.ui.diagnosis.DiagnosisListPresenter;
import eu.wuttke.nrf.ui.medication.MedicationListPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class DiagnosisMedicationTabPresenter implements RefreshablePresenter {

	private Subject parentSubject;
	private DiagnosisListPresenter diagnosisListPresenter = new DiagnosisListPresenter();
	private MedicationListPresenter medicationListPresenter = new MedicationListPresenter();
	
	private Component view;

	public DiagnosisMedicationTabPresenter() {
		view = new TwoPanelsComposite(
				"Diagnoses", diagnosisListPresenter.getView(), 
				"Medication", medicationListPresenter.getView()
				);
	}

	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}

	@Override
	public void refreshContent() {
		medicationListPresenter.setParentSubject(parentSubject);
		diagnosisListPresenter.setParentSubject(parentSubject);
		medicationListPresenter.refreshContent();
		diagnosisListPresenter.refreshContent();
	}

	@Override
	public Component getView() {
		return view;
	}

}
