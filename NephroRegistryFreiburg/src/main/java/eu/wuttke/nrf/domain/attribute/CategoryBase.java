package eu.wuttke.nrf.domain.attribute;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(mappedSuperclass=true)
public class CategoryBase {

	@ManyToOne
	@NotNull
	private AttributeCategory category;

}
