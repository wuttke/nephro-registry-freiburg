// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.encounter;

import eu.wuttke.nrf.domain.encounter.EncounterCategory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect EncounterCategory_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager EncounterCategory.entityManager;
    
    public static final EntityManager EncounterCategory.entityManager() {
        EntityManager em = new EncounterCategory().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long EncounterCategory.countEncounterCategorys() {
        return entityManager().createQuery("SELECT COUNT(o) FROM EncounterCategory o", Long.class).getSingleResult();
    }
    
    public static List<EncounterCategory> EncounterCategory.findAllEncounterCategorys() {
        return entityManager().createQuery("SELECT o FROM EncounterCategory o", EncounterCategory.class).getResultList();
    }
    
    public static EncounterCategory EncounterCategory.findEncounterCategory(Long id) {
        if (id == null) return null;
        return entityManager().find(EncounterCategory.class, id);
    }
    
    public static List<EncounterCategory> EncounterCategory.findEncounterCategoryEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM EncounterCategory o", EncounterCategory.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void EncounterCategory.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void EncounterCategory.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            EncounterCategory attached = EncounterCategory.findEncounterCategory(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void EncounterCategory.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void EncounterCategory.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public EncounterCategory EncounterCategory.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        EncounterCategory merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}