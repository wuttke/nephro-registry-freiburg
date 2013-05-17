package eu.wuttke.nrf.domain.medication;

import eu.wuttke.nrf.domain.misc.PrecisionDate;
import eu.wuttke.nrf.domain.subject.Subject;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Audited
@RooJpaActiveRecord(finders = { "findMedicationsBySubject" })
public class Medication {

	@NotNull
    @ManyToOne
    private Subject subject;

    @Column(length = 200)
    @NotNull
    private String label;

    private int mid;

    private double amount;

    @Column(length = 2000)
    private String description;

    @Column(length=3)
    private String pharmaceuticalForm;
    
    @Column(length = 12)
    private String atcCode;

    @Column(length = 16)
    @Embedded
    private PrecisionDate validFrom;

    @Column(length = 16)
    @Embedded
    private PrecisionDate validUntil;

}
