package eu.wuttke.nrf.ui.diagnosis;

import eu.wuttke.nrf.domain.diagnosis.Diagnosis;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class DiagnosisEditorPresenter 
extends EditorPresenter<Diagnosis, DiagnosisEditorView> {
	
	private Subject parentSubject;
	
	public DiagnosisEditorPresenter(Subject parentSubject, RefreshablePresenter parentPresenter) {
		super(new DiagnosisEditorView(), parentPresenter);
		this.parentSubject = parentSubject;
	}
	
	@Override
	public void showEditorWindow(OkCancelWindow w) {
		w.show(((DiagnosisListPresenter)getParent()).getListView().getUI(), "Edit Diagnosis", "600", "400");	
	}

	@Override
	public Diagnosis createEntity() {
		Diagnosis d = new Diagnosis();
		d.setSubject(parentSubject);
		return d;
	}
	
	@Override
	public void saveEntity(Diagnosis entity) {
		entity.merge();
	}

}
