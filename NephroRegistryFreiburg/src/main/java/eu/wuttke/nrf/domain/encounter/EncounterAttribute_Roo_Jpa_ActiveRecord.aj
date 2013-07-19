// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.encounter;

import eu.wuttke.nrf.domain.encounter.EncounterAttribute;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect EncounterAttribute_Roo_Jpa_ActiveRecord {
    
    public static long EncounterAttribute.countEncounterAttributes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM EncounterAttribute o", Long.class).getSingleResult();
    }
    
    public static List<EncounterAttribute> EncounterAttribute.findAllEncounterAttributes() {
        return entityManager().createQuery("SELECT o FROM EncounterAttribute o", EncounterAttribute.class).getResultList();
    }
    
    public static EncounterAttribute EncounterAttribute.findEncounterAttribute(Long id) {
        if (id == null) return null;
        return entityManager().find(EncounterAttribute.class, id);
    }
    
    public static List<EncounterAttribute> EncounterAttribute.findEncounterAttributeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM EncounterAttribute o", EncounterAttribute.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public EncounterAttribute EncounterAttribute.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        EncounterAttribute merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}