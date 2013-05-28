package eu.wuttke.nrf.domain.misc;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrecisionDateUtil {

	/**
	 * Formats a date respecting its precision.
	 */
	public static String formatPrecisionDate(PrecisionDate d) {
		return formatPrecisionDate(d, Locale.getDefault());
	}

	/**
	 * Formats a date respecting its precision, using the given locale.
	 */
	public static String formatPrecisionDate(PrecisionDate d, Locale locale) {
		if (locale == null)
			locale = Locale.getDefault();
		
		if (d == null || d.getDate() == null)
			return "";
		
		switch (d.getPrecision()) {
		case DAY:
			return formatDate(d.getDate(), locale);
			
		case MONTH:
		case YEAR:
			Calendar gc = GregorianCalendar.getInstance();
			gc.setTime(d.getDate());
			if (d.getPrecision() == PrecisionDateType.MONTH)
				return (gc.get(Calendar.MONTH) + 1) + "/" + gc.get(Calendar.YEAR);
			else
				return Integer.toString(gc.get(Calendar.YEAR));
			
		default:
			return formatDate(d.getDate(), locale);
		}
	}

	/**
	 * Formats a date in short format using the current locale.
	 */
	public static String formatDate(Date date, Locale locale) {
		if (locale == null)
			locale = Locale.getDefault();
		return DateFormat.getDateInstance(DateFormat.MEDIUM, locale).format(date);
	}
	
	/**
	 * Parses a date in short format using the current locale,
	 * yielding NULL if the date is invalid.
	 */
	public static Date parseDate(String txt) {
		try {
			return DateFormat.getDateInstance(DateFormat.MEDIUM).parse(txt);
		} catch (ParseException e) {
			return null;
		}
	}

    /**
     * Tries to parse a given date, determining the
     * best possible date precision.
     */
    public static PrecisionDate parsePrecisionDate(String txt) {
    	PrecisionDate dt;
        dt = parsePrecisionDate(txt, PrecisionDateType.YEAR);
        if (dt != null)
        	return dt;
        
        dt = parsePrecisionDate(txt, PrecisionDateType.MONTH);
        if (dt != null)
            return dt;
        
        return parsePrecisionDate(txt, PrecisionDateType.DAY);
    }

	/**
	 * Parses a date with the given precision.
	 */
    public static PrecisionDate parsePrecisionDate(String txt, PrecisionDateType precision)
    {
        int year, month;
        switch (precision)
        {
            case YEAR:
                if (txt.length() != 2 && txt.length() != 4)
                    return null;

                year = Integer.parseInt(txt);
                if (checkYear(year) == null)
                    return null;
                
                return new PrecisionDate(makeDate(checkYear(year), 1, 1), precision);

            case MONTH:
                Matcher m = PATTERN_MONTH_YEAR_SHORT.matcher(txt);
                if (m == null || !m.matches())
                    m = PATTERN_MONTH_YEAR_LONG.matcher(txt);
                if (m == null || !m.matches())
                    return null;

                month = Integer.parseInt(m.group(1));
                if (month < 1 || month > 12)
                    return null;

                year = Integer.parseInt(m.group(2));
                if (checkYear(year) == null)
                    return null;

                return new PrecisionDate(makeDate(checkYear(year), month, 1), precision);

            case DAY:
            	Date date = parseDate(txt);
                return new PrecisionDate(date, precision);

            default:
                throw new RuntimeException("unsupported precision: " + precision);
        }
    }
    
    /**
     * Makes a Gregorian calendar date.
     */
    public static Date makeDate(int year, int month, int day) {
    	Calendar c = GregorianCalendar.getInstance();
    	c.set(Calendar.YEAR, year);
    	c.set(Calendar.MONTH, month-1);
    	c.set(Calendar.DAY_OF_MONTH, day);
    	c.set(Calendar.HOUR, 0);
    	c.set(Calendar.MINUTE, 0);
    	c.set(Calendar.SECOND, 0);
    	c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

    /**
     * Checks the given year number.
     * @param year year number with two or four digits
     * @return year number with for digist (1000..3000) or NULL
     */
    public static Integer checkYear(int year) {
        if (year < 100) {
            if (year < 20)
                year += 2000;
            else
                year += 1900;
        }
        if (year < 1000 || year > 3000)
            return null;
        return year;
    }
    
    private static Pattern PATTERN_MONTH_YEAR_LONG = Pattern.compile("^(\\d?\\d)[\\/\\.](\\d{4})$");
    private static Pattern PATTERN_MONTH_YEAR_SHORT = Pattern.compile("^(\\d?\\d)[\\/\\.](\\d{2})$");

}
