package eu.wuttke.nrf.domain.diagnosis;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField="")
public class Icd10Code {

	@Id
	@Column(length=12)
	@NotNull
	private String code;
	
	@NotNull
	@Column(length=200)
	private String name;
	
	@Column(length=12)
	private String trimmedCode;

	@Column(length=12)
	private String category;

}
