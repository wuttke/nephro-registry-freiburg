package eu.wuttke.nrf.domain.diagnosis;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import eu.wuttke.nrf.domain.misc.PrecisionDate;
import eu.wuttke.nrf.domain.subject.Subject;

@RooJavaBean
@RooToString
@Audited
@RooJpaActiveRecord(finders = { "findDiagnosesBySubject" })
public class Diagnosis {

	@NotNull
    @ManyToOne
    private Subject subject;

    @Column(length = 200)
    @NotNull
    private String label;

    @Column(length = 80)
    private String code;

    @Column(length = 2000)
    private String description;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length=30)
    private DiagnosisCodingSystem codingSystem;
    
    // Ausschluss von
    private boolean exclusion;

    @Embedded
    private PrecisionDate validFrom;

    @Embedded
    private PrecisionDate validUntil;
    
}
