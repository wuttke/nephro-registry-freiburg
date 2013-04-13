package eu.wuttke.nrf.domain.audit;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class EnversSpringSecurityRevisionListener implements RevisionListener {

	public void newRevision(Object revisionEntity) {
		RevisionInfo revision = (RevisionInfo)revisionEntity;

		revision.setUsername("unknown");
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			Authentication auth = context.getAuthentication();
			if (auth != null && auth.getName() != null)
				revision.setUsername(auth.getName());
		}
	}

}
