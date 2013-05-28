package eu.wuttke.nrf.ui.subject;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import eu.wuttke.nrf.domain.subject.Gender;
import eu.wuttke.nrf.domain.subject.Relation;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.subject.SubjectRelationRole;
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
		List<Relation> siblings = new LinkedList<Relation>();
		
		List<Relation> family = Relation.findRelationsBySubject(subject).getResultList();
		Relation myself = family.size() > 0 ? family.get(0) : null;
		if (myself != null) {
			myself.setRole(SubjectRelationRole.SELF);
			siblings = findSiblings(myself);
		}
		
		List<Relation> children;
		if (subject.getGender() == Gender.MALE)
			children = Relation.findRelationsByFather(subject).getResultList();
		else
			children = Relation.findRelationsByMother(subject).getResultList();
		
		for (Relation child : children)
			child.setRole(SubjectRelationRole.CHILD);
		for (Relation sibling : siblings)
			sibling.setRole(SubjectRelationRole.SIBLING);
		
		family.addAll(children);
		family.addAll(siblings);
		return family;
	}
	
	private List<Relation> findSiblings(Relation myself) {
		EntityManager em = Relation.entityManager();
		TypedQuery<Relation> q = em.createQuery("FROM Relation r WHERE ((r.mother = :mother AND NOT (r.mother IS NULL)) OR (r.father = :father AND NOT (r.father IS NULL))) AND r != :self", Relation.class);
		q.setParameter("self", myself);
		q.setParameter("mother", myself.getMother());
		q.setParameter("father", myself.getFather());
		return q.getResultList();
	}

	@Override
	public EditorPresenter<Relation, ?> createEditorPresenter() {
		return new RelationEditorPresenter(subject, this);
	}
	
}
