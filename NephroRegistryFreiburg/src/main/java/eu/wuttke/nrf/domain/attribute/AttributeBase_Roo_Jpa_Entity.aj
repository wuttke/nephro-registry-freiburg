// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.attribute;

import eu.wuttke.nrf.domain.attribute.AttributeBase;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

privileged aspect AttributeBase_Roo_Jpa_Entity {
    
    declare @type: AttributeBase: @MappedSuperclass;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long AttributeBase.id;
    
    @Version
    @Column(name = "version")
    private Integer AttributeBase.version;
    
    public Long AttributeBase.getId() {
        return this.id;
    }
    
    public void AttributeBase.setId(Long id) {
        this.id = id;
    }
    
    public Integer AttributeBase.getVersion() {
        return this.version;
    }
    
    public void AttributeBase.setVersion(Integer version) {
        this.version = version;
    }
    
}
