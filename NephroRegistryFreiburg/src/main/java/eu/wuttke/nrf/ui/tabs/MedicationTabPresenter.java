package eu.wuttke.nrf.ui.tabs;

import com.vaadin.ui.Component;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.medication.MedicationListPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class MedicationTabPresenter implements RefreshablePresenter {

	private Subject parentSubject;
	private MedicationListPresenter medicationListPresenter = new MedicationListPresenter();
	
	public MedicationTabPresenter() {
	}

	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}

	@Override
	public void refreshContent() {
		medicationListPresenter.setParentSubject(parentSubject);
		medicationListPresenter.refreshContent();
	}

	@Override
	public Component getView() {
		return medicationListPresenter.getView();
	}

}
