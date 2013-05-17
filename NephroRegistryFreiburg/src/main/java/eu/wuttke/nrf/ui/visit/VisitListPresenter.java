package eu.wuttke.nrf.ui.visit;

import java.util.Collection;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.visit.Visit;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.ListPresenter;


public class VisitListPresenter 
extends ListPresenter<Visit, VisitListView> {
	
	private Subject parentSubject;
	
	public VisitListPresenter() {
		super(new VisitListView());
	}
	
	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}
	
	@Override
	public Collection<? extends Visit> loadEntities() {
		return Visit.findVisitsBySubject(parentSubject).getResultList();
	}
	
	@Override
	public EditorPresenter<Visit, ?> createEditorPresenter() {
		return null; //new VisitEditorPresenter(parentSubject, this);
	}
	
}
