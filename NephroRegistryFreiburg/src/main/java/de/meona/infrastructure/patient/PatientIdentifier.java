/**
 * $Id: PatientIdentifier.java,v 1.19 2011-09-16 10:50:00 wuttke Exp $
 * Copyright Tobias Schäfer, Matthias Wuttke 2007
 * Alle Rechte vorbehalten.
 * Created: 23.06.2007 14:27:13
 */
package de.meona.infrastructure.patient;

import java.io.Serializable;
import java.util.Date;

/**
 * Objekt zur Patientenidentifikation
 * Wird nicht in Datenbank gespeichert
 * @author Matthias Wuttke
 * @version $Revision: 1.19 $
 */
public class PatientIdentifier
implements Serializable {

	private static final long serialVersionUID = -8530917927867508179L;

	/**
	 * Patienten-Nummer
	 */
	private long patientId;
	
	/**
	 * Fall-Nummer = Meona-Kurvennummer
	 */
	private long caseId;
	
	/**
	 * Externe Fall-/Aufenthaltsnummer des 
	 * Basis-KIS
	 */
	private String externalCaseId;
	
	/**
	 * Aufenthaltsart
	 */
	private StayType stayType;
	
	/**
	 * Flag für (bisher nur) vorläufig erfolgte Aufnahme,
	 * z.B. Notfall ohne persönliche Daten
	 */
	private boolean preliminaryAdmission;
	
	/**
	 * Stations-ID
	 */
	private long wardId;
	
	/**
	 * Kostenstellen-ID
	 */
	private long accountId;
	
	/**
	 * Geschlecht des Patienten (M/W)
	 */
	//@NotNull
	//@Enumerated(EnumType.ORDINAL)
	//private Gender gender;
	
	/**
	 * Akademischer Titel
	 */
	private String title;	
	
	/**
	 * Vorname des Patienten
	 */
	private String firstName;
	
	/**
	 * Nachname des Patienten
	 */
	private String lastName;
	
	/**
	 * Geburtsdatum des Patienten
	 */
	private Date birthDate;

	/**
	 * Behandlungsrecht
	 */
	private boolean accessRight;
	
	/**
	 * Beginn des Aufenthalts
	 */
	private Date stayBegin;
	
	/**
	 * Ende des Aufenthalts
	 */
	private Date stayEnd;

	/**
	 * Geplantes Ende des Aufenthalts
	 */
	private Date plannedStayEnd;
	
	/**
	 * Liste aller OrderTypes, für die es nicht-vidierte
	 * Orders für den Patienten gibt.
	 */
	private int[] unseenOrders = null;
	
	/**
	 * Betreuender Arzt
	 * (aus Stay Value)
	 */
	private String primaryPhysician;
	
	/**
	 * Primary Nurse
	 * (aus Stay Value)
	 */
	private String primaryNurse;
	
	/**
	 * Flags (z.B. A3S3?);
	 * derzeit unbenutzt
	 */
	private String flags;
	
	/**
	 * Optionale PIZ der Mutter, wenn übermittelt.
	 * (MeonaPatientSource unterstützt dieses Feld nicht.)
	 * @see PatientRelation
	 */
	private Long motherPatientId;
	
	/**
	 * Beginn der Bewegung
	 */
	private Date moveBegin;
	
	/**
	 * Ende der Bewegung
	 */
	private Date moveEnd;
	
	/**
	 * Kommentar
	 */
	private String comment;
	
	public Date getStayBegin() {
		return stayBegin;
	}

	public void setStayBegin(Date stayBegin) {
		this.stayBegin = stayBegin;
	}

	public Date getStayEnd() {
		return stayEnd;
	}

	public void setStayEnd(Date stayEnd) {
		this.stayEnd = stayEnd;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public long getCaseId() {
		return caseId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public boolean getAccessRight() {
		return accessRight;
	}

	public void setAccessRight(boolean accessRight) {
		this.accessRight = accessRight;
	}

	public int[] getUnseenOrders() {
		return unseenOrders;
	}
	
	public void setUnseenOrders(int[] unseenOrders) {
		this.unseenOrders = unseenOrders;
	}
	
	public StayType getStayType() {
		return stayType;
	}
	
	public void setStayType(StayType stayType) {
		this.stayType = stayType;
	}

	public boolean isPreliminaryAdmission() {
		return preliminaryAdmission;
	}

	public void setPreliminaryAdmission(boolean preliminaryAdmission) {
		this.preliminaryAdmission = preliminaryAdmission;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setExternalCaseId(String externalCaseId) {
		this.externalCaseId = externalCaseId;
	}
	
	public String getExternalCaseId() {
		return externalCaseId;
	}

	public String getPrimaryPhysician() {
		return primaryPhysician;
	}

	public void setPrimaryPhysician(String primaryPhysician) {
		this.primaryPhysician = primaryPhysician;
	}

	public String getPrimaryNurse() {
		return primaryNurse;
	}

	public void setPrimaryNurse(String primaryNurse) {
		this.primaryNurse = primaryNurse;
	}
	
	public long getWardId() {
		return wardId;
	}
	
	public void setWardId(long wardId) {
		this.wardId = wardId;
	}
	
	public long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public void setMotherPatientId(Long motherPatientId) {
		this.motherPatientId = motherPatientId;
	}
	
	public Long getMotherPatientId() {
		return motherPatientId;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public Date getPlannedStayEnd() {
		return plannedStayEnd;
	}
	
	public void setPlannedStayEnd(Date plannedStayEnd) {
		this.plannedStayEnd = plannedStayEnd;
	}
	
	public void setMoveBegin(Date moveBegin) {
		this.moveBegin = moveBegin;
	}
	
	public void setMoveEnd(Date moveEnd) {
		this.moveEnd = moveEnd;
	}
	
	public Date getMoveBegin() {
		return moveBegin;
	}
	
	public Date getMoveEnd() {
		return moveEnd;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
	
}
