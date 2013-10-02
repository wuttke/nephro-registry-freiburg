/**
 * $Id: LabRow.java,v 1.3 2009-02-28 23:30:17 matthias Exp $
 * Copyright Tobias Schäfer, Matthias Wuttke 2007
 * Alle Rechte vorbehalten.
 * Created: 02.07.2007 19:07:42
 */
package de.meona.infrastructure.lab;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Eine Zeile mit Laborwerten, als Container zur
 * Gruppierung gleichartiger LabItem-Objekte
 * @see LabItem
 * @author Matthias Wuttke
 * @version $Revision: 1.3 $
 */
public class LabRow
implements Cloneable, Serializable {

	private static final long serialVersionUID = -4017745316142392447L;

	/**
	 * Code der Werte in dieser Zeile,
	 * z.B. "LEUK"
	 * Kann leider auch null sein, wenn ein Mapping des externen Codes nicht
	 * möglich ist. 
	 */
	private LabRowType valueCode;
	
	/**
	 * Für den Benutzer sichtbarer Titel der Zeile,
	 * z.B. "Leukozyten"
	 */
	private String rowTitle;
	
	/**
	 * Einheit der Zeile (für den Benutzer),
	 * z.B. "G/l"
	 */
	private String valueUnit;
	
	/**
	 * Normalbereich der Werte in der Zeile (für den Benutzer),
	 * z.B. "4,4 - 11,3"
	 * Format: siehe Patterns
	 * Alternativ auch als String ohne Format
	 */
	private String normalRange;
	
	/**
	 * Bemerkung auf Zeilenebene
	 */
	private String rowNote;
	
	/**
	 * Einzelne Items der Zeile
	 */
	private List<LabItem> items;
	
	/**
	 * Externer Code für Laborwert
	 */
	private String externalCode;
	
	/**
	 * Gewicht der Zeile
	 */
	private String weight;
	
	public LabRow() {
	}
	
	public LabRow(LabRowType valueCode, String valueUnit, String rowTitle,
			String normalRange) {
		this.valueCode = valueCode;
		this.valueUnit = valueUnit;
		this.rowTitle = rowTitle;
		this.normalRange = normalRange;
	}

	/**
	 * Muster für Normalbereich mit Obergrenze
	 */
	public static Pattern UPPER_PATTERN = Pattern.compile("\\s*\\<\\s*(\\d*,?\\d*)\\s*"); //$NON-NLS-1$
	
	/**
	 * Muster für Normalbereich mit Untergrenze
	 */
	public static Pattern LOWER_PATTERN = Pattern.compile("\\s*\\>\\s*(\\d*,?\\d*)\\s*"); //$NON-NLS-1$
	
	/**
	 * Muster für Normalbereich mit Bereichsangabe
	 */
	public static Pattern RANGE_PATTERN = Pattern.compile("\\s*(\\d*,?\\d*)\\s*\\-\\s*(\\d*,?\\d*)\\s*"); //$NON-NLS-1$

	/**
	 * Liefert die Obergrenze des Normbereichs oder
	 * null, wenn es keine Grenze oder keine Obergrenze
	 * gibt oder wenn das Parsen nicht klappt. 
	 * @return Grenze oder null
	 */
	public Number getNormalRangeUpperBounds() {
		if (getNormalRange() == null || getNormalRange().length() == 0)
			return null;
		
		Matcher upperMatcher = UPPER_PATTERN.matcher(getNormalRange());
		if (upperMatcher.matches()) {
			String group = upperMatcher.group(1);
			return parseGermanNumber(group);
		}
		
		Matcher rangeMatcher = RANGE_PATTERN.matcher(getNormalRange());
		if (rangeMatcher.matches()) {
			String group = rangeMatcher.group(2);
			return parseGermanNumber(group);
		}
		
		return null;
	}

	/**
	 * Liefert die Untergrenze des Normbereichs oder
	 * null, wenn es keine Grenze oder keine Untergrenze
	 * gibt oder wenn das Parsen nicht klappt. 
	 * @return Grenze oder null
	 */
	public Number getNormalRangeLowerBounds() {
		if (getNormalRange() == null || getNormalRange().length() == 0)
			return null;
		
		Matcher lowerMatcher = LOWER_PATTERN.matcher(getNormalRange());
		if (lowerMatcher.matches()) {
			String group = lowerMatcher.group(1);
			return parseGermanNumber(group);
		}
		
		Matcher rangeMatcher = RANGE_PATTERN.matcher(getNormalRange());
		if (rangeMatcher.matches()) {
			String group = rangeMatcher.group(1);
			return parseGermanNumber(group);
		}
		
		return null;
	}
	
	private Number parseGermanNumber(String group) {
		return Double.parseDouble(group.replace(',', '.'));
	}

	public LabRow clone() {
		try {
			return (LabRow)super.clone();
		} catch (CloneNotSupportedException cnse) {
			throw new RuntimeException(cnse);
		}
	}
	
	public List<LabItem> getItems() {
		return items;
	}
	public void setItems(List<LabItem> items) {
		this.items = items;
	}
	public String getNormalRange() {
		return normalRange;
	}
	public void setNormalRange(String normalRange) {
		this.normalRange = normalRange;
	}
	public String getRowNote() {
		return rowNote;
	}
	public void setRowNote(String rowNote) {
		this.rowNote = rowNote;
	}
	public String getRowTitle() {
		return rowTitle;
	}
	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}
	public LabRowType getValueCode() {
		return valueCode;
	}
	public void setValueCode(LabRowType valueCode) {
		this.valueCode = valueCode;
	}
	public String getValueUnit() {
		return valueUnit;
	}
	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}

	public String getExternalCode() {
		return externalCode;
	}

	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getWeight() {
		return weight;
	}
	
}
