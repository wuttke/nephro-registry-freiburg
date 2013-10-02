package eu.wuttke.nrf.domain.subject;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

@Component
public class SubjectRepository {

	public Collection<Subject> performSubjectSearch(String query) {
		String queryContains = "%" + query + "%"; //$NON-NLS-2$ //$NON-NLS-1$
		EntityManager em = Subject.entityManager();
		TypedQuery<Subject> q = em.createQuery(
						"FROM Subject WHERE LOWER(lastName) LIKE LOWER(:query) OR patientId = :queryNum OR LOWER(pseudonym) LIKE LOWER(:query) OR LOWER(firstName) LIKE LOWER(:query)",
						Subject.class);
		q.setParameter("query", queryContains);
		q.setParameter("queryNum", query);
		return q.getResultList();
	}
	
}
