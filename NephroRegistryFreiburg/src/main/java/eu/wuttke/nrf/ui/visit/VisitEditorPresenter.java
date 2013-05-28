package eu.wuttke.nrf.ui.visit;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.visit.Visit;
import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.diagnosis.DiagnosisListPresenter;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class VisitEditorPresenter 
extends EditorPresenter<Visit, VisitEditorView> {
	
	private Subject parentSubject;
	
	public VisitEditorPresenter(Subject parentSubject, RefreshablePresenter parentPresenter) {
		super(new VisitEditorView(), parentPresenter);
		this.parentSubject = parentSubject;
	}
	
	@Override
	public void showEditorWindow(OkCancelWindow w) {
		w.show(((DiagnosisListPresenter)getParent()).getListView().getUI(), "Edit Visit", "400", "300");	
	}

	@Override
	public Visit createEntity() {
		Visit d = new Visit();
		d.setSubject(parentSubject);
		return d;
	}
	
	@Override
	public void saveEntity(Visit entity) {
		entity.merge();
	}

}
