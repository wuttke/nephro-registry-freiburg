// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.encounter;

import eu.wuttke.nrf.domain.encounter.Encounter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Encounter_Roo_Jpa_Entity {
    
    declare @type: Encounter: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Encounter.id;
    
    @Version
    @Column(name = "version")
    private Integer Encounter.version;
    
    public Long Encounter.getId() {
        return this.id;
    }
    
    public void Encounter.setId(Long id) {
        this.id = id;
    }
    
    public Integer Encounter.getVersion() {
        return this.version;
    }
    
    public void Encounter.setVersion(Integer version) {
        this.version = version;
    }
    
}
