package eu.wuttke.nrf.domain.attribute;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Audited
public class AttributeType {

	@NotNull
	private int sequenceNumber;
	
	@NotNull
	@Column(length=128)
	private String shortcut;
	
	@NotNull
	@Column(length=128)
	private String label;
	
	@NotNull
	@Column(length=80)
	private String groupName;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private AttributeDataType dataType;
	
	private Integer minimumLength;
	
	private Integer maximumLength;
	
	private Double minimumValue;
	
	private Double maximumValue;
	
	@Column(length=100)
	private String defaultValue;
	
	@Column(length=800)
	private String enumItems;
	
	private boolean required;
	
	@Column(length=32)
	@Enumerated(EnumType.STRING)
	private AttributeTypeUsage attributeUsage;
	
	@ManyToOne
	@NotNull
	private AttributeCategory category;
	
}
