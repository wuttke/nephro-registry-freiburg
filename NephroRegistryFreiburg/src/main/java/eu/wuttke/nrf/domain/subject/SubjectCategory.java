package eu.wuttke.nrf.domain.subject;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import eu.wuttke.nrf.domain.attribute.CategoryBase;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders={"findSubjectCategorysBySubject"})
@Audited
public class SubjectCategory extends CategoryBase {

	@ManyToOne
	@NotNull
	private Subject subject;
	
}
