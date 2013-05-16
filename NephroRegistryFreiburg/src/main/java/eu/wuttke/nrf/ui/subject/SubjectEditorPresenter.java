package eu.wuttke.nrf.ui.subject;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class SubjectEditorPresenter 
extends EditorPresenter<Subject, SubjectEditorView> {
	
	public SubjectEditorPresenter(RefreshablePresenter parent) {
		super(new SubjectEditorView(), parent);
	}
	
	@Override
	public void showEditorWindow(OkCancelWindow w) {
		w.show(((SubjectListPresenter)getParent()).getListView().getUI(), "Edit Subject", "600", "400");	
	}

	@Override
	public Subject createEntity() {
		Subject s = new Subject();
		s.generatePseudonym();
		return s;
	}
	
	@Override
	public void saveEntity(Subject entity) {
		entity.merge();
	}

	
}
