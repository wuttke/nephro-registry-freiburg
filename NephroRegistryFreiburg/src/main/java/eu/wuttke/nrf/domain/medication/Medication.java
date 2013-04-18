package eu.wuttke.nrf.domain.medication;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import eu.wuttke.nrf.domain.misc.DatePrecision;
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
	
	@Temporal(TemporalType.DATE)
	private Date validFrom;
	
	@Temporal(TemporalType.DATE)
	private Date validUntil;
	
	@Enumerated(EnumType.STRING)
	@Column(length=16)
	private DatePrecision validFromPrecision;

	@Enumerated(EnumType.STRING)
	@Column(length=16)
	private DatePrecision validUntilPrecision;
	
}
