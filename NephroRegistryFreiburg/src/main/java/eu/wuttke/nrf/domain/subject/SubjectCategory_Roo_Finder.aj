// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.subject;

import eu.wuttke.nrf.domain.attribute.AttributeCategory;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.subject.SubjectCategory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect SubjectCategory_Roo_Finder {
    
    public static TypedQuery<SubjectCategory> SubjectCategory.findSubjectCategorysBySubject(Subject subject) {
        if (subject == null) throw new IllegalArgumentException("The subject argument is required");
        EntityManager em = SubjectCategory.entityManager();
        TypedQuery<SubjectCategory> q = em.createQuery("SELECT o FROM SubjectCategory AS o WHERE o.subject = :subject", SubjectCategory.class);
        q.setParameter("subject", subject);
        return q;
    }
    
    public static TypedQuery<SubjectCategory> SubjectCategory.findSubjectCategorysBySubjectAndCategory(Subject subject, AttributeCategory category) {
        if (subject == null) throw new IllegalArgumentException("The subject argument is required");
        if (category == null) throw new IllegalArgumentException("The category argument is required");
        EntityManager em = SubjectCategory.entityManager();
        TypedQuery<SubjectCategory> q = em.createQuery("SELECT o FROM SubjectCategory AS o WHERE o.subject = :subject AND o.category = :category", SubjectCategory.class);
        q.setParameter("subject", subject);
        q.setParameter("category", category);
        return q;
    }
    
}
