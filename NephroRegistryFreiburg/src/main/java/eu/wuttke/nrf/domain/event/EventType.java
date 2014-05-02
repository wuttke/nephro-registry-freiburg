package eu.wuttke.nrf.domain.event;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Audited
@RooJpaActiveRecord(finders = { "findEventTypesByCodeEquals" })
public class EventType {

    @NotNull
    @Column(length = 32)
    private String code;

    @NotNull
    @Column(length = 200)
    private String label;

    @Column(length = 2000)
    private String description;

    @Override
    public String toString() {
        return label;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof EventType && getId() != null)
    		return getId().equals(((EventType)obj).getId());

   		return super.equals(obj);
    }
    
}
