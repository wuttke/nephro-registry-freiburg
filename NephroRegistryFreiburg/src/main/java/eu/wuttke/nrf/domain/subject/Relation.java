package eu.wuttke.nrf.domain.subject;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Audited
public class Relation {

    @NotNull
    @ManyToOne
    private Subject partner1;

    @NotNull
    @ManyToOne
    private Subject partner2;
    
}
