/**
 * $Id: LabItemMarker.java,v 1.5 2009-06-14 13:24:11 matthias Exp $
 * Copyright Tobias Schäfer, Matthias Wuttke 2007
 * Alle Rechte vorbehalten.
 * Created: 02.07.2007 19:08:59
 */
package de.meona.infrastructure.lab;

/**
 * Markierung für Laborwerte
 * @author Matthias Wuttke
 * @version $Revision: 1.5 $
 */
public enum LabItemMarker {

	/**
	 * Sehr niedrig
	 */
	VERY_LOW,
	
	/**
	 * Niedrig
	 */
	LOW,
	
	/**
	 * Im Normbereich
	 */
	NORMAL,
	
	/**
	 * Hoch
	 */
	HIGH,
	
	/**
	 * Sehr hoch
	 */
	VERY_HIGH,
	
	/**
	 * Nicht bestimmt/bestimmbar
	 */
	DONT_KNOW,
	
	/**
	 * Pathologischer Befund
	 */
	PATHOLOGICAL,
	
	/**
	 * Intermediär ("etwas pathologisch")
	 */
	INTERMEDIATE,
	
	/**
	 * Ungültiger Wert, keine Bewertung möglich
	 */
	INVALID;
	
}
