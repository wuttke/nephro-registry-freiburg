package eu.wuttke.nrf.domain.misc;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@Embeddable
@RooToString
@RooJavaBean
public class PrecisionDate {

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(length=8)
	private PrecisionDateType precision;
	
	public PrecisionDate() {
		this.date = new Date();
		this.precision = PrecisionDateType.YEAR;
	}
	
	public PrecisionDate(Date date, PrecisionDateType precision) {
		this.date = date;
		this.precision = precision;
	}
	
}
