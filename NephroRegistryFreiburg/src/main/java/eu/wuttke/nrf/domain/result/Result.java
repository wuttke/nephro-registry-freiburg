package eu.wuttke.nrf.domain.result;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import eu.wuttke.nrf.domain.subject.Subject;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Audited
public class Result {

	@ManyToOne
	private Subject subject;

	@Temporal(TemporalType.TIMESTAMP)
	private Date resultDateTime;
	
	@Column(length=400)
	private String title;
	
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private ResultType type;

	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private ResultClassification classification;
	
	@Column(length=2000)
	private String description;
	
}
