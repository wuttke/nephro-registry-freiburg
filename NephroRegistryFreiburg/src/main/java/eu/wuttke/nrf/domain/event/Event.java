package eu.wuttke.nrf.domain.event;

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
@Audited
@RooJpaActiveRecord(finders = { "findEventsBySubject" })
public class Event {

	@NotNull
	@ManyToOne
	private Subject subject;
	
	@NotNull
	@ManyToOne
	private EventType type;
	
	@NotNull
	@Embedded
	private PrecisionDate eventDate;
	
	@Column(length=1000)
	private String notes;
	
}
