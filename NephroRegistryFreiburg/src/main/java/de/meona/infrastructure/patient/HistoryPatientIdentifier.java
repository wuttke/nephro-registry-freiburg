package de.meona.infrastructure.patient;

/**
 * Ein Eintrag der Patientenhistorie.
 * @author Matthias Wuttke
 */
public class HistoryPatientIdentifier 
extends PatientIdentifier {

	private static final long serialVersionUID = 2231823116596531301L;

	/**
	 * Name der Station, auf der der Patient liegt/lag
	 */
	private String wardName;
	
	/**
	 * ID der Abteilung 
	 */
	private long departmentId;
	
	/**
	 * Name der Abteilung
	 */
	private String departmentName;
	
	/**
	 * Gibt es Meona Orders?
	 */
	private boolean ordersAvailable;
	
	/**
	 * Ist der Stay momentan ausgelagert?
	 */
	private boolean swappedOut;

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public boolean isOrdersAvailable() {
		return ordersAvailable;
	}

	public void setOrdersAvailable(boolean ordersAvailable) {
		this.ordersAvailable = ordersAvailable;
	}

	public boolean isSwappedOut() {
		return swappedOut;
	}

	public void setSwappedOut(boolean swappedOut) {
		this.swappedOut = swappedOut;
	}
	
	public long getDepartmentId() {
		return departmentId;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}
