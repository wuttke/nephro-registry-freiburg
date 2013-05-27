package eu.wuttke.nrf.domain.visit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import eu.wuttke.nrf.domain.subject.Subject;

@RooJavaBean
@RooToString
@Audited
@RooJpaActiveRecord(finders = { "findVisitsBySubject" })
public class Visit {

    @NotNull
    @ManyToOne
    private Subject subject;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date visitDate;

    // z.B. Name der Ambulanz
    @Column(length=400)
    private String label;
    
}
