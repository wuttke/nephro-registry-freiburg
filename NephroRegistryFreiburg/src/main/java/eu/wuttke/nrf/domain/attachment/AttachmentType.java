package eu.wuttke.nrf.domain.attachment;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Audited
public class AttachmentType {

    @NotNull
    @Column(length = 128)
	private String shortcut;
    
    @NotNull
    @Column(length = 200)
	private String label;
	
}
