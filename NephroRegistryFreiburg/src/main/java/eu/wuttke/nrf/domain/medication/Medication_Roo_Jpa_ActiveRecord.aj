// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.medication;

import eu.wuttke.nrf.domain.medication.Medication;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Medication_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Medication.entityManager;
    
    public static final EntityManager Medication.entityManager() {
        EntityManager em = new Medication().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Medication.countMedications() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Medication o", Long.class).getSingleResult();
    }
    
    public static List<Medication> Medication.findAllMedications() {
        return entityManager().createQuery("SELECT o FROM Medication o", Medication.class).getResultList();
    }
    
    public static Medication Medication.findMedication(Long id) {
        if (id == null) return null;
        return entityManager().find(Medication.class, id);
    }
    
    public static List<Medication> Medication.findMedicationEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Medication o", Medication.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Medication.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Medication.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Medication attached = Medication.findMedication(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Medication.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Medication.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Medication Medication.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Medication merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
