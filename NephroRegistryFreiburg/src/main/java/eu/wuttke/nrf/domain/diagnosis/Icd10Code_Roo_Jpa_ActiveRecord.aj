// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.diagnosis;

import eu.wuttke.nrf.domain.diagnosis.Icd10Code;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Icd10Code_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Icd10Code.entityManager;
    
    public static final EntityManager Icd10Code.entityManager() {
        EntityManager em = new Icd10Code().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Icd10Code.countIcd10Codes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Icd10Code o", Long.class).getSingleResult();
    }
    
    public static List<Icd10Code> Icd10Code.findAllIcd10Codes() {
        return entityManager().createQuery("SELECT o FROM Icd10Code o", Icd10Code.class).getResultList();
    }
    
    public static Icd10Code Icd10Code.findIcd10Code(String code) {
        if (code == null || code.length() == 0) return null;
        return entityManager().find(Icd10Code.class, code);
    }
    
    public static List<Icd10Code> Icd10Code.findIcd10CodeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Icd10Code o", Icd10Code.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Icd10Code.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Icd10Code.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Icd10Code attached = Icd10Code.findIcd10Code(this.code);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Icd10Code.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Icd10Code.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Icd10Code Icd10Code.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Icd10Code merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
