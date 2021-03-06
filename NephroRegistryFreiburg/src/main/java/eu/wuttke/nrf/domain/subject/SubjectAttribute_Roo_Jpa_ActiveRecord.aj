// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.subject;

import eu.wuttke.nrf.domain.subject.SubjectAttribute;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SubjectAttribute_Roo_Jpa_ActiveRecord {
    
    public static long SubjectAttribute.countSubjectAttributes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SubjectAttribute o", Long.class).getSingleResult();
    }
    
    public static List<SubjectAttribute> SubjectAttribute.findAllSubjectAttributes() {
        return entityManager().createQuery("SELECT o FROM SubjectAttribute o", SubjectAttribute.class).getResultList();
    }
    
    public static SubjectAttribute SubjectAttribute.findSubjectAttribute(Long id) {
        if (id == null) return null;
        return entityManager().find(SubjectAttribute.class, id);
    }
    
    public static List<SubjectAttribute> SubjectAttribute.findSubjectAttributeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SubjectAttribute o", SubjectAttribute.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public SubjectAttribute SubjectAttribute.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SubjectAttribute merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
