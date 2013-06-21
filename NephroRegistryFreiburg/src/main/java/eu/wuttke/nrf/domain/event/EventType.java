package eu.wuttke.nrf.domain.event;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Audited
@RooJpaActiveRecord
public class EventType {

	@NotNull
	@Column(length=32)
	private String code;
	
	@NotNull
	@Column(length=200)
	private String label;
	
	@Column(length=2000)
	private String description;
	
}
