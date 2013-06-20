// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.attribute;

import eu.wuttke.nrf.domain.attribute.AttributeCategory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AttributeCategory_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager AttributeCategory.entityManager;
    
    public static final EntityManager AttributeCategory.entityManager() {
        EntityManager em = new AttributeCategory().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long AttributeCategory.countAttributeCategorys() {
        return entityManager().createQuery("SELECT COUNT(o) FROM AttributeCategory o", Long.class).getSingleResult();
    }
    
    public static List<AttributeCategory> AttributeCategory.findAllAttributeCategorys() {
        return entityManager().createQuery("SELECT o FROM AttributeCategory o", AttributeCategory.class).getResultList();
    }
    
    public static AttributeCategory AttributeCategory.findAttributeCategory(Long id) {
        if (id == null) return null;
        return entityManager().find(AttributeCategory.class, id);
    }
    
    public static List<AttributeCategory> AttributeCategory.findAttributeCategoryEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM AttributeCategory o", AttributeCategory.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void AttributeCategory.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void AttributeCategory.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            AttributeCategory attached = AttributeCategory.findAttributeCategory(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void AttributeCategory.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void AttributeCategory.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public AttributeCategory AttributeCategory.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        AttributeCategory merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}