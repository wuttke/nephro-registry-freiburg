// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.attribute;

import eu.wuttke.nrf.domain.attribute.CategoryBase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect CategoryBase_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager CategoryBase.entityManager;
    
    public static final EntityManager CategoryBase.entityManager() {
        EntityManager em = new CategoryBase().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long CategoryBase.countCategoryBases() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CategoryBase o", Long.class).getSingleResult();
    }
    
    public static List<CategoryBase> CategoryBase.findAllCategoryBases() {
        return entityManager().createQuery("SELECT o FROM CategoryBase o", CategoryBase.class).getResultList();
    }
    
    public static CategoryBase CategoryBase.findCategoryBase(Long id) {
        if (id == null) return null;
        return entityManager().find(CategoryBase.class, id);
    }
    
    public static List<CategoryBase> CategoryBase.findCategoryBaseEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM CategoryBase o", CategoryBase.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void CategoryBase.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void CategoryBase.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            CategoryBase attached = CategoryBase.findCategoryBase(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void CategoryBase.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void CategoryBase.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public CategoryBase CategoryBase.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CategoryBase merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
