package eu.wuttke.nrf.domain.attribute;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class AttributeType {

	@NotNull
	@Column(length=128)
	private String shortcut;
	
	@NotNull
	@Column(length=128)
	private String label;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private AttributeDataType dataType;
	
}
