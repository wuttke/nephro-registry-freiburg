package eu.wuttke.nrf.domain.attribute;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import eu.wuttke.nrf.domain.encounter.Encounter;
import eu.wuttke.nrf.domain.encounter.EncounterAttribute;
import eu.wuttke.nrf.domain.encounter.EncounterCategory;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.subject.SubjectAttribute;
import eu.wuttke.nrf.domain.subject.SubjectCategory;

@Component
public class AttributeDao {

	// TODO prevent delete categories that still have attributes
	
	public List<AttributeCategory> getAttributeCategories(AttributeParentType parentType) {
		EntityManager em = AttributeCategory.entityManager();
		TypedQuery<AttributeCategory> q = em.createQuery("FROM AttributeCategory WHERE parentType = :parentType ORDER BY sequenceNumber", 
				AttributeCategory.class);
		q.setParameter("parentType", parentType);
		return q.getResultList();
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
		return SubjectAttribute.findSubjectAttributesBySubject(s).getResultList();
	}
	
	public List<EncounterAttribute> getEncounterAttributesByEncounter(Encounter v) {
		return EncounterAttribute.findEncounterAttributesByEncounter(v).getResultList();
	}

	public List<SubjectCategory> getSubjectCategoriesBySubject(
			Subject s) {
		return SubjectCategory.findSubjectCategorysBySubject(s).getResultList();
	}

	public void syncSubjectCategories(Subject subject,
			List<SubjectCategory> existingSubjectCategories,
			Collection<AttributeCategory> editedSubjectCategories) {
		createNewSubjectCategories(subject, existingSubjectCategories, editedSubjectCategories);
		removeDeletedCategories(existingSubjectCategories, editedSubjectCategories);
	}

	private void createNewSubjectCategories(Subject subject,
			List<SubjectCategory> existingSubjectCategories,
			Collection<AttributeCategory> editedSubjectCategories) {
		List<AttributeCategory> newCategories = findNewCategories(existingSubjectCategories, editedSubjectCategories);
		for (AttributeCategory newCategory : newCategories) {
			logger.info("new category {} for subject {}", newCategory.getLabel(), subject.getPatientId());
			SubjectCategory sc = new SubjectCategory();
			sc.setCategory(newCategory);
			sc.setSubject(subject);
			sc.persist();
		}
	}
	
	public void syncEncounterCategories(Encounter encounter,
			List<EncounterCategory> existingEncounterCategories,
			Collection<AttributeCategory> editedEncounterCategories) {
		createNewEncounterCategories(encounter, existingEncounterCategories, editedEncounterCategories);
		removeDeletedCategories(existingEncounterCategories, editedEncounterCategories);
	}

	private void createNewEncounterCategories(Encounter encounter,
			List<EncounterCategory> existingEncounterCategories,
			Collection<AttributeCategory> editedEncounterCategories) {
		List<AttributeCategory> newCategories = findNewCategories(existingEncounterCategories, editedEncounterCategories);
		for (AttributeCategory newCategory : newCategories) {
			logger.info("new category {} for encounter {}", newCategory.getLabel(), encounter.getLabel());
			EncounterCategory ec = new EncounterCategory();
			ec.setCategory(newCategory);
			ec.setEncounter(encounter);
			ec.persist();
		}
	}

	private List<AttributeCategory> findNewCategories(
			List<? extends CategoryBase> existingCategories,
			Collection<AttributeCategory> editedCategories) {
		List<AttributeCategory> newCategories = new LinkedList<AttributeCategory>();
		for (AttributeCategory category : editedCategories) {
			boolean found = false;
			for (CategoryBase existing : existingCategories)
				if (existing.getCategory() == category)
					found = true;
			if (!found)
				newCategories.add(category);
		}
		return newCategories;
	}

	private List<? extends CategoryBase> findDeletedCategories(
			List<? extends CategoryBase> existingCategories,
			Collection<AttributeCategory> editedCategories) {
		List<CategoryBase> deletedCategories = new LinkedList<CategoryBase>();
		for (CategoryBase category : existingCategories) {
			boolean found = false;
			for (AttributeCategory edited : editedCategories)
				if (category.getCategory() == edited)
					found = true;
			if (!found)
				deletedCategories.add(category);
		}
		return deletedCategories;
	}

	private void removeDeletedCategories(
			List<? extends CategoryBase> existingCategories,
			Collection<AttributeCategory> editedCategories) {
		List<? extends CategoryBase> deletedCategories = findDeletedCategories(existingCategories, editedCategories);
		for (CategoryBase delete : deletedCategories) {
			logger.info("removed subject/encounter category for category {}", delete.getCategory().getLabel());
			deleteBean(delete);
		}
	}
	
	private void deleteBean(CategoryBase delete) {
		// problem with re-attaching detached objects
		EntityManager em = delete.entityManager;
		Query q = em.createQuery("FROM " + delete.getClass().getSimpleName() + " WHERE id = :id");
		q.setParameter("id", delete.getId());
		Object bean = q.getSingleResult();
		em.remove(bean);
	}

	public boolean haveAttributeWithType(List<? extends AttributeBase> attributes,
			AttributeType type) {
		for (AttributeBase attribute : attributes)
			if (attribute.getAttributeType() == type)
				return true;
		return false;
	}

	public void saveAttributes(List<? extends AttributeBase> attributes) {
		for (AttributeBase attribute : attributes)
			attribute.merge();
	}

	public boolean subjectHasAttributeWithType(Subject subject,
			AttributeType type) {
		Query q = SubjectAttribute.entityManager().createQuery("SELECT COUNT(*) FROM SubjectAttribute WHERE attributeType = :type AND subject = :subject");
		q.setParameter("type", type);
		q.setParameter("subject", subject);
		return ((Number)q.getSingleResult()).intValue() > 0;
	}

	private static Logger logger = LoggerFactory.getLogger(AttributeDao.class);
	
}
