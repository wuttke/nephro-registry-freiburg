package eu.wuttke.nrf.ui.subject;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.presenter.SearchPresenter;

public class SubjectSearchPresenter extends
		SearchPresenter<Subject, SubjectSearchView> {

	public SubjectSearchPresenter() {
		super(new SubjectSearchView());
	}

	@Override
	protected Collection<Subject> performSearch(String query) {
		query = "%" + query + "%"; //$NON-NLS-2$ //$NON-NLS-1$
		EntityManager em = Subject.entityManager();
		TypedQuery<Subject> q = em.createQuery(
						"FROM Subject WHERE LOWER(lastName) LIKE LOWER(:query) OR patientId = :query OR LOWER(pseudonym) LIKE LOWER(:query)",
						Subject.class);
		q.setParameter("query", query);
		return q.getResultList();
	}

}
