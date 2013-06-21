package eu.wuttke.nrf.domain.encounter;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import eu.wuttke.nrf.domain.attribute.AttributeCategory;

@RooJavaBean
@RooToString
@Audited
@RooJpaActiveRecord(finders = { "findEncounterCategorysByEncounter" })
public class EncounterCategory {

	@ManyToOne
	@NotNull
	private Encounter encounter;

	@ManyToOne
	@NotNull
	private AttributeCategory category;
	
}
