package eu.wuttke.nrf.domain.attribute;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@MappedSuperclass
public class AttributeBase {

    @ManyToOne
    @NotNull
    private AttributeType attributeType;
    
    @Column(length=102400)
    private String attributeValue;
    
}
