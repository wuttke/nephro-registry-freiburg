package eu.wuttke.nrf.domain.subject;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

@Component
public class SubjectRepository {

	public Collection<Subject> performSubjectSearch(String query) {
		query = "%" + query + "%"; //$NON-NLS-2$ //$NON-NLS-1$
		EntityManager em = Subject.entityManager();
		TypedQuery<Subject> q = em.createQuery(
						"FROM Subject WHERE LOWER(lastName) LIKE LOWER(:query) OR patientId = :query OR LOWER(pseudonym) LIKE LOWER(:query)",
						Subject.class);
		q.setParameter("query", query);
		return q.getResultList();
	}

}
