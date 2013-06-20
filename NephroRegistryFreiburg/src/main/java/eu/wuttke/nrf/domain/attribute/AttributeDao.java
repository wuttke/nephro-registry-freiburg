package eu.wuttke.nrf.domain.attribute;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import eu.wuttke.nrf.domain.encounter.Encounter;
import eu.wuttke.nrf.domain.encounter.EncounterAttribute;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.subject.SubjectAttribute;

@Component
public class AttributeDao {

	public List<AttributeCategory> getAttributeCategories() {
		EntityManager em = AttributeCategory.entityManager();
		return em.createQuery("FROM AttributeCategory ORDER BY sequenceNumber", AttributeCategory.class).getResultList();
	}
	
	public List<AttributeType> findAttributeTypesByUsageAndCategories(AttributeTypeUsage usage, Collection<AttributeCategory> categories) {
		if (categories.size() <= 0)
			return new LinkedList<AttributeType>();
		
		EntityManager em = AttributeType.entityManager();
		TypedQuery<AttributeType> query = em.createQuery("FROM AttributeType WHERE attributeUsage = :usage AND category IN (:categories) ORDER BY sequenceNumber", AttributeType.class);
		query.setParameter("usage", usage);
		query.setParameter("categories", categories);
		return query.getResultList();
	}
	
	public List<SubjectAttribute> getSubjectAttributesBySubject(Subject s) {
		EntityManager em = Subject.entityManager();
		TypedQuery<SubjectAttribute> query = em.createQuery("FROM SubjectAttribute WHERE subject = :subject", SubjectAttribute.class);
		query.setParameter("subject", s);
		return query.getResultList();
	}
	
	public List<EncounterAttribute> getEncounterAttributesByEncounter(Encounter v) {
		EntityManager em = Subject.entityManager();
		TypedQuery<EncounterAttribute> query = em.createQuery("FROM EncounterAttribute WHERE encounter = :encounter", EncounterAttribute.class);
		query.setParameter("encounter", v);
		return query.getResultList();
	}
	
}
