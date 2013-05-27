package eu.wuttke.nrf.ui.subject;

import java.util.Collection;
import java.util.List;

import eu.wuttke.nrf.domain.subject.Gender;
import eu.wuttke.nrf.domain.subject.Relation;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.ListPresenter;


public class RelationListPresenter 
extends ListPresenter<Relation, RelationListView> {
	
	private Subject subject;
	
	public RelationListPresenter() {
		super(new RelationListView());
	}
	
	public void setParentSubject(Subject parentSubject) {
		this.subject = parentSubject;
	}
	
	@Override
	public Collection<? extends Relation> loadEntities() {
		List<Relation> family = Relation.findRelationsBySubject(subject).getResultList();
		
		List<Relation> children;
		if (subject.getGender() == Gender.MALE)
			children = Relation.findRelationsByFather(subject).getResultList();
		else
			children = Relation.findRelationsByMother(subject).getResultList();
		
		family.addAll(children);
		return family;
	}
	
	@Override
	public EditorPresenter<Relation, ?> createEditorPresenter() {
		return new RelationEditorPresenter(subject, this);
	}
	
}
