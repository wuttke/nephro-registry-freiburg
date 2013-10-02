package eu.wuttke.nrf.domain.subject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

@Component
public class RelationRepository {

	public Collection<Relation> findFirstDegreeFamily(Subject subject) {
		List<Relation> family = new LinkedList<Relation>();
		Relation myself = findRelationBySubject(subject);
		if (myself != null) {
			myself.setRole(SubjectRelationRole.SELF);
			family.add(myself);
			
			List<Relation> siblings = findSiblings(myself);
			family.addAll(siblings);
		} 
		
		List<Relation> children = findChildren(subject);
		family.addAll(children);
		
		return family;
	}
	
	public Relation findRelationBySubject(Subject subject) {
		List<Relation> family = Relation.findRelationsBySubject(subject).getResultList();
		assert family.size() < 2;
		return family.size() == 1 ? family.get(0) : null;
	}
	
	public List<Relation> findChildren(Subject subject) {
		List<Relation> children;
		if (subject.getGender() == Gender.MALE)
			children = Relation.findRelationsByFather(subject).getResultList();
		else
			children = Relation.findRelationsByMother(subject).getResultList();
		
		for (Relation child : children)
			child.setRole(SubjectRelationRole.CHILD);

		return children;
	}

	public List<Relation> findSiblings(Relation myself) {
		EntityManager em = Relation.entityManager();
		TypedQuery<Relation> q = em.createQuery("FROM Relation r WHERE ((r.mother = :mother AND NOT (r.mother IS NULL)) OR (r.father = :father AND NOT (r.father IS NULL))) AND r != :self", Relation.class);
		q.setParameter("self", myself);
		q.setParameter("mother", myself.getMother());
		q.setParameter("father", myself.getFather());
		
		List<Relation> siblings = q.getResultList();
		for (Relation sibling : siblings)
			sibling.setRole(SubjectRelationRole.SIBLING);
		return siblings;
	}

	public void collectFamilySubjects(Subject subject, List<Relation> family) {
		Relation relation = findRelationBySubject(subject);
		if (!family.contains(relation)) {
			family.add(relation);
			if (relation.getFather() != null)
				collectFamilySubjects(relation.getFather(), family);
			if (relation.getMother() != null)
				collectFamilySubjects(relation.getMother(), family);
		}
		
		List<Relation> children = findChildren(subject);
		for (Relation child : children)
			if (!family.contains(child)) {
				family.add(child);
				collectFamilySubjects(relation.getSubject(), family);
			}
	}

	public List<Relation> collectFamilySubjects(Subject index) {
		List<Relation> family = new ArrayList<Relation>();
		collectFamilySubjects(index, family);
		return family;
	}
	
}
