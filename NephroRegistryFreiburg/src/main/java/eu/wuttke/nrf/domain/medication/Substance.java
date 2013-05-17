package eu.wuttke.nrf.domain.medication;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField="")
public class Substance {

	@Id
	@NotNull
	private int substanceId;
	
	@Column(length=800)
	private String name;
	
}
