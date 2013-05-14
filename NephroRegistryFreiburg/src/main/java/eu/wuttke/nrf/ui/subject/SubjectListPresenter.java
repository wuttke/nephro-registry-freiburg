package eu.wuttke.nrf.ui.subject;

import java.util.Collection;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.ListPresenter;


public class SubjectListPresenter 
extends ListPresenter<Subject, SubjectListView> {

	public SubjectListPresenter() {
		super(new SubjectListView());
		getListView().setListPresenter(this);
	}
	
	@Override
	public Collection<? extends Subject> loadEntities() {
		return Subject.findAllSubjects();
	}
	
	@Override
	public EditorPresenter<Subject, ?> createEditorPresenter() {
		return new SubjectEditorPresenter(this);
	}
	
}
