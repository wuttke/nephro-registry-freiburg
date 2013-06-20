package eu.wuttke.nrf.domain.encounter;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import eu.wuttke.nrf.domain.attribute.AttributeBase;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Audited
public class EncounterAttribute 
extends AttributeBase {
	
	@ManyToOne
	@NotNull
	private Encounter encounter;
	
}
