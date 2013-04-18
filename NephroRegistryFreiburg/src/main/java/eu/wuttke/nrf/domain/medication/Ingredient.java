package eu.wuttke.nrf.domain.medication;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Ingredient {

	@ManyToOne
	@NotNull
	private Medicament medicament;
	
	@Column(length=400)
	private String name;
	
	private Double amount;
	
	@Column(length=16)
	private String unit;
	
	private Long substanceId;
	
}
