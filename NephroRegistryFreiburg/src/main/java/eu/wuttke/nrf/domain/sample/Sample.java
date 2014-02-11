package eu.wuttke.nrf.domain.sample;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
@RooJpaActiveRecord(finders = { "findSamplesBySubjectEquals" })
public class Sample {

	@Id
	@Column(length=40)
	@NotNull
	private String id;
	
	@ManyToOne
	private Subject subject;
	
	@Enumerated(EnumType.STRING)
	private SampleType sampleType;
	
    @Embedded
    private PrecisionDate sampleDate;
	
	private Integer amount;
	
	@Column(length=2000)
	private String comment;
	
}
