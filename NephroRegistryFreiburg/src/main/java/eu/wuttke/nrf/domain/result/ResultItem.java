package eu.wuttke.nrf.domain.result;

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

import eu.wuttke.nrf.domain.subject.Subject;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Audited
public class ResultItem {

	@ManyToOne
	private Subject subject;
	
	@ManyToOne
	private Result result;

	@Temporal(TemporalType.TIMESTAMP)
	private Date itemDateTime;
	
	@Enumerated(EnumType.STRING)
	private ResultItemType itemType;
	
	@Column(length=200)
	private String rawType;
	
	@NotNull
	@Column(length=200)
	private String label;

	@Column(length=800)
	private String stringValue;
	
	private Double numericValue;

	
}
