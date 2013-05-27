package eu.wuttke.nrf.ui.medication;

import java.util.Collection;

import eu.wuttke.nrf.domain.medication.Medication;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.ListPresenter;


public class MedicationListPresenter 
extends ListPresenter<Medication, MedicationListView> {
	
	private Subject parentSubject;
	
	public MedicationListPresenter() {
		super(new MedicationListView());
	}
	
	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}
	
	@Override
	public Collection<? extends Medication> loadEntities() {
		return Medication.findMedicationsBySubject(parentSubject).getResultList();
	}
	
	@Override
	public EditorPresenter<Medication, ?> createEditorPresenter() {
		return new MedicationEditorPresenter(parentSubject, this);
	}
	
}