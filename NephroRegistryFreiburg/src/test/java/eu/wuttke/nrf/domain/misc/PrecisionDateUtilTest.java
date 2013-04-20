package eu.wuttke.nrf.domain.misc;

import java.util.Date;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.Test;

public class PrecisionDateUtilTest {

    @Test
	public void testFormatPrecisionDateYear() {
    	PrecisionDate pd = new PrecisionDate(PrecisionDateUtil.makeDate(2008, 10, 1), PrecisionDateType.YEAR);
		Assert.assertEquals("2008", PrecisionDateUtil.formatPrecisionDate(pd, Locale.getDefault()));
	}
    
    @Test
	public void testFormatPrecisionDateMonth() {
    	PrecisionDate pd = new PrecisionDate(PrecisionDateUtil.makeDate(2008, 10, 1), PrecisionDateType.MONTH);
		Assert.assertEquals("10/2008", PrecisionDateUtil.formatPrecisionDate(pd, Locale.getDefault()));
	}

    @Test
	public void testFormatPrecisionDateDay() {
    	PrecisionDate pd = new PrecisionDate(PrecisionDateUtil.makeDate(2008, 10, 1), PrecisionDateType.DAY);
		Assert.assertEquals("01.10.2008", PrecisionDateUtil.formatPrecisionDate(pd, Locale.GERMANY));
	}

    @Test
    public void testFormatAndParseDate() {
    	Date d = new Date();
    	String s = PrecisionDateUtil.formatDate(d, Locale.getDefault());
    	Date e = PrecisionDateUtil.parseDate(s);
    	String t = PrecisionDateUtil.formatDate(e, Locale.getDefault());
    	Assert.assertEquals(s, t);
	}

    @Test
    public void testParsePrecisionDateYear() {
    	PrecisionDate p = PrecisionDateUtil.parsePrecisionDate("2008");
    	Assert.assertEquals(PrecisionDateType.YEAR, p.getPrecision());
    	Assert.assertEquals(PrecisionDateUtil.makeDate(2008, 1, 1), p.getDate());
    }
    
    @Test
    public void testParsePrecisionDateMonth() {
    	PrecisionDate p = PrecisionDateUtil.parsePrecisionDate("10/2008");
    	Assert.assertEquals(PrecisionDateType.MONTH, p.getPrecision());
    	Assert.assertEquals(PrecisionDateUtil.makeDate(2008, 10, 1), p.getDate());
    }

    @Test
    public void testParsePrecisionDateDay() {
    	PrecisionDate p = PrecisionDateUtil.parsePrecisionDate("1.10.2008");
    	Assert.assertEquals(PrecisionDateType.DAY, p.getPrecision());
    	Assert.assertEquals(PrecisionDateUtil.makeDate(2008, 10, 1), p.getDate());
    }
    
    public void testCheckYear() {
    	Assert.assertEquals(new Integer(2019), PrecisionDateUtil.checkYear(19));
    	Assert.assertEquals(new Integer(1921), PrecisionDateUtil.checkYear(21));
    	Assert.assertNull(PrecisionDateUtil.checkYear(120));
    	Assert.assertEquals(new Integer(1980), PrecisionDateUtil.checkYear(1980));
    }

    
}
