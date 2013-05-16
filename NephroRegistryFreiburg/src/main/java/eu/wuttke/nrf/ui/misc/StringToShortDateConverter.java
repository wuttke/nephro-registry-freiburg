package eu.wuttke.nrf.ui.misc;

import java.text.DateFormat;
import java.util.Locale;

import com.vaadin.data.util.converter.StringToDateConverter;

public class StringToShortDateConverter 
extends StringToDateConverter {

	private static final long serialVersionUID = 1L;

	@Override
	protected DateFormat getFormat(Locale locale) {
        if (locale == null)
            locale = Locale.getDefault();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        df.setLenient(false);
		return df;
	}
	
}
