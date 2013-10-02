/**
 * $Id: LabItem.java,v 1.5 2009-12-01 09:22:56 wuttke Exp $
 * Copyright Tobias Schäfer, Matthias Wuttke 2007
 * Alle Rechte vorbehalten.
 * Created: 02.07.2007 19:08:43
 */
package de.meona.infrastructure.lab;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Einfache Abstraktion für einen einzelnen Laborwert,
 * der immer Teil einer Zeile ist.
 * @author Matthias Wuttke
 * @version $Revision: 1.5 $
 */
public class LabItem
implements Comparable<LabItem>, Serializable {
	
	private static final long serialVersionUID = -82228683157729028L;

	/**
	 * Datum und Uhrzeit der Probenentnahme (=Observation Date Time)
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date sampleDateTime;
	
	/**
	 * Datum und Uhrzeit der Auftragserfassung (=Transaction Date Time)
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDateTime;

	/**
	 * Zeitpunkt der Ergebnisgewinnung
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date resultAcquisitionDateTime;
	
	/**
	 * Datum und Uhrzeit der Berichtserstellung
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date reportDateTime;
	
	/**
	 * Der eigentliche Wert als String
	 * Locale-spezifisch formatiert (d.h. ",")
	 */
	@NotNull
	private String value;
	
	/**
	 * Optional Anmerkungen zum Wert
	 */
	private String note;
	
	/**
	 * Markierung (hoch, tief, etc.)
	 */
	private LabItemMarker marker;
	
	/**
	 * Status (vorläufig/endgültig)
	 */
	private ExaminationItemStatus status;
	
	/**
	 * Auftragsnummer
	 */
	private String labOrderNumber;
	
	public LabItem() {
	}
	
	public LabItem(Date orderDateTime, String value, LabItemMarker marker) {
		this.orderDateTime = orderDateTime;
		this.value = value;
		this.marker = marker;
	}

	/**
	 * Liefert den Wert als Zahl.
	 * @return Number oder Null (falsches Format, keine Angabe, etc.)
	 */
	public Double getValueAsDouble() {
		try {
			if (value != null && value.length() > 0)
				return Double.valueOf(getValue().replace(',', '.'));
		} catch (Throwable pe) {
			try {
				if (value != null && value.length() > 0)
					return Double.valueOf(getValue());
			} catch (Throwable nfe) {
			}
		}
		return null;
	}
	
	public int compareTo(LabItem o) {
		if (resultAcquisitionDateTime == null || o.getResultAcquisitionDateTime() == null)
			return 0;
		return resultAcquisitionDateTime.compareTo(o.getResultAcquisitionDateTime());
	}
	public LabItemMarker getMarker() {
		return marker;
	}
	public void setMarker(LabItemMarker marker) {
		this.marker = marker;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Date getResultAcquisitionDateTime() {
		return resultAcquisitionDateTime;
	}
	public void setResultAcquisitionDateTime(Date resultAcquisitionDateTime) {
		this.resultAcquisitionDateTime = resultAcquisitionDateTime;
	}

	public Date getSampleDateTime() {
		return sampleDateTime;
	}

	public void setSampleDateTime(Date sampleDateTime) {
		this.sampleDateTime = sampleDateTime;
	}

	public Date getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(Date orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public Date getReportDateTime() {
		return reportDateTime;
	}

	public void setReportDateTime(Date reportDateTime) {
		this.reportDateTime = reportDateTime;
	}
	
	public ExaminationItemStatus getStatus() {
		return status;
	}
	
	public void setStatus(ExaminationItemStatus status) {
		this.status = status;
	}
	
	public void setLabOrderNumber(String labOrderNumber) {
		this.labOrderNumber = labOrderNumber;
	}
	
	public String getLabOrderNumber() {
		return labOrderNumber;
	}
	
}
