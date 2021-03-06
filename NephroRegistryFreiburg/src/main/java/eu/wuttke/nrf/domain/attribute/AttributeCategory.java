package eu.wuttke.nrf.domain.attribute;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Audited
public class AttributeCategory {

	private int sequenceNumber;
	
	@Column(length=200)
	private String label;
	
	@Column(length=16)
	@Enumerated(EnumType.STRING)
	private AttributeParentType parentType;
	
	@Override
	public String toString() {
		return String.format("%s (%s)", label, parentType);
	}
    
    @Override
    public boolean equals(Object obj) {
    	Long myId = getId();
    	if (obj != null && obj instanceof AttributeCategory) {
	    	Long otherId = ((AttributeCategory)obj).getId();
	    	if (myId != null && otherId != null)
	    		return myId.longValue() == otherId.longValue();
    	}
		return super.equals(obj);
    }

}
