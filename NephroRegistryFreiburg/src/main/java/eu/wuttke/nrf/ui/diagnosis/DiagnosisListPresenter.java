package eu.wuttke.nrf.ui.diagnosis;

import java.util.Collection;

import eu.wuttke.nrf.domain.diagnosis.Diagnosis;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.ListPresenter;


public class DiagnosisListPresenter 
extends ListPresenter<Diagnosis, DiagnosisListView> {
	
	private Subject parentSubject;
	
	public DiagnosisListPresenter() {
		super(new DiagnosisListView());
	}
	
	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}
	
	@Override
	public Collection<? extends Diagnosis> loadEntities() {
		return Diagnosis.findDiagnosesBySubject(parentSubject).getResultList();
	}
	
	@Override
	public EditorPresenter<Diagnosis, ?> createEditorPresenter() {
		return new DiagnosisEditorPresenter(parentSubject, this);
	}
	
}
