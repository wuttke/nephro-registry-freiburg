package eu.wuttke.nrf.export;

import org.apache.commons.lang3.StringUtils;
import org.springframework.roo.addon.javabean.RooJavaBean;

import eu.wuttke.nrf.domain.subject.Gender;

@RooJavaBean
public class PedFileLine {

	private String individualId;
	private String familyId;
	private String paternalId;
	private String maternalId;
	private Gender gender;
	private String phenotype;
	private String[] genotypes;
	
	public void appendStringLine(StringBuilder sb) {
		sb.append(StringUtils.isNotBlank(familyId) ? familyId : "0");
		sb.append(" ");
		
		sb.append(StringUtils.isNotBlank(individualId) ? individualId : "0");
		sb.append(" ");
		
		sb.append(StringUtils.isNotBlank(paternalId) ? paternalId : "0");
		sb.append(" ");
		
		sb.append(StringUtils.isNotBlank(maternalId) ? maternalId : "0");
		sb.append(" ");
		
		if (gender == null || gender == Gender.UNKNOWN)
			sb.append("0 ");
		else if (gender == Gender.FEMALE)
			sb.append("2 ");
		else if (gender == Gender.MALE)
			sb.append("1 ");
		else
			throw new IllegalArgumentException("unknown gender: " + gender);
		
		sb.append(StringUtils.isNotBlank(phenotype) ? phenotype : "0");
		
		if (genotypes != null) {
			assert genotypes.length % 2 == 0 : "must be even number of genotypes";
			for (String genotype : genotypes) {
				sb.append(" ");
				sb.append(StringUtils.isNotBlank(genotype) ? genotype : "0");
			}
		}
		
		sb.append("\n");
	}
	
	// parse PED line?
	
}
