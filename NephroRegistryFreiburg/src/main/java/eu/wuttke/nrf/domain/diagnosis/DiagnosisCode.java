package eu.wuttke.nrf.domain.diagnosis;

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
public class DiagnosisCode {

	@NotNull
	@Column(length=20)
	private String code;

	@NotNull
	@Column(length=30)
	@Enumerated(EnumType.STRING)
	private DiagnosisCodingSystem codingSystem;
	
	@NotNull
	@Column(length=200)
	private String label;
	
	@Column(length=2000)
	private String description;
	
}
