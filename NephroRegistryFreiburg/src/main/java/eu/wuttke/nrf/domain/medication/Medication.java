package eu.wuttke.nrf.domain.medication;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
@RooJpaActiveRecord
@Audited
public class Medication {

	@ManyToOne
	private Subject subject;
	
	@Column(length=200)
	@NotNull
	private String label;
	
	private int mid;

	// daily sum dose (in medicament reference unit)
	private double amount;
	
	@Column(length=2000)
	private String description;
	
	@Column(length=16)
	@Embedded
	private PrecisionDate validFrom;

	@Column(length=16)
	@Embedded
	private PrecisionDate validUntil;
	
}
