// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.diagnosis;

import eu.wuttke.nrf.domain.diagnosis.Diagnosis;
import eu.wuttke.nrf.domain.subject.Subject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Diagnosis_Roo_Finder {
    
    public static TypedQuery<Diagnosis> Diagnosis.findDiagnosesBySubject(Subject subject) {
        if (subject == null) throw new IllegalArgumentException("The subject argument is required");
        EntityManager em = Diagnosis.entityManager();
        TypedQuery<Diagnosis> q = em.createQuery("SELECT o FROM Diagnosis AS o WHERE o.subject = :subject", Diagnosis.class);
        q.setParameter("subject", subject);
        return q;
    }
    
}
