package eu.wuttke.nrf.domain.subject;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import eu.wuttke.nrf.domain.attribute.AttributeBase;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders={"findSubjectAttributesBySubject"})
@Audited
public class SubjectAttribute
extends AttributeBase {

    @ManyToOne
    @NotNull
    private Subject subject;
    
}
