// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.attribute;

import eu.wuttke.nrf.domain.attribute.AttributeCategory;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect AttributeCategory_Roo_Jpa_Entity {
    
    declare @type: AttributeCategory: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long AttributeCategory.id;
    
    @Version
    @Column(name = "version")
    private Integer AttributeCategory.version;
    
    public Long AttributeCategory.getId() {
        return this.id;
    }
    
    public void AttributeCategory.setId(Long id) {
        this.id = id;
    }
    
    public Integer AttributeCategory.getVersion() {
        return this.version;
    }
    
    public void AttributeCategory.setVersion(Integer version) {
        this.version = version;
    }
    
}
