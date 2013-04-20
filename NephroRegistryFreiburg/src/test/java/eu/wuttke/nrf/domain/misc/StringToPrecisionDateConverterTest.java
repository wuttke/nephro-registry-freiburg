package eu.wuttke.nrf.domain.misc;

import java.util.Date;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.Test;

public class StringToPrecisionDateConverterTest {

    private StringToPrecisionDateConverter stringToPrecisionDateConverter = new StringToPrecisionDateConverter();

    @Test
    public void convertToModel() {
    	PrecisionDate pd = stringToPrecisionDateConverter.convertToModel("10/2008", Locale.US);
    	Assert.assertNotNull(pd);
    	Assert.assertNotNull(pd.getDate());
    	
        String s = PrecisionDateUtil.formatDate(pd.getDate(), Locale.GERMANY);
    	Assert.assertEquals(PrecisionDateType.MONTH, pd.getPrecision());
    	Assert.assertEquals("01.10.2008", s);
    }

    @Test
    public void convertToPresentation() {
        Date d = PrecisionDateUtil.makeDate(2008, 10, 1);
    	PrecisionDate pd = new PrecisionDate(d, PrecisionDateType.MONTH);
        Assert.assertEquals("10/2008", stringToPrecisionDateConverter.convertToPresentation(pd, Locale.US)); 
    }

    @Test
    public void getModelType() {
        Assert.assertEquals(PrecisionDate.class, stringToPrecisionDateConverter.getModelType());
    }

    @Test
    public void getPresentationType() {
        Assert.assertEquals(String.class, stringToPrecisionDateConverter.getPresentationType());
    }
    
}
